package space.yjeong.yourday;

import org.springframework.context.support.AbstractApplicationContext;
import org.springframework.context.support.GenericXmlApplicationContext;
import space.yjeong.yourday.domain.diary.Diary;
import space.yjeong.yourday.domain.diary.DiaryDto;
import space.yjeong.yourday.domain.todo.ToDo;
import space.yjeong.yourday.domain.todo.ToDoDto;
import space.yjeong.yourday.domain.user.User;
import space.yjeong.yourday.exception.*;
import space.yjeong.yourday.service.DiaryService;
import space.yjeong.yourday.service.impl.DiaryServiceImpl;
import space.yjeong.yourday.service.ToDoService;
import space.yjeong.yourday.service.UserService;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.InputMismatchException;
import java.util.List;
import java.util.Scanner;

public class Main {
    public static final int END = -1;
    public static final int START = 0;
    public static final int LOGIN = 1;
    public static final int JOIN = 2;
    public static final int MAIN = 3;
    public static final int TODO = 4;
    public static final int TODO_WRITE = 5;
    public static final int TODO_READ = 6;
    public static final int TODO_UPDATE = 7;
    public static final int TODO_DELETE = 8;
    public static final int TODO_LIST = 9;
    public static final int DIARY = 10;
    public static final int DIARY_WRITE = 11;
    public static final int DIARY_READ = 12;
    public static final int DIARY_UPDATE = 13;
    public static final int DIARY_DELETE = 14;
    public static final int DIARY_LIST = 15;

    public static Long loginUser = null;
    private static UserService userService;
    private static ToDoService todoService = new ToDoService();
    private static DiaryService diaryService;
    private static Scanner sc = new Scanner(System.in);

    public static void main(String[] args) {
        AbstractApplicationContext ctx = new GenericXmlApplicationContext("applicationContext.xml");
        userService = (UserService) ctx.getBean("userService");
//        todoService = (ToDoService) ctx.getBean("todoService");
        diaryService = (DiaryService) ctx.getBean("diaryService");

        while (true) {
            menuStart();
        }
    }

    public static void move(int menu) {
        switch (menu) {
            case END:
                System.out.println("프로그램을 종료합니다.");
                System.exit(0);
            case START:
                menuStart();
                break;
            case LOGIN:
                login();
                break;
            case JOIN:
                join();
                break;
            case MAIN:
                menuMain();
                break;
            case TODO:
                menuToDo();
                break;
            case DIARY:
                menuDiary();
                break;
            case TODO_WRITE:
                todoWrite();
                break;
            case TODO_LIST:
                todoList();
                break;
            case DIARY_WRITE:
                diaryWrite();
                break;
            case DIARY_LIST:
                diaryList();
                break;
        }
    }

    public static void menuStart() {
        int menuNum;
        while (true) {
            System.out.println("[*************** YOUR DAY ***************]");
            System.out.println(" - 1. 로그인\n - 2. 회원가입\n - 3. 시스템 종료");
            menuNum = inputMenuNumber(3, "Input menu number : ");
            switch (menuNum) {
                case 1:
                    move(LOGIN);
                    break;
                case 2:
                    move(JOIN);
                    break;
                case 3:
                    move(END);
                    break;
            }
        }
    }

    public static void menuMain() {
        int menuNum;
        while (true) {
            System.out.println("[*----------- YOUR DAY MENU -----------*]");
            System.out.println(" - 1. TODO\n - 2. 다이어리\n - 3. 뒤로가기");
            menuNum = inputMenuNumber(3, "Input menu number : ");
            switch (menuNum) {
                case 1:
                    move(TODO);
                    break;
                case 2:
                    move(DIARY);
                    break;
                case 3:
                    loginUser = null;
                    move(START);
                    break;
            }
        }
    }

    public static void menuToDo() {
        int menuNum;
        while (true) {
            System.out.println("[*-------------- ToDo MENU --------------*]");
            System.out.println(" - 1. 할 일 작성하기\n - 2. 할 일 조회하기\n - 3. 뒤로가기");
            menuNum = inputMenuNumber(3, "Input menu number : ");
            switch (menuNum) {
                case 1:
                    move(TODO_WRITE);
                    break;
                case 2:
                    move(TODO_LIST);
                    break;
                case 3:
                    move(MAIN);
                    break;
            }
        }
    }

    public static void menuToDoList(ToDoDto todo) {
        int menuNum;
        while (true) {
            System.out.println(" - 1. 할 일 수정하기\n - 2. 할 일 삭제하기\n - 3. 뒤로가기");
            menuNum = inputMenuNumber(3, "Input menu number : ");
            switch (menuNum) {
                case 1:
//                    todoUpdate(todo);
                    break;
                case 2:
//                    todoDelete(todo);
                    break;
                case 3:
                    return;
            }
        }
    }

