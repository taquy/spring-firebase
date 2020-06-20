package fpt.fbiz.fremote.controllers;

import fpt.fbiz.fremote.entities.BaseEntity;
import fpt.fbiz.fremote.services.BaseService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.web.PageableDefault;
import org.springframework.data.web.SortDefault;
import org.springframework.web.bind.annotation.*;

@RequiredArgsConstructor
public class BaseController<T extends BaseEntity, R extends JpaRepository<T, Long>, S extends BaseService<T, R>> {

    private final S service;

    @GetMapping()
    public Page list(
            @PageableDefault(page = 0, size = 5)
            @SortDefault.SortDefaults({
                    @SortDefault(sort = "created_at", direction = Sort.Direction.DESC),
                    @SortDefault(sort = "id", direction = Sort.Direction.ASC)
            }) Pageable pageable
    ) {
        return service.list(pageable);
    }

    @GetMapping("{item_id}")
    public T show(@PathVariable("item_id") long id) {
        return service.show(id);
    }

    @DeleteMapping("{item_id}")
    public void delete(@PathVariable("item_id") long id) {
        service.delete(id);
    }

    @PostMapping()
    public T createOrUpdate(@RequestBody T item) {
        return service.createOrUpdate(item);
    }
}
