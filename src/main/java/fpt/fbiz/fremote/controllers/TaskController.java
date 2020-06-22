package fpt.fbiz.fremote.controllers;

import fpt.fbiz.fremote.dtos.TaskUserAssignDto;
import fpt.fbiz.fremote.entities.Task;
import fpt.fbiz.fremote.facades.AuthFacade;
import fpt.fbiz.fremote.repositories.TaskRepository;
import fpt.fbiz.fremote.services.TaskService;
import fpt.fbiz.fremote.shared.ApiResponse;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("private/tasks")
class TaskController extends BaseController<Task, TaskRepository, TaskService> {

    private final
    TaskService service;

    public TaskController(TaskService service, AuthFacade authFacade) {
        super(service, authFacade);
        this.service = service;
    }

    @GetMapping("users/{user_id}")
    public ApiResponse listTasksByEmployee(
            @PathVariable("user_id") Long userId,
            @RequestParam(defaultValue = "0") Integer pageNo,
            @RequestParam(defaultValue = "5") Integer pageSize
    ) {
        try {
            Pageable page = PageRequest.of(pageNo, pageSize);
            var results = service.listTasksByUser(userId, page);
            return ApiResponse.success(results);
        } catch (Exception ex) {
            return ApiResponse.error(ex.getMessage());
        }
    }

    @PostMapping("{id}/assign-users")
    public ApiResponse assignUsers(
            @PathVariable("id") Long taskId,
            @RequestBody TaskUserAssignDto dto
    ) {
        try {
            var result = service.assignUsers(taskId, dto);
            return ApiResponse.success(result);
        } catch (Exception ex) {
            return ApiResponse.error(ex.getMessage());
        }
    }
}
