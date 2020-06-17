package fpt.fbiz.fremote.entities;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import java.util.Date;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "comments")
@Entity(name = "comments")
public class Comment extends BaseEntity {
    @Column
    private Request request;

    @Column
    @JsonProperty(value = "request_id")
    private long requestId;

    @Column
    private User employee;

    @Column
    @JsonProperty(value = "employee_id")
    private long employeeId;

    @Column
    private String content;
}
