package controller;
import model.Task;
import service.TaskService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.util.Map;

import static org.hamcrest.Matchers.is;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
@WebMvcTest(TaskController.class)
public class TaskControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private TaskService taskService;

    private ObjectMapper objectMapper;
    private Task sampleTask;

    @BeforeEach
    public void setup() {
        objectMapper = new ObjectMapper();
        sampleTask = new Task();
        sampleTask.setId(1L);
        sampleTask.setTitle("Test Title");
        sampleTask.setDescription("Test Description");
        sampleTask.setCompleted(false);
    }

    @Test
    public void shouldCreateTaskAndReturnJson() throws Exception {
        Map<String, String> payload = Map.of(
                "title", "Test Title",
                "description", "Test Description"
        );

        Mockito.when(taskService.createTask("Test Title", "Test Description")).thenReturn(sampleTask);

        mockMvc.perform(post("/tasks")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(payload)))
                .andExpect(status().isCreated())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.id",  is(1)))
                .andExpect(jsonPath("$.title", is("Test Title")))
                .andExpect(jsonPath("$.description", is("Test Description")))
                .andExpect(jsonPath("$.completed", is(false)));
    }
}