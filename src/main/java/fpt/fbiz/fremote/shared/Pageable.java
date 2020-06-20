package fpt.fbiz.fremote.shared;

import org.springframework.data.domain.Sort;

public interface Pageable {
    int getPageNumber();
    int getPageSize();
    Sort getSort();
}