package org.character.iras.Controller;

import com.alibaba.fastjson.JSONObject;
import io.minio.errors.InternalException;
import io.minio.errors.InvalidResponseException;
import io.minio.errors.*;
import org.character.iras.Service.UploadFileService;
import org.character.iras.Utils.TimeStampGenerator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.ErrorResponseException;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.rmi.ServerException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;

/**
 * 上传简历文件的控制器
 */
@RestController
public class UploadResumeController {

    /**
     * 文件上传服务层实例
     */
    private UploadFileService uploadFileService;

    /**
     * 时间戳生成器
     */
    private TimeStampGenerator timeStampGenerator;

    /**
     * 自动注入文件上传服务实例
     * @param service
     */
    @Autowired
    private void setUploadFileService(UploadFileService service){
        this.uploadFileService = service;
    }

    /**
     * 自动注入时间戳生成器实例
     * @param generator 时间戳生成器
     */
    @Autowired
    private void setTimeStampGenerator(TimeStampGenerator generator){
        this.timeStampGenerator = generator;
    }

    /**
     * 上传文件接口
     * @param username 文件所有者
     * @param file 文件
     * @return 上传结果信息键值对
     */
    @PostMapping("/upload")
    public JSONObject upload(@RequestParam("username") String username,
                             @RequestParam("file") MultipartFile file){

        JSONObject result = new JSONObject();
        try {
            String currentTimeString = timeStampGenerator.getCurrentTimeString("yyyyMMddHHmmssSSS");
            uploadFileService.setFilename(currentTimeString + "_" + file.getOriginalFilename());
            uploadFileService.putFile(file);
            uploadFileService.setUsername(username);
            String url = uploadFileService.upload();
            result.put("code", 1);
            result.put("url", url);
            return result;
        } catch (IOException e) {
            result.put("code", 0);
            result.put("message", "服务器I/O异常：" + e.getMessage());
            return result;
        } catch (InsufficientDataException e) {
            result.put("code", 0);
            result.put("message", "MiniO不充足的数据：" + e.getMessage());
            return result;
        } catch (ErrorResponseException e) {
            result.put("code", 0);
            result.put("message", "MiniO响应错误：" + e.getMessage());
            return result;
        } catch (NoSuchAlgorithmException e) {
            result.put("code", 0);
            result.put("message", "MiniO-NoSuchAlgorithmException：" + e.getMessage());
            return result;
        } catch (InvalidKeyException e) {
            result.put("code", 0);
            result.put("message", "MiniO密钥不正确：" + e.getMessage());
            return result;
        } catch (InvalidResponseException e) {
            result.put("code", 0);
            result.put("message", "MiniO无效的相应：" + e.getMessage());
            return result;
        } catch (XmlParserException e) {
            result.put("code", 0);
            result.put("message", "MiniO-XmlParserException：" + e.getMessage());
            return result;
        } catch (InternalException | io.minio.errors.ErrorResponseException | io.minio.errors.ServerException e) {
            result.put("code", 0);
            result.put("message", "MiniO服务器内部错误：" + e.getMessage());
            return result;
        }





    }
}
