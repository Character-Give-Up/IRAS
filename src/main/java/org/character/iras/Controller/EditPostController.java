package org.character.iras.Controller;

import com.alibaba.fastjson.JSONObject;
import org.character.iras.Service.PostService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.print.attribute.standard.PrinterResolution;

@RestController
public class EditPostController {

    private PostService postService;

    @Autowired
    private void setPostService(PostService service){
        this.postService = service;
    }

    @PostMapping("/addPost")
    public JSONObject addPost(@RequestBody JSONObject info){
        String postName = info.getString("postName");
        postService.addPost(postName);
        JSONObject result = new JSONObject();
        result.put("code", 1);
        result.put("message", "成功");
        return result;
    }
}
