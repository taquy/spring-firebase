package fpt.fbiz.fremote.entities;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import java.util.Date;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "check_in")
@Entity(name = "check_in")
public class CheckIn extends BaseEntity {
    @Column
    private Request request;

    @Column
    @JsonProperty(value = "request_id")
    private long requestId;

    @Column
    @JsonProperty(value = "check_time")
    private Date checkTime;

    @Column
    private String image;

    @Column
    private String status;
}
