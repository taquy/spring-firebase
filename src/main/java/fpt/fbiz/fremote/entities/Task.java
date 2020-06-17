package fpt.fbiz.fremote.entities;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Lob;
import javax.persistence.Table;
import java.util.Date;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "tasks")
@Entity(name = "tasks")
public class Task extends BaseEntity {
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
