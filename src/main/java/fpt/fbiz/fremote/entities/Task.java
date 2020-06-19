package fpt.fbiz.fremote.entities;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.Date;
import java.util.Set;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "tasks")
@Entity(name = "tasks")
public class Task extends BaseEntity {

    @OneToMany(mappedBy = "task")
    Set<RequestTask> requestTasks;

    @Column
    private String title;

    @Column
    private User employee;

    @Column
    private long employeeId;

    @Column
    @Lob
    private String description;
}
