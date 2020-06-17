package fpt.fbiz.fremote.controllers;

import fpt.fbiz.fremote.entities.Request;
import fpt.fbiz.fremote.repositories.RequestRepository;
import fpt.fbiz.fremote.services.RequestService;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("public/requests")
public class RequestController extends BaseController<Request, RequestRepository, RequestService> {
    public RequestController(RequestService service) {
        super(service);
    }
}
