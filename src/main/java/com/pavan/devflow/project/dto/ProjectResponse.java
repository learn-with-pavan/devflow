package com.pavan.devflow.project.dto;

import java.time.LocalDateTime;

import com.pavan.devflow.project.enums.ProjectPriority;
import com.pavan.devflow.project.enums.ProjectStatus;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class ProjectResponse {
	private Long id;

	private String name;

	private String slug;

	private String description;

	private ProjectStatus status;

	private ProjectPriority priority;

	private String color;

	private String icon;

	private Boolean archived;

	private String workspaceName;

	private String ownerName;

	private LocalDateTime createdAt;

	private LocalDateTime updatedAt;
}
