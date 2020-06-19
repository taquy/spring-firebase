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
@Table(name = "request_task")
@Entity(name = "request_task")
public class RequestTask extends BaseEntity {
    @Column
    private String note;

    @Column
    private String status;

    @OneToOne(cascade = CascadeType.ALL)
    @MapsId("task_id")
    @JoinColumn(name = "task_id")
    private Task task;

    @OneToOne(cascade = CascadeType.ALL)
    @MapsId("request_id")
    @JoinColumn(name = "request_id")
    private Request request;

}