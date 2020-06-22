package fpt.fbiz.fremote.entities;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import java.io.Serializable;

@Embeddable
@NoArgsConstructor
@AllArgsConstructor
public class TaskUserKey implements Serializable {
    @Column(name = "task_id")
    Long taskId;

    @Column(name = "employee_id")
    Long employeeId;
}
