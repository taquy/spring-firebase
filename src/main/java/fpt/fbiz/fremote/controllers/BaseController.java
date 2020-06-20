package fpt.fbiz.fremote.controllers;

import fpt.fbiz.fremote.entities.BaseEntity;
import fpt.fbiz.fremote.facades.AuthFacade;
import fpt.fbiz.fremote.services.BaseService;
import fpt.fbiz.fremote.shared.ApiResponse;
import fpt.fbiz.fremote.shared.CustomPager;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.web.PageableDefault;
import org.springframework.data.web.SortDefault;
import org.springframework.web.bind.annotation.*;


@RequiredArgsConstructor
public class BaseController<T extends BaseEntity, R extends JpaRepository<T, Long>, S extends BaseService<T, R>> {

    private final S service;
    private final AuthFacade authFacade;

    @GetMapping()
    public ApiResponse list(
            @PageableDefault(page = 0, size = 5)
            @SortDefault.SortDefaults({
                    @SortDefault(sort = "createdAt", direction = Sort.Direction.DESC),
                    @SortDefault(sort = "id", direction = Sort.Direction.DESC)
            }) Pageable pageable
    ) {
        var result = service.list(pageable);
        return ApiResponse.success(CustomPager.load(result));
    }

    @GetMapping("{item_id}")
    public ApiResponse show(@PathVariable("item_id") long id) {
        return ApiResponse.success(service.show(id));
    }

    @DeleteMapping("{item_id}")
    public ApiResponse delete(@PathVariable("item_id") long id) {
        service.delete(id);
        return ApiResponse.success();
    }

    @PostMapping()
    public ApiResponse createOrUpdate(@RequestBody T item) {
        var editor = authFacade.getAuthUser();
        var result = service.createOrUpdate(item, editor);
        return ApiResponse.success(result);
    }
}
