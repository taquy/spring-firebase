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
class TaskUser extends BaseEntity {
    @OneToOne(cascade = CascadeType.ALL)
    @MapsId("task_id")
    @JoinColumn(name = "task_id")
    private Task task;

    @OneToOne(cascade = CascadeType.ALL)
    @MapsId("user_id")
    @JoinColumn(name = "user_id")
    private User employee;

}