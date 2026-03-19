package com.example.blog.controller;

import com.example.blog.dto.PostRequest;
import com.example.blog.dto.PostResponse;
import com.example.blog.exception.ApiResponse;
import com.example.blog.service.PostService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/posts")
@RequiredArgsConstructor
public class PostController {

	private final PostService postService;

	@GetMapping("/{id}")
	public ResponseEntity<ApiResponse<PostResponse>> getPost(@PathVariable Long id) {
		PostResponse postResponse = postService.getPost(id);
		return ResponseEntity.ok(ApiResponse.ok(postResponse));
	}

	@GetMapping
	public ResponseEntity<ApiResponse<Page<PostResponse>>> getPosts(
			@PageableDefault(size = 10, sort = "createdAt", direction = Sort.Direction.DESC) Pageable pageable
	) {
		Page<PostResponse> response = postService.getPosts(pageable);
		return ResponseEntity.ok(ApiResponse.ok(response));
	}

	@PostMapping
	public ResponseEntity<ApiResponse<PostResponse>> createPost(
			@Valid @RequestBody PostRequest request) {

		PostResponse postResponse = postService.createPost(request);

		return ResponseEntity
				.status(HttpStatus.CREATED)
				.body(ApiResponse.ok(postResponse));
	}

	@DeleteMapping("/{id}")
	public ResponseEntity<ApiResponse<Void>> deletePost(@PathVariable Long id) {
		postService.deletePost(id);
		return ResponseEntity.ok(ApiResponse.ok());
	}

	@PatchMapping("/{id}")
	public ResponseEntity<ApiResponse<PostResponse>> updatePost(
			@PathVariable Long id,
			@Valid @RequestBody PostRequest postRequest) {
		PostResponse postResponse = postService.updatePost(id, postRequest);
		return ResponseEntity.ok(ApiResponse.ok(postResponse));
	}
}
