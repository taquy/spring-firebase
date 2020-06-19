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
@Table(name = "comments")
@Entity(name = "comments")
public class Comment extends BaseEntity {

    @ManyToOne
    @JoinColumn(name = "request_id", referencedColumnName = "id")
    private Request request;

    @ManyToOne
    @JoinColumn(name = "employee_id", referencedColumnName = "id")
    private User employee;

    @Column
    private String content;
}
