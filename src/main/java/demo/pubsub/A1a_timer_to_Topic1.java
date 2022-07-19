package demo.pubsub;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.integration.support.MessageBuilder;
import org.springframework.messaging.Message;
import org.springframework.stereotype.Component;
import reactor.core.publisher.Flux;
import reactor.core.scheduler.Schedulers;

import java.util.Random;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.function.Supplier;
@Component
public class A1a_timer_to_Topic1 {
    private static final Log LOGGER = LogFactory.getLog(A1a_timer_to_Topic1.class);
    private static final Random rand = new Random(2020);
    AtomicInteger count = new AtomicInteger(0);
    // Create an output binder to send messages to `topic-one` using a Supplier bean.
    @Bean
    public Supplier<Flux<Message<String>>> sendMessageToTopicOne() {
        return () ->
                Flux.<Message<String>>generate(
                    sink -> {
                        try {
                            Thread.sleep(10000);
                        } catch (InterruptedException e) {
                            // Stop sleep earlier.
                        }

                        Message<String> message =
                                MessageBuilder.withPayload("message-" + count.getAndIncrement()).build();
                        LOGGER.info("Sending a message via the output binder to topic-one! Payload: "
                                        + message.getPayload());
                        sink.next(message);
                    }).subscribeOn(Schedulers.boundedElastic());
    }
    // [END pubsub_spring_cloud_stream_output_binder]
}
