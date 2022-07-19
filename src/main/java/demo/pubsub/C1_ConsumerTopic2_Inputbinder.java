package demo.pubsub;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.messaging.Message;
import org.springframework.stereotype.Component;

import java.util.function.Consumer;
@Component
/**
 * Create an input binder to receive messages from `topic-two` using a Consumer bean.
 **/
public class C1_ConsumerTopic2_Inputbinder {
    private static final Log LOGGER = LogFactory.getLog(C1_ConsumerTopic2_Inputbinder.class);
    @Bean
    public Consumer<Message<String>> receiveMessageFromTopicTwo() {
        return message -> {
            LOGGER.info("Message arrived via an input binder from topic-two! Payload: " + message.getPayload());
        };
    }
}
