package com.pavan.devflow.common.response;

import java.util.List;

import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class PageResponse<T> {
	private List<T> content;
	private int page;
	private int size;
	private Long totalElements;
	private int totalPages;
	private boolean last;
}
