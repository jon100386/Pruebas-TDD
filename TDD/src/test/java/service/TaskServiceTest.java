package service;

import model.Task;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import repository.TaskRepository;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class TaskServiceTest {
    @Mock
    private TaskRepository repository;

    @InjectMocks
    private TaskService service;

    @Test
    public void testCreateTask() {
        Task task = new Task();
        task.setId(1L);
        task.setTitle("Test");
        task.setDescription("Desc");

        when(repository.save(any(Task.class))).thenReturn(task);

        Task created = service.createTask("Test", "Desc");

        assertEquals("Test", created.getTitle());
        assertFalse(created.isCompleted());
    }
}