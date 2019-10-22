/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ptk.elearning.common;


import com.ptk.elearning.dto.FileInfo;
import com.ptk.elearning.dto.FolderDataSource;
import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import org.apache.commons.io.FilenameUtils;

public class FileAssert {

    public static List<FolderDataSource> getChildren(File folder, int pId) {
        File[] files = folder.listFiles();
        List<FolderDataSource> dataSources1 = new ArrayList<>();
        if (files != null && files.length > 0) {
            for (File file : files) {
                if (file.isDirectory()) {
                    FolderDataSource temp = new FolderDataSource();
                    temp.setText(file.getName());
                    temp.setIconCls("fa fa-folder");
                    temp.setItems(getChildren(file, pId));
                    String fullpath = file.getPath();
                    temp.setFullPath(fullpath.substring(CommonConstant.DIRECTORY.length(), fullpath.length()));
                    dataSources1.add(temp);
                }
            }
        }
        return dataSources1;
    }

//    public static void main(String[] args) {
//        File file = new File(CommonConstant.DIRECTORY);
//
//        System.out.println(new Gson().toJson(getChildren(file, 0)));
//    }
    public static List<FileInfo> getFiles(File folder, String path) {
        File[] files = folder.listFiles();
        List<FileInfo> dataSources1 = new ArrayList<>();
        if (files != null && files.length > 0) {
            for (File file : files) {
                if (!file.isDirectory()) {
                    FileInfo temp = new FileInfo();
                    temp.setFileName(file.getName());
                    temp.setFileSize(file.length() / 1024);
                    temp.setExtension(FilenameUtils.getExtension(file.getName()));
                    temp.setFilePath(path + "//" + file.getName());
                    dataSources1.add(temp);
                }
            }
        }
        return dataSources1;
    }

//    public static void main(String[] args) {
//        File file = new File(CommonConstant.DIRECTORY + "\\Đề 3");
//        System.out.println(new Gson().toJson(getFiles(file, "\\Đề 3".replaceAll("\\\\", "/"))));
//    }
    public static boolean isImage(String filename) {
        Pattern pattern;
        Matcher matcher;
        String IMAGE_PATTERN
                = "([^\\s]+(\\.(?i)(jpg|png|gif|bmp|jpeg))$)";
        pattern = Pattern.compile(IMAGE_PATTERN);
        matcher = pattern.matcher(filename);
        return matcher.matches();
    }
}
