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
class TaskUser extends BaseEntity {
    @OneToOne(cascade = CascadeType.ALL)
    @MapsId("id")
    @JoinColumn(name = "task_id")
    private Task task;

    @OneToOne(cascade = CascadeType.ALL)
    @MapsId("id")
    @JoinColumn(name = "user_id")
    private User employee;

}