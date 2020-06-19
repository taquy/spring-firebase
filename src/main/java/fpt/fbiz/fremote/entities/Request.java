package fpt.fbiz.fremote.entities;

import com.fasterxml.jackson.annotation.JsonProperty;
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
@Table(name = "requests")
@Entity(name = "requests")
public class Request extends BaseEntity {
    @Column
    private String reason;

    @ManyToOne
    @JoinColumn(name = "employee_id", referencedColumnName = "id")
    private User employee;

    @OneToMany(mappedBy = "request")
    private Set<CheckIn> checkIns;

    @OneToMany(mappedBy = "request")
    Set<RequestTask> requestTasks;

    @Column
    @JsonProperty(value = "working_date")
    private Date workingDate;

    @Column
    @JsonProperty(value = "accepted_date")
    private Date acceptedDate;

    @Column
    @JsonProperty(value = "is_accepted")
    private boolean isAccepted = false;

    @Column
    @JsonProperty(value = "check_in_time")
    private int checkInTime;
}