    public static void menuDiary() {
        int menuNum;
        while (true) {
            System.out.println("[*-------------- DIARY MENU --------------*]");
            System.out.println(" - 1. 다이어리 작성하기\n - 2. 다이어리 조회하기\n - 3. 뒤로가기");
            menuNum = inputMenuNumber(3, "Input menu number : ");
            switch (menuNum) {
                case 1:
                    move(DIARY_WRITE);
                    break;
                case 2:
                    move(DIARY_LIST);
                    break;
                case 3:
                    move(MAIN);
                    break;
            }
        }
    }

    public static void menuToDoList(List<ToDoDto> todos, LocalDateTime date) {
        int menuNum;
        while (true) {
            System.out.println(" - 1. 할 일 수정하기\n - 2. 진행상태 수정하기\n - 3. 할 일 삭제하기\n - 4. 뒤로가기");
            menuNum = inputMenuNumber(4, "Input menu number : ");
            switch (menuNum) {
                case 1:
                    todoUpdate(todos, date);
                    break;
                case 2:
                    break;
                case 3:
                    todoDelete(todos, date);
                    break;
                case 4:
                    return;
            }
        }
    }

    public static void menuDiaryList(Diary diary) {
        int menuNum;
        while (true) {
            System.out.println(" - 1. 다이어리 수정하기\n - 2. 다이어리 삭제하기\n - 3. 뒤로가기");
            menuNum = inputMenuNumber(3, "Input menu number : ");
            switch (menuNum) {
                case 1:
                    diaryUpdate(diary);
                    break;
                case 2:
                    diaryDelete(diary);
                    break;
                case 3:
                    return;
            }
        }
    }

    public static void login() {
        String email;
        String password;

        sc.nextLine();
        System.out.println("---------------------------------------------");
        System.out.println("\t\t\t\t< 로그인 >");
        System.out.print(" EMAIL : ");
        email = sc.nextLine();
        System.out.print(" PASSWORD : ");
        password = sc.nextLine();

        try {
            userService.signIn(email, password);
            loginUser = userService.getByEmail(email).getId();
            System.out.println("로그인에 성공하셨습니다. 메인 화면으로 이동합니다.");
            move(MAIN);
        } catch (UserNotFoundException e) {
            System.out.println("등록된 회원 정보가 없습니다. 초기 화면으로 이동합니다.");
            move(START);
        } catch (PasswordNotMatchingException e) {
            System.out.println("비밀번호가 일치하지 않습니다. 초기 화면으로 이동합니다.");
            move(START);
        }
    }

    public static void join() {
        String email;
        String password;
        String name;
        LocalDate birth;
        String phone;

        sc.nextLine();
        System.out.println("----------------------------------------------");
        System.out.println("\t\t\t\t< 회원가입 >");
        System.out.print(" EMAIL : ");
        email = sc.nextLine();
        System.out.print(" PASSWORD : ");
        password = sc.nextLine();
        System.out.print(" 이름 : ");
        name = sc.nextLine();
        birth = inputDate(" 생일(0000-00-00) : ");
        System.out.print(" 전화번호(010-0000-0000) : ");
        phone = sc.nextLine();

        User user = new User(0L, email, password, name, birth, phone);

        try {
            userService.signUp(user);
            System.out.println("회원가입에 성공하셨습니다. 초기 화면으로 이동합니다.");
        } catch (EmailDuplicateException e) {
            System.out.println("중복된 이메일이 있어 회원가입에 실패하셨습니다. 초기 화면으로 이동합니다.");
        }
        move(START);
    }

    public static void todoWrite() {
        LocalDate date;
        String content;
        Long id;

        sc.nextLine();
        System.out.println("--------------------------------------------------------");
        System.out.println("\t\t\t\t\t< 할 일 추가하기 >");
        date = inputDate(" 진행날짜(0000-00-00) : ");
        System.out.print(" 할 일 : ");
        content = sc.nextLine();

//        id = Long.parseLong("" + todoService.getToDoCount(email, date)) + 1;
        ToDo todo = null;
//        new ToDo(id, content, date, Status.TODO);

        if (todoService.writeToDo(todo)) {
            System.out.println("할 일이 추가되었습니다. 할 일 메뉴 화면으로 이동합니다.");
            move(TODO);
        } else {
            System.out.println("할 일 추가가 실패되었습니다. 다시 할 일을 작성하세요.");
            move(TODO_WRITE);
        }
    }

