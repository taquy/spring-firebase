package fpt.fbiz.fremote.controllers;

import fpt.fbiz.fremote.dtos.TaskUserAssignDto;
import fpt.fbiz.fremote.entities.Task;
import fpt.fbiz.fremote.facades.AuthFacade;
import fpt.fbiz.fremote.repositories.TaskRepository;
import fpt.fbiz.fremote.services.TaskService;
import fpt.fbiz.fremote.shared.ApiResponse;
import org.springframework.beans.factory.annotation.Autowired;
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

    @PostMapping("{id}/assign-users")
    public ApiResponse assignUsers(
            @PathVariable("id") Long taskId,
            @RequestBody TaskUserAssignDto dto
    ) throws Exception {
//        try {
        var result = service.assignUsers(taskId, dto);
        return ApiResponse.success(result);
//        } catch (Exception ex) {
//            return ApiResponse.error(ex.getMessage());
//        }
    }
}
