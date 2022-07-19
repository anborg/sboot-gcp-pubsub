package demo.pubsub;

import com.google.cloud.spring.pubsub.core.PubSubTemplate;
import com.google.cloud.spring.pubsub.integration.outbound.PubSubMessageHandler;
import com.google.cloud.spring.pubsub.support.BasicAcknowledgeablePubsubMessage;
import com.google.cloud.spring.pubsub.support.GcpPubSubHeaders;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.integration.annotation.ServiceActivator;
import org.springframework.messaging.MessageHandler;
import org.springframework.messaging.handler.annotation.Header;
import org.springframework.stereotype.Component;

@Component
public class B1_inputMsgCh_to_topic2_channelAdapter {
    private static final Log LOGGER = LogFactory.getLog(B1_inputMsgCh_to_topic2_channelAdapter.class);

    /**
     * The ServiceActivator bean messageReceiver logs each message arriving in inputMessageChannel
     * to the standard output and then acknowledges the message.
     */
    @ServiceActivator(inputChannel = "inputMessageChannel")
    public void messageReceiver(String payload,
            @Header(GcpPubSubHeaders.ORIGINAL_MESSAGE) BasicAcknowledgeablePubsubMessage message) {
        LOGGER.info("Message arrived via an inbound channel adapter from sub-one! Payload: " + payload);
        message.ack();
    }
    // Create an outbound channel adapter to send messages from the input message channel to the
    // topic `topic-two`.

    /**
     * PubSubMessageHandler is the outbound channel adapter for GCP Pub/Sub that listens for new messages on a Spring MessageChannel
     * PubSubMessageHandler publishes messages asynchronously by default
     *
     * setSuccessCallback() and setFailureCallback() methods (either one or both may be set). These give access to the
     * Pub/Sub publish message ID in case of success, or the root cause exception in case of error.
     */
    @Bean
    @ServiceActivator(inputChannel = "inputMessageChannel")
    public MessageHandler messageSender(PubSubTemplate pubsubTemplate) {
        PubSubMessageHandler adapter = new PubSubMessageHandler(pubsubTemplate, "topic-two");

        adapter.setSuccessCallback(
                ((ackId, message) ->
                        LOGGER.info("Message was sent via the outbound channel adapter to topic-two! ackiId="+ackId)));

        adapter.setFailureCallback(
                (cause, message) ->
                        LOGGER.info("Error sending " + message + " due to " + cause));

        return adapter;
    }
}
