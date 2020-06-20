package fpt.fbiz.fremote.entities;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.Setter;
import org.springframework.data.annotation.CreatedBy;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedBy;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.*;
import java.util.Collections;
import java.util.Date;
import java.util.Map;

@Getter
@MappedSuperclass
@EntityListeners(AuditingEntityListener.class)
@SequenceGenerator(name = "seq", initialValue = 1, allocationSize = 100)
public class BaseEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "seq")
    public Long id;

    public Map<String, Object> toMap() {
        var map = new ObjectMapper().convertValue(this, Map.class);
        map.values().removeAll(Collections.singleton(null));
        return map;
    }

    @CreatedBy
    @Column(name = "created_by")
    @JsonProperty(value="created_by")
    private User createdBy;

    @CreatedDate
    @Column(name = "created_date")
    @JsonProperty(value="created_date")
    private Date createdDate;

    @LastModifiedBy
    @Column(name = "last_modified_by")
    @JsonProperty(value="last_modified_by")
    private User lastModifiedBy;

    @LastModifiedDate
    @Column(name = "last_modified_date")
    @JsonProperty(value="last_modified_date")
    private Date lastModifiedDate;
}
