package com.example.rest.domain.post.post.controller;

import com.example.rest.domain.post.post.entity.Post;
import com.example.rest.domain.post.post.service.PostService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/api/v1/posts")
@RequiredArgsConstructor
public class ApiV1PostController {

    private final PostService postService;

    @GetMapping
    @ResponseBody
    public List<Post> getItems() {
        return postService.getItems();
    }

    @DeleteMapping("/{id}")
    public String deleteItem(@PathVariable long id) {
        Post post = postService.getItem(id).get();
        postService.deleteItem(post);

        return "%d번 글 삭제가 완료되었습니다.".formatted(id);
    }


}
