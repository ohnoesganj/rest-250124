package com.example.rest.domain.post.post.service;

import com.example.rest.domain.post.post.entity.Post;
import com.example.rest.domain.post.post.repository.PostRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class PostService {

    private final PostRepository postRepository;


    public Post write(String title, String content) {
        Post post = Post.builder()
                .title(title)
                .content(content)
                .build();

        postRepository.save(post);
        return post;
    }

    public List<Post> getItems() {
        return postRepository.findAll();
    }

    public Optional<Post> getItem(long id) {
        return postRepository.findById(id);
    }

    public long count() {
        return postRepository.count();
    }

    public void deleteItem(Post post) {
        postRepository.delete(post);
    }

    @Transactional
    public void modify(Post post, String title, String content) {
        post.setTitle(title);
        post.setContent(content);
    }
}
