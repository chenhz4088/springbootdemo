package demo.rabbit;

import org.springframework.amqp.core.*;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.HashMap;

@Configuration
public class QueueTest {
    public static final String X_EXCHANGE = "XT";
    public static final String QUEUE_A = "QAT";
    public static final String QUEUE_C = "QCT";
//    public static final String QUEUE_B = "QB";
//    public static final String Y_DEAD_LETTER_EXCHANGE = "YT";
//    public static final String DEAD_LETTER_QUEUE = "QD";

    @Bean("xtExchange")
    public DirectExchange xExchange(){
        return new DirectExchange(X_EXCHANGE);
    }

//    @Bean("yExchange")
//    public DirectExchange yExchange(){
//        return new DirectExchange(Y_DEAD_LETTER_EXCHANGE);
//    }

    @Bean("queueAT")
    public Queue queueA(){
        HashMap<String,Object> args = new HashMap<>(3);
//        args.put("x-dead-letter-exchange",Y_DEAD_LETTER_EXCHANGE);
//        args.put("x-dead-letter-routing-key","YD");
//        args.put("x-message-ttl",10000);
        return QueueBuilder.durable(QUEUE_A).withArguments(args).build();
    }

//    @Bean("queueB")
//    public Queue queueB(){
//        HashMap<String,Object> args = new HashMap<>(3);
////        args.put("x-dead-letter-exchange",Y_DEAD_LETTER_EXCHANGE);
////        args.put("x-dead-letter-routing-key","YD");
////        args.put("x-message-ttl",40000);
//        return QueueBuilder.durable(QUEUE_B).withArguments(args).build();
//    }

    @Bean("queueCT")
    public Queue queueC(){
        HashMap<String,Object> args = new HashMap<>(3);
//        args.put("x-dead-letter-exchange",Y_DEAD_LETTER_EXCHANGE);
//        args.put("x-dead-letter-routing-key","YD");
        return QueueBuilder.durable(QUEUE_C).withArguments(args).build();
    }

    @Bean
    public Binding queueaAindingXT(@Qualifier("queueAT")Queue queueA,
                                  @Qualifier("xtExchange")DirectExchange xExchange){
        return BindingBuilder.bind(queueA).to(xExchange).with("XAT");
    }

    @Bean
    public Binding queuebCindingXT(@Qualifier("queueCT")Queue queueB,
                                  @Qualifier("xtExchange")DirectExchange xExchange){
        return BindingBuilder.bind(queueB).to(xExchange).with("XAT");
    }
}

//    @Bean
//    public Binding queuecBindingXT(@Qualifier("queueC")Queue queueC,
//                                  @Qualifier("xExchange")DirectExchange xExchange){
//        return BindingBuilder.bind(queueC).to(xExchange).with("XC");
//    }
//}

//    @Bean("queueD")
//    public Queue queueD(){
//        return new Queue(DEAD_LETTER_QUEUE);
//    }


//    @Bean
//    public Binding deadLetterBindingQAD(@Qualifier("queueD") Queue queueD,
//                                        @Qualifier("yExchange") DirectExchange yExchange){
//        return BindingBuilder.bind(queueD).to(yExchange).with("YD");
//    }