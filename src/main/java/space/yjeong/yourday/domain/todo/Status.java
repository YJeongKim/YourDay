package space.yjeong.yourday.domain.todo;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum Status {

    TODO("STATUS_TODO", "진행예정"),
    DOING("STATUS_DOING", "진행중"),
    DONE("STATUS_DONE", "진행완료");

    private final String key;
    private final String title;

}
