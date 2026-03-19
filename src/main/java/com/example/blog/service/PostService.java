package com.example.blog.service;

import com.example.blog.dto.PostRequest;
import com.example.blog.dto.PostResponse;
import com.example.blog.entity.Post;
import com.example.blog.exception.NotFoundException;
import com.example.blog.repository.PostRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class PostService {
	private final PostRepository postRepository;

	@Transactional(readOnly = true)
	public Page<PostResponse> getPosts(Pageable pageable) {
		return postRepository.findAll(pageable)
				.map(PostResponse::from);
	}

	@Transactional
	public PostResponse getPost(Long id) {
		Post post = postRepository.findById(id)
				.orElseThrow(() -> new NotFoundException("게시글 없음"));
		post.increaseViewCount();
		return PostResponse.from(post);
	}

	@Transactional
	public PostResponse createPost(PostRequest request) {
		Post post = request.toEntity();
		Post savedPost = postRepository.save(post);
		return PostResponse.from(savedPost);
	}

	@Transactional
	public PostResponse updatePost(Long id, PostRequest request) {
		Post post = postRepository.findById(id)
				.orElseThrow(() -> new NotFoundException("게시글 없음"));

		if (request.getTitle() != null) {
			post.updateTitle(request.getTitle());
		}

		if (request.getContent() != null) {
			post.updateContent(request.getContent());
		}

		return PostResponse.from(post);
	}

	@Transactional
	public void deletePost(Long id) {
		Post post = postRepository.findById(id)
				.orElseThrow(() -> new NotFoundException("게시글 없음"));

		postRepository.deleteById(id);
	}
}
