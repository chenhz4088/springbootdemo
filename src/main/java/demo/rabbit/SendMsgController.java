package demo.rabbit;


import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.time.DateTimeException;
import java.util.Date;

@RestController
@Slf4j
@RequestMapping("ttl")
public class SendMsgController {

    @Autowired
    private RabbitTemplate rabbitTemplate;

    @GetMapping("sendMsg/{msg}")
    public void sendMsg(@PathVariable String msg){
        log.info("1当前时间：{},发送一条信息给1个 TTL 队列:{}", new Date(), msg);
        rabbitTemplate.convertAndSend("XT", "XAT", "消息来自 ttl 为 10S 的队列: "+msg);
    }

    @GetMapping("sendMsg/{msg}/{time}")
    public void sendMsgOfTime(@PathVariable String msg,@PathVariable String time){
        log.info("当前时间：{},发送一条信息给两个 TTL 队列:{}", new Date(), msg);
        rabbitTemplate.convertAndSend("X", "XC", "消息来自 ttl 为"+time+"S 的队列: "+msg,(correlation)->
        {
            correlation.getMessageProperties().setExpiration(time);
            return correlation;
        }
        );
//        rabbitTemplate.convertAndSend("X", "XB", "消息来自 ttl 为 40S 的队列: "+msg);
    }



}
