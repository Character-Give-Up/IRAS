package org.character.iras.Controller;

import com.alibaba.fastjson.JSONObject;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletRequest;
import org.character.iras.DataAccess.Interfaces.TokenDataAccess;
import org.character.iras.Entity.Resume;
import org.character.iras.Service.ResumeListService;
import org.jetbrains.annotations.NotNull;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@RestController
public class ResumeListController {

    private ResumeListService resumeListService;
    private TokenDataAccess tokenDataAccess;

    @Autowired
    public void setResumeListService(ResumeListService service){
        this.resumeListService = service;
    }

    @Autowired
    public void setTokenDataAccess(TokenDataAccess access) {
        this.tokenDataAccess = access;
    }

    @GetMapping("/resume_list")
    public List<JSONObject> getResumeList(@NotNull HttpServletRequest request){

//        JSONObject result = new JSONObject(true);
//
//        for (Cookie c : request.getCookies()) {
//            boolean a = c.getName().equals("token");
//            boolean b = tokenDataAccess.getTokenByValue(c.getValue()) != null;
//            boolean d = false;
//            if(b) d = !Objects.requireNonNull(tokenDataAccess.getTokenByValue(c.getValue())).isExpired();
//            if(!(a && b && d)) {
//                result.put("code", -1);
//                result.put("message", "令牌无效或已过期");
//                return result;
//            }
//
//
//        }

        List<Resume> resumes = this.resumeListService.getResumes();
        List<JSONObject> res = new ArrayList<>();
        for (int i = 1; i <= resumes.size(); i++) {
            JSONObject detailedInformation = new JSONObject(true);
            Resume resume = resumes.get(i - 1);
            detailedInformation.put("seq", i);
            detailedInformation.put("name", resume.getName());
            detailedInformation.put("keywords", resume.getKeyword());
            detailedInformation.put("age", resume.getAge());
            detailedInformation.put("HighestDegree", resume.getHighestDegree());
            detailedInformation.put("GraduateSchool", resume.getGraduateSchool());
            detailedInformation.put("WorkingSeniority", resume.getWorkingSeniority());
            detailedInformation.put("url", resume.getPath());
//            result.put(String.valueOf(i), detailedInformation);
            res.add(detailedInformation);
        }
        return res;
    }
}
