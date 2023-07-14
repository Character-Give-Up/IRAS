package org.character.iras.Controller;

import com.alibaba.fastjson.JSONObject;
import org.character.iras.CloudAIAccess.ResumeAnalyzer;
import org.character.iras.CloudAIAccess.ResumeAnalyzerImpl;
import org.character.iras.Entity.PDFResolver;
import org.character.iras.Entity.Resume;
import org.character.iras.Service.EditResumeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.io.*;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.URL;
import java.util.List;

/**
 * 手动录入简历控制器
 */
@RestController
public class OperateResumeController {

    private EditResumeService editResumeService;
    private ResumeAnalyzer resumeAnalyzer;
    private PDFResolver pdfResolver;

    @Autowired
    private void setEditResumeService(EditResumeService service){
        this.editResumeService = service;
    }

    @Autowired
    private void setResumeAnalyzer(ResumeAnalyzerImpl analyzer){
        this.resumeAnalyzer = analyzer;
    }

    @Autowired
    private void setPdfResolver(PDFResolver resolver){
        this.pdfResolver = resolver;
    }

    /**
     * 手动录入简历信息
     * @param info POST请求体，包含各个字段（见代码注释）
     * @return 手动录入结果JSON消息
     */
    @PostMapping(value = "/submit")
    public JSONObject submit(@RequestBody JSONObject info){

        // TODO 实现过程
        String username = info.getString("username"); // 获取投递简历的用户名
        String name = info.getString("name"); // 获取投递简历的姓名
        Integer age = info.getInteger("age"); // 获取投递简历的年龄
        String graduateSchool = info.getString("GraduateSchool"); // 设置投递简历的毕业院校
        String highestDegree = info.getString("HighestDegree"); //设置投递简历的最高学历
        String workingSeniority = info.getString("WorkingSeniority"); // 设置投递简历的工作年限
        String post = info.getString("post");

        Resume resume = new Resume();
        resume.setName(name); // 设置简历所有者的姓名
        resume.setAge(age);  // 设置简历所有者的年龄
        resume.setGraduateSchool(graduateSchool); // 设置简历所有者的毕业院校
        resume.setHighestDegree(highestDegree); // 设置简历所有者的最高学历
        resume.setWorkingSeniority(workingSeniority); // 设置简历所有者的工作年限
        resume.setOriginalContent(null); // 手动录入，无原始内容，因此设置为null
        resume.setPost(post);

        editResumeService.addNewResumeData(username, resume);

        JSONObject result = new JSONObject();
        result.put("code", 1);
        result.put("message", "成功");

        return result;
    }

    @GetMapping("/analyze")
    public JSONObject analyze(@RequestBody JSONObject info) throws IOException {

        Resume resume = new Resume();
        resume.setOriginalContent(pdfResolver.resolve(info.getString("url")));
        this.resumeAnalyzer.setResume(resume);
        return resumeAnalyzer.getKeyInfo();
    }



}
