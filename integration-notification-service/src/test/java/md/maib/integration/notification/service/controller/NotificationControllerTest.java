package md.maib.integration.notification.service.controller;

import md.maib.integration.notification.service.model.EventModel;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;

@WebMvcTest(NotificationController.class)
class NotificationControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private RabbitTemplate rabbitTemplate;

    @Test
    void sendNotification_ShouldSendEventToRabbitMQ() throws Exception {
        // Correct JSON payload
        String eventJson = """
            {
              "name": "John",
              "surname": "Doe",
              "age": 30,
              "gender": "Male"
            }
            """;

        EventModel eventModel = new EventModel("John", "Doe", 30, "Male");

        Mockito.doNothing().when(rabbitTemplate).convertAndSend(Mockito.eq("notifications"), Mockito.any(EventModel.class));

        mockMvc.perform(post("/api/notifications/send")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(eventJson))
                .andExpect(status().isOk())
                .andExpect(content().string("Notification sent to RabbitMQ successfully!"));

        Mockito.verify(rabbitTemplate, Mockito.times(1)).convertAndSend(Mockito.eq("notifications"), Mockito.refEq(eventModel));
    }

}
