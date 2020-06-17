package fpt.fbiz.fremote.controllers;

import fpt.fbiz.fremote.entities.BaseEntity;
import fpt.fbiz.fremote.services.BaseService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class BaseController<T extends BaseEntity, R extends JpaRepository<T, Long>, S extends BaseService<T, R>> {

    private final S service;

    public Page list(Pageable pageable) {
        return service.list(pageable);
    }

    public T show(long id) {
        return service.show(id);
    }

    public void delete(long id) {
        service.delete(id);
    }

    public T createOrUpdate(T item) {
        return service.createOrUpdate(item);
    }
}
