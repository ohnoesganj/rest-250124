package com.example.rest.domain.post.post.entity;

import com.example.rest.global.entity.BaseTimeEntity;
import jakarta.persistence.Entity;
import lombok.*;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
public class Post extends BaseTimeEntity {

    private String title;
    private String content;

}
