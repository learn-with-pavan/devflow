package com.pavan.devflow.workspace.dto;

import java.time.LocalDateTime;

import com.pavan.devflow.workspace.enums.WorkspaceVisibility;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class WorkspaceResponse {

	private Long id;

	private String name;

	private String description;

	private String slug;

	private WorkspaceVisibility visibility;

	private String ownerName;

	private LocalDateTime createdAt;
}
