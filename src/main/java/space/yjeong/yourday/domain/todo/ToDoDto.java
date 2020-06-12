package space.yjeong.yourday.domain.todo;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ToDoDto {
    private Long id;
    private String content;
    private String status;

    @Override
    public String toString() {
        return "(content='" + content + '\'' +
                ", status='" + status + ')';
    }
}
