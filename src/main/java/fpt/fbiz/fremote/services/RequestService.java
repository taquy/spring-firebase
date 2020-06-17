package fpt.fbiz.fremote.services;

import fpt.fbiz.fremote.entities.Request;
import fpt.fbiz.fremote.repositories.RequestRepository;
import org.springframework.stereotype.Service;

@Service
public class RequestService extends BaseService<Request, RequestRepository> {
    public RequestService(RequestRepository repository) {
        super(repository);
    }
}
