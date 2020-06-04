package space.yjeong.yourday.model.todo;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

@Getter
@NoArgsConstructor
@AllArgsConstructor
public class ToDo {
    private Long id;
    private String content;
    private LocalDateTime date;
    private Status status;

    public void update(String content, LocalDateTime date, Status status) {
        this.content = content;
        this.date = date;
        this.status = status;
    }

    public String toFileData() {
        return id.toString() + "\n"
                + content + "\n"
                + date.format(DateTimeFormatter.ofPattern("yyyy-MM-dd"))+ "\n"
                + status.toString() + "\n";
    }

}
