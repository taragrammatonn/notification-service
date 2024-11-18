package md.maib.gatewaynotificationservice.config;

import okhttp3.mockwebserver.MockResponse;
import okhttp3.mockwebserver.MockWebServer;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.Import;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.io.IOException;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;

@SpringBootTest
@AutoConfigureMockMvc
@Import(RoutesConfig.class) // Import the RoutesConfig for testing
class RoutesConfigIntegrationTest {

    @Autowired
    private MockMvc mockMvc;

    private MockWebServer mockWebServer;

    @BeforeEach
    void setUp() throws IOException {
        // Start a MockWebServer to simulate the downstream service
        mockWebServer = new MockWebServer();
        mockWebServer.start(8080); // Ensure it runs on port 8080 as in the RoutesConfig
    }

    @AfterEach
    void tearDown() throws IOException {
        // Shut down the MockWebServer
        mockWebServer.shutdown();
    }

    @Test
    void notificationRouteService_RouteMatch() throws Exception {
        // Set up a mock response for the downstream service
        mockWebServer.enqueue(new MockResponse()
                .setBody("Mocked Response")
                .setResponseCode(200));

        // Perform the POST request to the gateway route
        mockMvc.perform(post("/api/notifications/send")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("{\"message\": \"Hello\"}")) // Example payload
                .andExpect(status().isOk())
                .andExpect(content().string("Mocked Response")); // Assert the downstream response

        // Verify the request was forwarded to the downstream service
        var recordedRequest = mockWebServer.takeRequest();
        assert recordedRequest.getPath().equals("/api/notifications/send");
        assert recordedRequest.getMethod().equals("POST");
        assert recordedRequest.getBody().readUtf8().equals("{\"message\": \"Hello\"}");
    }

}
