package md.maib.mail.notification.service.consumer;

import md.maib.mail.notification.service.model.EventModel;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


class NotificationConsumerTest {

    private static final Logger log = LoggerFactory.getLogger(NotificationConsumer.class);

    @Test
    void testReceiveNotification() {
        EventModel mockNotification = new EventModel("John", "Doe", 30, "Male");

        NotificationConsumer consumer = new NotificationConsumer();
        consumer.receiveNotification(mockNotification);

        log.info("Simulated log output: {}", mockNotification);
    }

}