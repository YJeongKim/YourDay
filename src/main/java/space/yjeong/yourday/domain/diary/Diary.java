package space.yjeong.yourday.domain.diary;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Diary {
    private Long id;
    private String content;
    private LocalDate date;
    private int categoryId;
    private Long userId;

    public void update(String content, LocalDate date) {
        this.content = content;
        this.date = date;
    }
}
