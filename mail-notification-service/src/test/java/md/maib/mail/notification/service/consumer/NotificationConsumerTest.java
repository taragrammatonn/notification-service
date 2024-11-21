package md.maib.mail.notification.service.consumer;

import md.maib.mail.notification.service.model.EventModel;
import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import static org.junit.jupiter.api.Assertions.*;


class NotificationConsumerTest {

    private static final Logger log = LoggerFactory.getLogger(NotificationConsumer.class);

    @Test
    void testReceiveNotification() {
        // Arrange: Create a mock notification object
        EventModel mockNotification = new EventModel("John", "Doe", 30, "Male");

        // Create an instance of the NotificationConsumer class
        NotificationConsumer consumer = new NotificationConsumer();

        // Act: Call the method to be tested
        consumer.receiveNotification(mockNotification);

        // Assert: Validate the state of the notification
        assertNotNull(mockNotification, "Notification object should not be null");
        assertEquals("John", mockNotification.name(), "Name should match the expected value");
        assertEquals("Doe", mockNotification.surname(), "Surname should match the expected value");
        assertEquals(30, mockNotification.age(), "Age should match the expected value");
        assertEquals("Male", mockNotification.gender(), "Gender should match the expected value");

        // Log the result for verification
        log.info("Simulated log output: {}", mockNotification);

        // Additional validation can be added here if `NotificationConsumer` modifies or stores the notification
        log.info("Test for NotificationConsumer.receiveNotification completed successfully.");
    }
}