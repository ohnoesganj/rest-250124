package com.example.rest.domain.post.post.controller;

import com.example.rest.domain.post.post.dto.PostDto;
import com.example.rest.domain.post.post.entity.Post;
import com.example.rest.domain.post.post.service.PostService;
import com.example.rest.global.dto.RsData;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.hibernate.validator.constraints.Length;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/posts")
@RequiredArgsConstructor
public class ApiV1PostController {

    private final PostService postService;

    @GetMapping("{id}")
    public RsData<PostDto> getItem(@PathVariable long id) {
        Post post = postService.getItem(id).get();

        return new RsData<>("200-1",
                "글 조회가 완료되었습니다.",
                new PostDto(post));
    }


    @GetMapping
    public RsData<List<PostDto>> getItems() {
        List<Post> posts = postService.getItems();
        List<PostDto> postDtos = posts.stream()
                .map(PostDto::new)
                .toList();

        return new RsData<>(
                "200-1",
                "글 목록 조회가 완료되었습니다.",
                postDtos);
    }


    @DeleteMapping("/{id}")
    public RsData deleteItem(@PathVariable long id) {
        Post post = postService.getItem(id).get();
        postService.deleteItem(post);

        return new RsData(
                "204-1",
                "%d번 글 삭제가 완료되었습니다.".formatted(id));
    }


    @AllArgsConstructor
    @Getter
    public static class ModifyReqBody {
        @NotBlank @Length(min = 3)
        private String title;
        @NotBlank @Length(min = 3)
        private String content;
    }

    @PutMapping("{id}")
    public RsData<Void> updateItem(@PathVariable long id, @RequestBody @Valid ModifyReqBody body) {
        Post post = postService.getItem(id).get();
        postService.modify(post, body.getTitle(), body.getContent());

        return new RsData<>(
                "200-1",
                "%d번 글 수정이 완료되었습니다.".formatted(id));
    }


    @AllArgsConstructor
    @Getter
    public static class WriteReqBody {
        @NotBlank @Length(min = 3)
        private String title;
        @NotBlank @Length(min = 3)
        private String content;
    }

    @AllArgsConstructor
    @Getter
    public static class WriteResBody {
        private long id;
        private long totalCount;
    }

    @PostMapping
    public ResponseEntity<RsData<WriteResBody>> createItem(@RequestBody @Valid WriteReqBody body) {
        Post post = postService.write(body.getTitle(), body.getContent());

        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(new RsData<>(
                        "200-1",
                        "글 작성이 완료되었습니다.",
                        new WriteResBody(post.getId(), postService.count())
                ));
    }
}
