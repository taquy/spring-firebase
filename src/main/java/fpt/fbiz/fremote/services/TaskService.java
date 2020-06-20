package fpt.fbiz.fremote.services;

import fpt.fbiz.fremote.dtos.TaskUserAssignDto;
import fpt.fbiz.fremote.entities.Task;
import fpt.fbiz.fremote.entities.TaskUser;
import fpt.fbiz.fremote.repositories.TaskRepository;
import fpt.fbiz.fremote.repositories.TaskUserRepository;
import fpt.fbiz.fremote.repositories.UserRepository;
import fpt.fbiz.fremote.shared.ApiResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.ArrayList;
import java.util.Arrays;

@Service
public class TaskService extends BaseService<Task, TaskRepository> {

    private final
    UserRepository userRepository;

    private final
    TaskUserRepository taskUserRepository;

    public TaskService(TaskRepository repository, UserRepository userRepository, TaskUserRepository taskUserRepository) {
        super(repository);
        this.userRepository = userRepository;
        this.taskUserRepository = taskUserRepository;
    }

    public Task assignUsers(
            Long taskId,
            TaskUserAssignDto dto
    ) throws Exception {
        var task = show(taskId);
        if (task == null) {
            throw new Exception("Task not found");
        }

        var ids = Arrays.asList(dto.getUsersIds());
        var users = userRepository.findByIds(ids);

        for (var user : users) {
            var record = new TaskUser(task, user);
            taskUserRepository.save(record);
        }

//        users.parallelStream().forEach(user -> taskUserRepository.save(new TaskUser(task, user)));

        return show(taskId);
    }
}
