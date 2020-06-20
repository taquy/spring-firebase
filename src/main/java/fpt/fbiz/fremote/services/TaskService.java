package fpt.fbiz.fremote.services;

import fpt.fbiz.fremote.entities.Task;
import fpt.fbiz.fremote.repositories.TaskRepository;
import org.springframework.stereotype.Service;

@Service
public class TaskService extends BaseService<Task, TaskRepository> {
    public TaskService(TaskRepository repository) {
        super(repository);
    }
}
