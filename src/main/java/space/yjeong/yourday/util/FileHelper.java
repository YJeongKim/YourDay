package space.yjeong.yourday.util;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.stereotype.Component;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

@Component
public class FileHelper {
    private static String REPOSITORY_PATH = Constants.REPOSITORY_PATH;

    private Log logger = LogFactory.getLog(FileHelper.class);

    public File createFolder(String folderPath) {
        if (folderPath == null) {
//            logger.error("실패: 잘못된 경로입니다.");
            return null;
        }
        File folder = new File(REPOSITORY_PATH + folderPath);

        if (!folder.exists()) { // 폴더가 없는 경우 생성
            if (folder.mkdirs()) {
//                logger.info(folder.getPath() + " 폴더 생성");
            } else {
//                logger.error(folder.getPath() + " 폴더 생성 실패");
                return null;
            }
        } else {
//            logger.info(folder.getPath() + " 폴더 존재");
        }
        return folder;
    }

    public File createFile(String filePath) {
        if (filePath == null) {
//            logger.error("실패: 잘못된 경로입니다.");
            return null;
        }
        File file = new File(REPOSITORY_PATH + filePath);

        if (!file.exists()) { // 파일이 없는 경우 생성
            try {
                if (file.createNewFile()) {
//                    logger.info(file.getPath() + " 파일 생성");
                } else {
//                    logger.error(file.getPath() + " 파일 생성 실패");
                    return null;
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        } else {
//            logger.info(file.getPath() + " 파일 존재");
        }
        return file;
    }

    public List<File> findFolderList(String folderPath) {
        String searchPath = REPOSITORY_PATH + folderPath;
        List<File> folderList = new ArrayList<>();
        File checkFolder = new File(searchPath);

        if(!checkFolder.isDirectory()) {
//            logger.error(checkFolder.getPath() + " 폴더를 찾을 수 없습니다.");
            return null;
        }
        for (File folder : checkFolder.listFiles()) {
            if (folder.isDirectory()) {
                folderList.add(folder);
            }
        }
        return folderList;
    }

    public List<File> findFileList(String folderPath) {
        String searchPath = REPOSITORY_PATH + folderPath;
        List<File> fileList = new ArrayList<>();
        File checkFolder = new File(searchPath);

        if(!checkFolder.isDirectory()) {
//            logger.error(checkFolder.getPath() + " 폴더를 찾을 수 없습니다.");
            return null;
        }
        for (File file : checkFolder.listFiles()) {
            if (file.isFile()) {
                fileList.add(file);
            }
        }
        return fileList;
    }

    public File findFile(String filePath) {
        String searchPath = REPOSITORY_PATH + filePath;
        File checkFile = new File(searchPath);

        if(!checkFile.isFile()) {
//            logger.error(checkFile.getPath() + " 파일을 찾을 수 없습니다.");
            return null;
        }
        return checkFile;
    }

    public List<File> uploadFile(String storagePath) {
        if (storagePath == null) {
//            logger.error("실패: 잘못된 경로입니다.");
            return null;
        }
        List<File> fileList = new ArrayList<>();
        String path = REPOSITORY_PATH + storagePath;
//        logger.info("업로드 경로: " + path);

        File saveFile = new File(path);
        if (saveFile.isFile()) { // 중복된 이름의 파일이 있는 경우
            int i = 1;
            String saveName = saveFile.getName();
            String name = saveName.substring(0, saveName.lastIndexOf("."));
            String extension = saveName.substring(saveName.lastIndexOf(".") + 1);
            while (true) {
                if (saveFile.exists()) {
                    saveFile = new File(path, name + "(" + i + ")." + extension);
                    i++;
                } else {
                    break;
                }
            }
        }
        fileList.add(saveFile);
        return fileList;
    }

    public List<String> loadDirectories(String path){
        List<String> files = new ArrayList<>();
        path = REPOSITORY_PATH + path;

//        logger.info("폴더 목록 조회 경로: " + path);
        File dir = new File(path);
        if(!dir.isDirectory()) {
            return null;
        }
        File[] fileList = dir.listFiles();

        for(File file : fileList){
            if(file.isDirectory()){
                files.add(file.getName());
            }
        }

        return files;
    }

    public List<String> loadFile(String path){
        List<String> data = new ArrayList<>();
        FileReader fr = null;
        BufferedReader br = null;
        path = REPOSITORY_PATH + path;
        File dir = new File(path);
        if(!dir.isFile()) {
            return null;
        }
        try {
            fr = new FileReader(path);
            br = new BufferedReader(fr);
            String s = null;
            int count = 0;

            while ((s = br.readLine()) != null) {
                data.add(s);
            }
        } catch (Exception e) {
            e.printStackTrace();

        } finally {
            if (br != null)
                try {
                    br.close();
                } catch (IOException e) {
                }
            if (fr != null)
                try {
                    fr.close();
                } catch (IOException e) {
                }
        }
        return data;
    }

    public boolean deleteFolder(String folderPath) {
        if (folderPath == null) {
            return false;
        }
        File folder = new File(REPOSITORY_PATH + folderPath);

        if (folder.exists()) { // 폴더가 존재하는 경우
            if (folder.isDirectory()) {
                File[] files = folder.listFiles();
                for (int i = 0; i < files.length; i++) {
                    if (!files[i].delete()) {
                        return false;
                    }
                }
            }
            if (!folder.delete()) {
                return false;
            }
        } else {
            return false;
        }
        return true;
    }

    public boolean deleteFile(String filePath) {
        if (filePath == null) {
            return false;
        }
        File file = new File(REPOSITORY_PATH + filePath);

        if (file.exists()) { // 파일이 존재하는 경우
            if (!file.delete()) {
                return false;
            }
        } else {
            return false;
        }
        return true;
    }
}