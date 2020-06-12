package space.yjeong.yourday.domain.diary;

import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

@Getter
@NoArgsConstructor
public class Diary {
    private Long id;
    private String content;
    private LocalDateTime date;
    private Long categoryId;
    private String userEmail;

    public Diary(Long id, String content, LocalDateTime date, Long categoryId, String userEmail) {
        this.id = id;
        this.content = content;
        this.date = date;
        this.categoryId = categoryId;
        this.userEmail= userEmail;
    }

    public String toFileData(){
        return id.toString() + "\n"
                + content + "\n"
                + date.format(DateTimeFormatter.ofPattern("yyyy-MM-dd"))+ "\n"
                + categoryId.toString() + "\n"
                + userEmail + "\n";
    }

}
