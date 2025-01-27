package com.example.rest.domain.post.post.controller;

import com.example.rest.domain.post.post.entity.Post;
import com.example.rest.domain.post.post.service.PostService;
import com.example.rest.global.dto.RsData;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/posts")
@RequiredArgsConstructor
public class ApiV1PostController {

    private final PostService postService;

    @GetMapping
    public List<Post> getItems() {
        return postService.getItems();
    }

    @DeleteMapping("/{id}")
    public RsData deleteItem(@PathVariable long id) {
        Post post = postService.getItem(id).get();
        postService.deleteItem(post);

        return new RsData("200-1", "%d번 글 삭제가 완료되었습니다.".formatted(id));
    }

    @AllArgsConstructor
    @Getter
    public static class ModifyForm {
        private String title;
        private String content;
    }

    @PutMapping("{id}")
    public RsData updateItem(@PathVariable long id, @RequestBody ModifyForm form) {
        Post post = postService.getItem(id).get();
        postService.modify(post, form.getTitle(), form.getContent());

        return new RsData("200-1", "%d번 글 수정이 완료되었습니다.".formatted(id));
    }


}
