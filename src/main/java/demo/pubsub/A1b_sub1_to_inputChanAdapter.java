package demo.pubsub;

import com.google.cloud.spring.pubsub.core.PubSubTemplate;
import com.google.cloud.spring.pubsub.integration.AckMode;
import com.google.cloud.spring.pubsub.integration.inbound.PubSubInboundChannelAdapter;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.integration.annotation.ServiceActivator;
import org.springframework.integration.channel.PublishSubscribeChannel;
import org.springframework.messaging.Message;
import org.springframework.messaging.MessageChannel;
import org.springframework.messaging.MessagingException;
import org.springframework.stereotype.Component;

@Component
public class A1b_sub1_to_inputChanAdapter {
    private static final Log LOGGER = LogFactory.getLog(A1b_sub1_to_inputChanAdapter.class);
    // Create a message channel for messages arriving from the subscription `sub-one`.
    @Bean
    public MessageChannel inputMessageChannel() {
        return new PublishSubscribeChannel();
    }

    /**
     * The inboundChannelAdapter asynchronously pulls messages from sub-one using a PubSubTemplate
     * and sends the messages to inputMessageChannel.
     *
     * inboundChannelAdapter sets the acknowledgement mode to MANUAL
     * so the application can acknowledge messages after it processes them.
     */
    @Bean
    public PubSubInboundChannelAdapter inboundChannelAdapter(@Qualifier("inputMessageChannel") MessageChannel messageChannel,
                                                             PubSubTemplate pubSubTemplate) {
        PubSubInboundChannelAdapter adapter =
                new PubSubInboundChannelAdapter(pubSubTemplate, "sub-one");
        adapter.setOutputChannel(messageChannel);
        adapter.setAckMode(AckMode.MANUAL); //SET ack to MANUAL, so the consumr
        adapter.setPayloadType(String.class);
        adapter.setErrorChannelName("suboneERRORS");
        return adapter;
    }

    @ServiceActivator(inputChannel =  "suboneERRORS")
    public void pubsubErrorHandler(Message<MessagingException> message) {
        LOGGER.warn("This message will be automatically acked because error handler completes successfully");
    }
}
