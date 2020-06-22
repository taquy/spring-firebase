package fpt.fbiz.fremote.entities;

import lombok.AllArgsConstructor;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import java.io.Serializable;

@Embeddable
@AllArgsConstructor
public class TaskUserKey implements Serializable {
    @Column(name = "task_id")
    Long taskId;

    @Column(name = "employee_id")
    Long employeeId;
}
