package demo.rabbit;

import com.rabbitmq.client.Channel;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.util.Date;

@Slf4j
@Component
public class DeadLetterQueueConsumer {

    @RabbitListener(queues = "QAT")
    public void receiveD(Message message, Channel channel) throws IOException
    {
        String msg = new String(message.getBody());
        log.info("1当前时间：{},收到死信队列信息{}", new Date().toString(), msg);
    }

    @RabbitListener(queues = "QAT")
    public void receiveAT(Message message, Channel channel) throws IOException
    {
        String msg = new String(message.getBody());
        log.info("2当1前时间：{},收到死信队列信息{}", new Date().toString(), msg);
    }

    @RabbitListener(queues = "QCT")
    public void receiveET(Message message, Channel channel) throws IOException
    {
        String msg = new String(message.getBody());
        log.info("3当1前时间：{},收到死信队列信息{}", new Date().toString(), msg);
    }


    @RabbitListener(queues = "QD")
    public void receiveCT(Message message, Channel channel) throws IOException
    {
        String msg = new String(message.getBody());
        log.info("当前时间：{},收到死信队列信息{}", new Date().toString(), msg);
    }
}
