package space.yjeong.yourday.domain.todo;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ToDo {
    private Long id;
    private String content;
    private LocalDate date;
    private Status status;
    private Long userId;

    public void update(String content, LocalDate date) {
        this.content = content;
        this.date = date;
    }
}
