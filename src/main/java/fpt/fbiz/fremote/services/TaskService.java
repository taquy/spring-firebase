package fpt.fbiz.fremote.services;

import fpt.fbiz.fremote.dtos.TaskUserAssignDto;
import fpt.fbiz.fremote.entities.Task;
import fpt.fbiz.fremote.entities.TaskUser;
import fpt.fbiz.fremote.entities.TaskUserKey;
import fpt.fbiz.fremote.repositories.TaskRepository;
import fpt.fbiz.fremote.repositories.TaskUserRepository;
import fpt.fbiz.fremote.repositories.UserRepository;
import fpt.fbiz.fremote.shared.CustomPager;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Arrays;

@Service
public class TaskService extends BaseService<Task, TaskRepository> {

    private final
    UserRepository userRepository;

    private final
    TaskUserRepository taskUserRepository;

    private final
    TaskRepository taskRepository;

    public TaskService(
            TaskRepository repository, UserRepository userRepository, TaskUserRepository taskUserRepository
    ) {
        super(repository);
        this.userRepository = userRepository;
        this.taskUserRepository = taskUserRepository;
        this.taskRepository = repository;
    }

    public CustomPager listTasksByUser(
            Long userId, Pageable pageable
    ) throws Exception {
        var userOpt = userRepository.findById(userId);
        if (!userOpt.isPresent()) {
            throw new Exception("User not found");
        }

        var results = taskUserRepository.findAllByEmployeeId(userId, pageable);
        return CustomPager.load(results.map(TaskUser::getTask));
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

        var records = new ArrayList<TaskUser>();
        users.forEach(user -> {
            var record = new TaskUser();
            var key = new TaskUserKey(task.getId(), user.getId());
            record.setId(key);
            records.add(record);
        });

        taskUserRepository.saveAll(records);

        return show(taskId);
    }
}
