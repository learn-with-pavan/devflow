package com.pavan.devflow.project.dto;

import java.time.LocalDate;

import com.pavan.devflow.project.enums.ProjectPriority;
import com.pavan.devflow.project.enums.ProjectStatus;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class CreateProjectRequest {
	@NotBlank(message = "Project name is required")
	private String name;

	private String description;

	@NotNull(message = "Workspace id is required")
	private Long workspaceId;

	@NotNull(message = "Status is required")
	private ProjectStatus status;

	@NotNull(message = "Priority is required")
	private ProjectPriority priority;

	private String color;

	private String icon;

	private LocalDate startDate;

	private LocalDate endDate;
}
