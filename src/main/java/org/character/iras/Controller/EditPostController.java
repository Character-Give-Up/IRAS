package org.character.iras.Controller;

import com.alibaba.fastjson.JSONObject;
import org.character.iras.Service.PostService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.print.attribute.standard.PrinterResolution;
import java.util.ArrayList;
import java.util.List;

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

    @PostMapping("/removePost")
    public JSONObject removePost(@RequestBody JSONObject info){
        String postName = info.getString("postName");
        postService.removePost(postName);
        JSONObject result = new JSONObject();
        result.put("code", 1);
        result.put("message", "成功");
        return result;
    }

    @GetMapping("/getPosts")
    public List<JSONObject> getPosts(){
        List<String> posts = postService.getPosts();
        List<JSONObject> res = new ArrayList<>();
        for (String post : posts) {
            JSONObject jsonObject = new JSONObject(true);
            jsonObject.put("value", post);
            jsonObject.put("label", post);
            res.add(jsonObject);
        }
        return res;
    }
}
