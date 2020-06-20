package fpt.fbiz.fremote.controllers;

import fpt.fbiz.fremote.entities.Task;
import fpt.fbiz.fremote.facades.AuthFacade;
import fpt.fbiz.fremote.repositories.TaskRepository;
import fpt.fbiz.fremote.services.TaskService;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("private/tasks")
class TaskController extends BaseController<Task, TaskRepository, TaskService> {

    public TaskController(TaskService service, AuthFacade authFacade) {
        super(service, authFacade);
    }
}
