package org.character.iras.Controller;

import com.alibaba.fastjson.JSONObject;
import org.character.iras.Application;
import org.character.iras.DataAccess.MySQLImplments.MySQLResumeDataAccess;
import org.character.iras.Service.UploadFileService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

@RestController
public class UploadResumeController {

    private UploadFileService uploadFileService;

    @Autowired
    public void setUploadFileService(UploadFileService service){
        this.uploadFileService = service;
    }

    /**
     * 上传文件接口
     * @param username 文件所有者
     * @param file 文件
     * @return 上传结果信息键值对
     */


    @PostMapping("/upload")
    public JSONObject upload(@RequestParam("username") String username,
                             @RequestParam("file")MultipartFile file){
        // TODO 实现文件上传
        JSONObject result = new JSONObject();
        uploadFileService.setFileType(UploadFileService.RESUME);
        try {
            String path0 = uploadFileService.putFile(file);
            uploadFileService.setUsername(username);
            uploadFileService.upload(() -> {
                MySQLResumeDataAccess mySQLResumeDataAccess = Application.context.getBean(MySQLResumeDataAccess.class);
                mySQLResumeDataAccess.putNewResumeData(mySQLResumeDataAccess.getMaximumId(), path0);

            });
        } catch (IOException e) {
            result.put("code", 0);
            result.put("message", "服务器I/O异常");
            return result;
        }

        result.put("code", 1);


        return result;
    }
}
