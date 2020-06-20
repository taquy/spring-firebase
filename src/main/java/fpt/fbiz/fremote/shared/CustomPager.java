package fpt.fbiz.fremote.shared;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.Setter;
import org.springframework.data.domain.Page;

import java.util.List;

@Getter
@Setter
@JsonInclude(JsonInclude.Include.NON_NULL)
public class CustomPager {

    @JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
    List content;

    @JsonProperty("total_pages")
    long totalPages;

    @JsonProperty("total_items")
    long totalItems;

    @JsonProperty("page_number")
    long pageNumber;

    @JsonProperty("page_size")
    long pageSize;

    @JsonCreator(mode = JsonCreator.Mode.PROPERTIES)
    public static CustomPager load(Page page) {
        var pager = new CustomPager();

        pager.setTotalItems(page.getTotalElements());
        pager.setContent(page.getContent());
        pager.setTotalPages(page.getTotalPages());

        var pageable = page.getPageable();
        pager.setPageNumber(pageable.getPageNumber());
        pager.setPageSize(pageable.getPageSize());

        return pager;
    }

}