    public static void todoUpdate(List<ToDoDto> todos, LocalDateTime d) {
        String content;
        int todoNum;
        Long id;
        ToDoDto todoDto;
        LocalDate date;

        sc.nextLine();
        System.out.println("--------------------------------------------------------");
        System.out.println("\t\t\t\t\t< 할 일 수정하기 >");
        todoNum = inputMenuNumber(todos.size(), " 수정할 할 일을 선택하세요 : ");
        todoDto = todos.get(todoNum - 1);
        sc.nextLine();
        date = inputDate(" 수정할 날짜(0000-00-00) : ");
        System.out.print(" 수정할 내용 : ");
        content = sc.nextLine();

//        if (todoService.deleteToDo(email, d, todoDto.getId())) {
//            id = Long.parseLong("" + todoService.getToDoCount(email, date)) + 1;
//            ToDo todo = null;
////            new ToDo(id, content, date, Status.TODO);
//            if (todoService.writeToDo(todo)) {
//                System.out.println("할 일 수정이 완료되었습니다. 할 일 메뉴 화면으로 이동합니다.");
//                move(TODO);
//            } else {
//                System.out.println("할 일 수정에 실패하셨습니다. 다시 할 일을 수정하세요.");
//                move(TODO_UPDATE);
//            }
//        } else {
//            System.out.println("할 일 수정에 실패하셨습니다. 할 일 메뉴 화면으로 이동합니다.");
//            move(TODO);
//        }
    }

    public static void todoDelete(List<ToDoDto> todos, LocalDateTime date) {
        int menuNum;
        int todoNum;
        ToDoDto todo;

        sc.nextLine();
        System.out.println("--------------------------------------------------------");
        System.out.println("\t\t\t\t\t< 할 일 삭제하기 >");
        todoNum = inputMenuNumber(todos.size(), "삭제할 할 일을 선택하세요 : ");
        todo = todos.get(todoNum - 1);
        System.out.println("정말 할 일을 삭제하시겠습니까?");
        System.out.println(" - 1. 네\n - 2. 아니오");
        menuNum = inputMenuNumber(2, "Input menu number : ");
//        switch (menuNum) {
//            case 1:
//                if (todoService.deleteToDo(email, date, todo.getId())) {
//                    System.out.println("할 일 삭제가 완료되었습니다. 할 일 메뉴 화면으로 이동합니다.");
//                } else {
//                    System.out.println("할 일 삭제에 실패하셨습니다. 할 일 메뉴 화면으로 이동합니다.");
//                }
//                move(TODO);
//                break;
//            case 2:
//                System.out.println("할 일 삭제가 취소되었습니다. 할 일 메뉴 화면으로 이동합니다.");
//                move(TODO);
//                break;
//        }
    }

    public static void todoList() {
        LocalDateTime date;
        List<ToDoDto> todos;

        sc.nextLine();
        System.out.println("--------------------------------------------------------");
        System.out.println("\t\t\t\t\t< 할 일 조회 >");
        List<String> todoList = todoService.readAllToDo();
        if (todoList == null) {
            System.out.println(" * 작성된 할 일이 없습니다.");
            move(TODO);
        } else {
            for (String t : todoList) {
                System.out.println(" - " + t);
            }
            System.out.println();
//            date = inputDate(" 조회할 날짜를 입력하세요(0000-00-00) : ");
//            do {
//                todos = todoService.readToDoList(date);
//                if (todos != null) break;
//                date = inputDate(" 조회 가능한 날짜를 입력하세요(0000-00-00) : ");
//            } while (true);
//            System.out.println("==================================================");
//            if (todos == null) {
//                System.out.println(" * 작성된 할 일이 없습니다.");
//                move(TODO);
//            } else {
//                int i = 0;
//                for (ToDoDto todo : todos) {
//                    System.out.println(" [" + ++i + "] " + todo.getContent() + " (" + todo.getStatus() + ")");
//                }
//            }
//            System.out.println("==================================================");
//            menuToDoList(todos, date);
        }
    }

    public static void diaryWrite() {
        String content;
        LocalDate date;

        sc.nextLine();
        System.out.println("--------------------------------------------------------");
        System.out.println("\t\t\t\t\t< 다이어리 작성하기 >");
        do {
            date = inputDate(" 작성날짜(0000-00-00) : ");
            try {
                diaryService.checkByUserIdAndDate(loginUser, date);
                break;
            } catch (ExistDiaryThatDateException e) {
                System.out.println(" 해당 날짜에 존재하는 다이어리가 있습니다.");
            }
        } while (true);
        System.out.print(" 작성내용 : ");
        content = sc.nextLine();

        Diary diary = new Diary(0L, content, date, 1, loginUser);
        try {
            diaryService.writeDiary(diary);
            System.out.println("다이어리 작성이 완료되었습니다. 다이어리 메뉴 화면으로 이동합니다.");
            move(DIARY);
        } catch (ExistDiaryThatDateException e) {
            System.out.println("다이어리 작성에 실패하셨습니다. 다시 다이어리를 작성하세요.");
            move(DIARY_WRITE);
        }
    }

