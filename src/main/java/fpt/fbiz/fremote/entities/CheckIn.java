package fpt.fbiz.fremote.entities;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.Date;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "check_in")
@Entity(name = "check_in")
public class CheckIn extends BaseEntity {

    @ManyToOne
    @JoinColumn(name = "request_id", referencedColumnName = "id")
    private Request request;

    @Column
    @JsonProperty(value = "check_time")
    private Date checkTime;

    @Column
    private String image;

    @Column
    private String status;
}
