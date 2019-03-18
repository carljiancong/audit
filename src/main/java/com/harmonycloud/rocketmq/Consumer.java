package com.harmonycloud.rocketmq;

import com.alibaba.fastjson.JSON;
import com.harmonycloud.bo.Audit;
import com.harmonycloud.entity.CimsAudit;
import com.harmonycloud.enums.ErrorMsgEnum;
import com.harmonycloud.exception.AuditException;
import com.harmonycloud.repository.AuditRepository;
import org.apache.rocketmq.client.consumer.DefaultMQPushConsumer;
import org.apache.rocketmq.client.consumer.listener.ConsumeConcurrentlyStatus;
import org.apache.rocketmq.client.consumer.listener.MessageListenerConcurrently;
import org.apache.rocketmq.common.consumer.ConsumeFromWhere;
import org.apache.rocketmq.common.message.MessageExt;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Service;

/**
 * @author qidong
 * @date 2019/3/4
 */
@Service
public class Consumer implements CommandLineRunner {
    /**
     * 消费者
     */
    @Value("${apache.rocketmq.consumer.pushConsumer}")
    private String pushConsumer;

    /**
     * NameServer 地址
     */
    @Value("${apache.rocketmq.namesrvAddr}")
    private String namesrvAddr;


    @Autowired
    AuditRepository auditRepository;

    private Logger logger = LoggerFactory.getLogger(this.getClass());

    /**
     * 初始化RocketMq的监听信息，渠道信息
     */
    public void messageListener() {

        DefaultMQPushConsumer consumer = new DefaultMQPushConsumer("Order");

        consumer.setNamesrvAddr(namesrvAddr);
        try {

            // 订阅PushTopic下Tag为push的消息,都订阅消息
            consumer.subscribe("OrderTopic", "OrderPush");

            // 程序第一次启动从消息队列头获取数据
            consumer.setConsumeFromWhere(ConsumeFromWhere.CONSUME_FROM_FIRST_OFFSET);
            //可以修改每次消费消息的数量，默认设置是每次消费一条
            consumer.setConsumeMessageBatchMaxSize(1);

            consumer.registerMessageListener((MessageListenerConcurrently) (msgs, context) -> {
                //在此监听中消费信息，并返回消费的状态信息

                try {
                    // msgs中只收集同一个topic，同一个tag，并且key相同的message
                    // 会把不同的消息分别放置到不同的队列中
                    for (MessageExt messageExt : msgs) {
                        Audit audit = JSON.parseObject(new String(messageExt.getBody()), Audit.class);
                        CimsAudit cimsAudit = new CimsAudit(audit);
                        auditRepository.save(cimsAudit);
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                    return ConsumeConcurrentlyStatus.RECONSUME_LATER; //稍后再试
                }
                return ConsumeConcurrentlyStatus.CONSUME_SUCCESS; //消费成功
            });

            consumer.start();

        } catch (
                Exception e)

        {
            logger.info(e.getMessage());
            throw new AuditException(ErrorMsgEnum.ROCKETMQ_ERROR.getMessage());
        }

    }


    @Override
    public void run(String... args) throws Exception {
        this.messageListener();
    }
}