    public static void diaryUpdate(Diary diary) {
        LocalDate date;
        String content;

        sc.nextLine();
        System.out.println("--------------------------------------------------------");
        System.out.println("\t\t\t\t\t< 다이어리 수정하기 >");
        do {
            date = inputDate(" 수정할 날짜(0000-00-00) : ");
            try {
                if(!diary.getDate().equals(date))
                    diaryService.checkByUserIdAndDate(loginUser, date);
                break;
            } catch (ExistDiaryThatDateException e) {
                System.out.println(" 해당 날짜에 존재하는 다이어리가 있습니다.");
            }
        } while (true);
        System.out.print(" 수정할 내용 : " + diary.getContent());
        content = diary.getContent() + sc.nextLine();

        diary.update(content, date);
        try {
            diaryService.updateDiary(diary);
            System.out.println("다이어리 수정이 완료되었습니다. 다이어리 메뉴 화면으로 이동합니다.");
            move(DIARY);
        } catch (ExistDiaryThatDateException e) {
            System.out.println("다이어리 수정에 실패하셨습니다. 다이어리 메뉴 화면으로 이동합니다.");
            move(DIARY);
        }
    }

    public static void diaryDelete(Diary diary) {
        int selectNum;
        System.out.println(" 정말 다이어리를 삭제하시겠습니까?");
        System.out.println(" - 1. 네\n - 2. 아니오");
        selectNum = inputMenuNumber(2, "Input select number : ");
        switch (selectNum) {
            case 1:
                try {
                    diaryService.delete(diary.getId());
                    System.out.println("다이어리 삭제가 완료되었습니다. 다이어리 메뉴 화면으로 이동합니다.");
                } catch (DiaryNotFoundException e) {
                    System.out.println("다이어리 삭제에 실패하셨습니다. 다이어리 메뉴 화면으로 이동합니다.");
                }
                break;
            case 2:
                System.out.println("다이어리 삭제가 취소되었습니다. 다이어리 메뉴 화면으로 이동합니다.");
                break;
        }
        move(DIARY);
    }

    public static void diaryList() {
        Diary diary = null;
        LocalDate date;

        sc.nextLine();
        System.out.println("--------------------------------------------------------");
        System.out.println("\t\t\t\t\t< 다이어리 조회 >");
        try {
            List<Diary> diaries = diaryService.getAllByUserId(loginUser);
            for(Diary d : diaries)
                System.out.println(" - " + d.getDate().toString());
            System.out.println();

            date = inputDate(" 조회할 날짜를 입력하세요(0000-00-00) : ");
            do {
                try {
                    diaryService.checkByUserIdAndDate(loginUser, date);
                    date = inputDate(" 조회 가능한 날짜를 입력하세요(0000-00-00) : ");
                } catch (ExistDiaryThatDateException e) {
                    break;
                }
            } while (true);

            for(Diary d : diaries) {
                if(d.getDate().equals(date)) diary = d;
            }
            System.out.println("==================================================");
            System.out.println(" 작성날짜 : " + diary.getDate());
            System.out.println(" 작성내용 : " + diary.getContent());
            System.out.println("==================================================");
            menuDiaryList(diary);
        } catch (DiaryNotWrittenException e) {
            System.out.println(" * 작성된 다이어리가 없습니다.");
            move(DIARY);
        }
    }

    public static int inputMenuNumber(int maxNum, String message) {
        int menuNum;
        System.out.print(message);
        while (true) {
            try {
                menuNum = sc.nextInt();
                if (menuNum >= 1 && menuNum <= maxNum)
                    break;
                else
                    throw new InputMismatchException();
            } catch (InputMismatchException e) {
                sc.nextLine();
                System.out.println("*잘못 입력하였습니다. 다시 입력하세요.");
                System.out.print(message);
            }
        }
        return menuNum;
    }

    public static LocalDate inputDate(String message) {
        String date;
        LocalDate parseDate;
        System.out.print(message);
        while (true) {
            try {
                date = sc.nextLine();
                parseDate = LocalDate.parse(date);
                break;
            } catch (DateTimeParseException e) {
                System.out.println("*잘못된 날짜 형식입니다. 다시 입력하세요.");
                System.out.print(message);
            }
        }
        return parseDate;
    }

}