package fpt.fbiz.fremote.services;

import fpt.fbiz.fremote.entities.BaseEntity;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

@RequiredArgsConstructor
public class BaseService<T extends BaseEntity, R extends JpaRepository<T, Long>> {
    private final R repository;

    public Page<T> list(Pageable pageable) {
        return repository.findAll(pageable);
    }

    public T show(long id) {
        var result = repository.findById(id);
        return result.orElse(null);
    }

    public void delete(long id) {
        repository.deleteById(id);
    }

    public T createOrUpdate(T item) {
        if (item.getId() != null) {
            var oldItem = show(item.getId());
            if (oldItem == null) {
                return null;
            }
        }
        return repository.save(item);
    }
}
