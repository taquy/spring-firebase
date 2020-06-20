package fpt.fbiz.fremote.dtos;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode
@AllArgsConstructor
public class TaskUserAssignDto {
    private Long id;
    private String title;
    private String description;
}
