package fpt.fbiz.fremote.entities;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "task_user")
@Entity(name = "task_user")
public
class TaskUser {

    @EmbeddedId
    private TaskUserKey id;

    @ManyToOne
    @MapsId("task_id")
    @JoinColumn(name = "task_id")
    private Task task;

    @ManyToOne
    @MapsId("employee_id")
    @JoinColumn(name = "employee_id")
    private User employee;

}