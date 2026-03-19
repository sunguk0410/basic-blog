package com.example.blog.dto;

import com.example.blog.entity.Post;
import lombok.Builder;
import lombok.Getter;

import java.time.LocalDateTime;

@Getter
public class PostResponse {

	private Long id;
	private String title;
	private String content;
	private Long viewCount;
	private LocalDateTime createdAt;
	private LocalDateTime updatedAt;

	@Builder
	public PostResponse(Long id, String title, String content, Long viewCount,
						LocalDateTime createdAt, LocalDateTime updatedAt) {
		this.id = id;
		this.title = title;
		this.content = content;
		this.viewCount = viewCount;
		this.createdAt = createdAt;
		this.updatedAt = updatedAt;
	}

	public static PostResponse from(Post post) {
		return PostResponse.builder()
				.id(post.getId())
				.title(post.getTitle())
				.content(post.getContent())
				.viewCount(post.getViewCount())
				.createdAt(post.getCreatedAt())
				.updatedAt(post.getUpdatedAt())
				.build();
	}
}
