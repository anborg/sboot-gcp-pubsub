package demo.api;

import com.google.cloud.spring.pubsub.core.publisher.PubSubPublisherTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.reactive.result.view.RedirectView;

/**
 * A helper controller for sending Pub/Sub messages, which can then be received by {@link
 * ReactiveController}.
 *
 * @since 1.2
 */
@Controller
public class MessageSenderController {

  @Autowired PubSubPublisherTemplate pubSubPublisherTemplate;

  @PostMapping("/postMessage")
  public RedirectView publish(@ModelAttribute("message") Message message) {

    for (int i = 0; i < message.count; i++) {
      this.pubSubPublisherTemplate.publish("topic-one", message.message + " " + i);
    }

    return new RedirectView("/?statusMessage=Published");
  }

  /** Convenience class encapsulating the form field values needed for publishing. */
  public static class Message {

    String message;
    int count;

    public Message(String message, int count) {
      this.message = message;
      this.count = count;
    }
  }
}