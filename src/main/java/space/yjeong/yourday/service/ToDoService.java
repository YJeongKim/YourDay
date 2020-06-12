package space.yjeong.yourday.service;

import space.yjeong.yourday.domain.todo.Status;
import space.yjeong.yourday.domain.todo.ToDo;
import space.yjeong.yourday.domain.todo.ToDoDto;
import java.time.LocalDateTime;
import java.util.List;

public class ToDoService {

    public boolean writeToDo(ToDo todo){
//        String currentPath = "\\" + email +PATH + todo.getDate().format(DateTimeFormatter.ofPattern("yyyy-MM-dd"));
//        fileHelper.createFolder(currentPath);
//        File file = fileHelper.createFile(currentPath+"\\"+todo.getId()+".txt");
//        FileWriter fw = null;
//        try {
//            fw = new FileWriter(file, false);
//            fw.write(todo.toFileData());
//            fw.close();
//        } catch (IOException e) {
//            e.printStackTrace();
//            return false;
//        }
        return true;
    }

    public List<ToDoDto> readToDoList(LocalDateTime date) {
////        List<File> fileList = fileHelper.findFileList("\\" + email + PATH + date.format(DateTimeFormatter.ofPattern("yyyy-MM-dd")));
//        List<ToDoDto> dataList = new ArrayList();
//        if(fileList == null) return null;
//        for(File file : fileList) {
//            List<String> data = fileHelper.loadFile("\\" + email + PATH + date.format(DateTimeFormatter.ofPattern("yyyy-MM-dd"))+"\\"+file.getName());
//            dataList.add(new ToDoDto(Long.parseLong(data.get(0)), data.get(1), parseStatus(data.get(3))));
//        }
        return null;
    }

    public ToDoDto readToDo(String year, String month, String day, Long id){
//        List<String> data = fileHelper.loadFile("\\" + email + PATH + year + "-" + month + "-" + day+"\\"+id+".txt");
//        return new ToDoDto(Long.parseLong(data.get(0)), data.get(1), parseStatus(data.get(3)));
        return null;
    }

    public List<String> readAllToDo(){
//        return fileHelper.loadDirectories("\\" + email + PATH);
        return null;
    }

    public int getToDoCount(String email, LocalDateTime date){
//        List<File> todos = fileHelper.findFileList("\\" + email + PATH + date.format(DateTimeFormatter.ofPattern("yyyy-MM-dd")));
//        if(todos == null) return 0;
//        File f = todos.get(todos.size() - 1);
//        return Integer.parseInt(f.getName().substring(0, f.getName().indexOf('.')));
        return 0;
    }

    public boolean deleteToDo(String email, LocalDateTime date, Long id) {
//        List<String> data = fileHelper.loadFile("\\" + email + PATH+"\\"+date.format(DateTimeFormatter.ofPattern("yyyy-MM-dd"))+"\\"+id+".txt");
//        if(data == null) return false;
//        else return fileHelper.deleteFile("\\" + email + PATH+"\\"+date.format(DateTimeFormatter.ofPattern("yyyy-MM-dd"))+"\\"+id+".txt");
        return false;
    }

    public String parseStatus(String status) {
        if (status.equals("TODO")) {
            return Status.TODO.getTitle();
        } else if (status.equals("DOING")) {
            return Status.DOING.getTitle();
        } else {
            return Status.DONE.getTitle();
        }
    }

}
