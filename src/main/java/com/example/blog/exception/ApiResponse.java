package com.example.blog.exception;

import lombok.Getter;

import java.util.Map;

@Getter
public class ApiResponse<T> {

	private final boolean success;
	private final T data;
	private final String message;

	public ApiResponse(boolean success, T data, String message) {
		this.success = success;
		this.data = data;
		this.message = message;
	}

	public static <T> ApiResponse<T> ok(T data) {
		return new ApiResponse<>(true, data, null);
	}

	public static <T> ApiResponse<T> ok() {
		return new ApiResponse<>(true, null, null);
	}

	public static <T> ApiResponse<T> error(String message) {
		return new ApiResponse<>(false, null, message);
	}

	public static <T> ApiResponse<T> error(T data) {
		return new ApiResponse<>(false, data, null);
	}
}
