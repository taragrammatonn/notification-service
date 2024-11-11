package md.maib.mail.notification.service.consumer;

import md.maib.mail.notification.service.model.EventModel;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.stereotype.Component;

@Component
public class NotificationConsumer {

    private static final Logger log = LoggerFactory.getLogger(NotificationConsumer.class);

    @RabbitListener(queues = "notifications")
    public void receiveNotification(@Payload EventModel notification) {
        log.info("Received notification: {}", notification);
    }
}
