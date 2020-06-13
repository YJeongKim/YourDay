package space.yjeong.yourday.domain.user;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class User {
    private Long id;
    private String email;
    private String password;
    private String name;
    private LocalDate birthday;
    private String phone;

    public void update(String password, LocalDate birthday, String phone) {
        this.password = password;
        this.birthday = birthday;
        this.phone = phone;
    }
}