package com.example.rest.domain.post.post.entity;

import com.example.rest.global.BaseTimeEntity;
import jakarta.persistence.Entity;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Builder
public class Post extends BaseTimeEntity {

    private String title;
    private String content;

}
