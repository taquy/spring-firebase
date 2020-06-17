package fpt.fbiz.fremote.entities;

import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.Setter;
import org.springframework.data.annotation.CreatedBy;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedBy;
import org.springframework.data.annotation.LastModifiedDate;

import javax.persistence.*;
import java.util.Date;
import java.util.Map;

@Entity
@Setter(AccessLevel.PUBLIC)
@Getter(AccessLevel.PUBLIC)
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@SequenceGenerator(name = "seq", initialValue = 1, allocationSize = 100)
public class BaseEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "seq")
    long id;

    public Map<String, Object> toMap() {
        return new ObjectMapper().convertValue(this, Map.class);
    }

    @CreatedBy
    @Column(name = "created_by")
    private User createdBy;

    @CreatedDate
    @Column(name = "created_date")
    private Date createdDate;

    @LastModifiedBy
    @Column(name = "last_modified_by")
    private User lastModifiedBy;

    @LastModifiedDate
    @Column(name = "last_modified_date")
    private Date lastModifiedDate;
}
