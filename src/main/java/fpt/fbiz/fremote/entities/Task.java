package fpt.fbiz.fremote.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
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

    @JsonIgnore
    @OneToMany(mappedBy = "task", cascade = CascadeType.REMOVE)
    Set<TaskRequest> requestTasks;

    @JsonIgnore
    @OneToMany(mappedBy = "task", cascade = CascadeType.REMOVE)
    Set<TaskUser> userTasks;

    @Column
    private String title;

    @Column
    @Lob
    private String description;
}
