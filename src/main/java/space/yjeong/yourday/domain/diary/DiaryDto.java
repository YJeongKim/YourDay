package space.yjeong.yourday.domain.diary;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class DiaryDto {
    private String content;
    private String date;

    @Override
    public String toString() {
        return "content='" + content + '\'' +
                ", date=" + date;
    }
}
