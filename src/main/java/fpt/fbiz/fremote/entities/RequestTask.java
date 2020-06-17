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
@Table(name = "request_task")
@Entity(name = "request_task")
public class RequestTask extends BaseEntity {
    @Column
    private String note;

    @Column
    private String status;

    @Column
    private Task task;

    @Column
    @JsonProperty(value = "task_id")
    private long taskId;

    @Column
    private Request request;

    @Column
    @JsonProperty(value = "request_id")
    private long requestId;
}