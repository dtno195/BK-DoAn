/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ptk.elearning.controller;

import com.google.gson.Gson;
import com.ptk.elearning.common.CommonConstant;
import com.ptk.elearning.common.ErrorResult;
import com.ptk.elearning.common.FileAssert;
import com.ptk.elearning.util.StringUtil;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import javax.servlet.http.HttpServletRequest;
import org.apache.commons.io.FilenameUtils;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import org.springframework.web.servlet.ModelAndView;


@Controller
@RequestMapping(value = "TblFiles")
public class TblFilesController extends BaseController {

    @RequestMapping(value = "/Index.html", method = RequestMethod.GET)
    public ModelAndView Index(Long userId) {
        ModelAndView model = new ModelAndView();
        model.setViewName("tbl_files");
        return model;
    }

    @RequestMapping(value = "/getDatasources.json", method = RequestMethod.GET,
            produces = "application/json; charset=UTF-8")
    public @ResponseBody
    String getDatasources(HttpServletRequest request) {
        try {
            File file = new File(CommonConstant.DIRECTORY);
            return new Gson().toJson(FileAssert.getChildren(file, 0));
        } catch (Exception ex) {
            return null;
        }
    }

    @RequestMapping(value = "/getFolder.json", method = RequestMethod.GET,
            produces = "application/json; charset=UTF-8")
    public @ResponseBody
    String getFolder(HttpServletRequest request) {
        try {
            String folder = request.getParameter("folder");
            File file = new File(CommonConstant.DIRECTORY + "\\" + folder);
            return new Gson().toJson(FileAssert.getFiles(file, folder.replaceAll("\\\\", "/")));
        } catch (Exception ex) {
            return null;
        }
    }

    @RequestMapping(value = "/saveFolder.json", method = RequestMethod.POST,
            produces = "application/json; charset=UTF-8")
    public @ResponseBody
    String saveFolder(HttpServletRequest request) {
        String name = request.getParameter("name");
        String path = request.getParameter("path");
        File folder = new File(CommonConstant.DIRECTORY + "\\" + path + "\\" + name);
        ErrorResult result = new ErrorResult();
        if (!folder.exists()) {
            folder.mkdir();
            result.setHasError(false);
            result.setError("Thêm mới thư mục thành công");
            return new Gson().toJson(result);
        }
        result.setHasError(true);
        result.setError("Thư mục đã tồn tại, mời chọn tên khác");
        return new Gson().toJson(result);
    }

    @RequestMapping(value = "/updateFolder.json", method = RequestMethod.POST,
            produces = "application/json; charset=UTF-8")
    public @ResponseBody
    String updateFolder(HttpServletRequest request) {
        String newName = request.getParameter("newName");
        String path = request.getParameter("path");
        File folder = new File(CommonConstant.DIRECTORY + "\\" + path);
        String[] arrPath = path.split("\\\\");
        int size = arrPath.length;
        path = "";
        for (int i = 0; i < size - 1; i++) {
            path += arrPath[i];
        }
        File newFolder = new File(CommonConstant.DIRECTORY + "\\" + path + "\\" + newName);
        boolean isRename = folder.renameTo(newFolder);
        ErrorResult result = new ErrorResult();
        if (isRename) {
            result.setHasError(false);
            result.setError("Cập nhật thư mục thành công");
            return new Gson().toJson(result);
        }
        result.setHasError(true);
        result.setError("Không thể cập nhật thư mục này");
        return new Gson().toJson(result);
    }

    @RequestMapping(value = "/removeFolder.json", method = RequestMethod.POST,
            produces = "application/json; charset=UTF-8")
    public @ResponseBody
    String removeFolder(HttpServletRequest request) {
        String path = request.getParameter("path");
        File folder = new File(CommonConstant.DIRECTORY + "\\" + path);
        ErrorResult result = new ErrorResult();
        if (folder.delete()) {
            result.setHasError(false);
            result.setError("Xóa thư mục thành công");
            return new Gson().toJson(result);
        }
        result.setHasError(true);
        result.setError("Không thể cập nhật thư mục này");
        return new Gson().toJson(result);
    }

    @RequestMapping(value = {"/uploadFiles.json"}, method = RequestMethod.POST,
            produces = "application/json; charset=UTF-8")
    @ResponseBody
    public String importExcel(MultipartHttpServletRequest request) throws IOException {
        List<MultipartFile> files = request.getFiles("files");
        ErrorResult result = new ErrorResult();
        String path = request.getParameter("path");
        if (files.size() == 0) {
            result.setHasError(true);
            result.setError("Bạn chưa chọn hình ảnh nào");
            return new Gson().toJson(result);
        }
        SimpleDateFormat smp = new SimpleDateFormat("dd-MM-yyyy HHmmss");
        int uploaded = 0;
        for (MultipartFile item : files) {
            if (!StringUtil.isBlank(item.getOriginalFilename()) && FileAssert.isImage(item.getOriginalFilename())) {
                File serverFile = new File(CommonConstant.DIRECTORY + "\\" + path + "\\" + item.getOriginalFilename());
                if (serverFile.exists()) {
                    String extension = FilenameUtils.getExtension(item.getOriginalFilename());
                    String fileName = FilenameUtils.removeExtension(item.getOriginalFilename()) + smp.format(new Date());
                    serverFile = new File(CommonConstant.DIRECTORY + "\\" + path + "\\" + fileName + "." + extension);
                }
                try (BufferedOutputStream streamOut = new BufferedOutputStream(
                        new FileOutputStream(serverFile.toString()))) {
                    streamOut.write(item.getBytes());
                    streamOut.close();
                    uploaded++;
                } catch (IOException ex) {
                    logger.error(ex);
                }
            }

        }
        if (uploaded > 0) {
            result.setHasError(false);
            result.setError("Đã tải lên " + uploaded + " hình ảnh");
            return new Gson().toJson(result);
        } else {
            result.setHasError(true);
            result.setError("Tải hình ảnh không thành công");
            return new Gson().toJson(result);
        }
    }

    @RequestMapping(value = "/removeFile.json", method = RequestMethod.POST,
            produces = "application/json; charset=UTF-8")
    public @ResponseBody
    String removeFile(HttpServletRequest request) {
        String name = request.getParameter("name");
        String path = request.getParameter("path");
        File file = new File(CommonConstant.DIRECTORY + "\\" + path + "\\" + name);
        ErrorResult result = new ErrorResult();
        if (file.exists() && file.delete()) {
            result.setHasError(false);
            result.setError("Xóa hình ảnh thành công");
            return new Gson().toJson(result);
        }
        result.setHasError(true);
        result.setError("Hình ảnh không tồn tại hoặc không thể xóa hình ảnh");
        return new Gson().toJson(result);
    }

}
