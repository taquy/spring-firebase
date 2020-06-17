package fpt.fbiz.fremote.entities;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Collection;
import java.util.Date;
import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "requests")
@Entity(name = "requests")
public class Request extends BaseEntity {
    @Column
    private String reason;

    @Column
    private User employee;

    @Column
    @JsonProperty(value="employee_id")
    private long employeeId;

    @Column
    @JsonProperty(value="working_date")
    private Date workingDate;

    @Column
    @JsonProperty(value="accepted_date")
    private Date acceptedDate;

    @Column
    @JsonProperty(value="is_accepted")
    private boolean isAccepted = false;

    @Column
    @JsonProperty(value="check_in_time")
    private int checkInTime;
}
