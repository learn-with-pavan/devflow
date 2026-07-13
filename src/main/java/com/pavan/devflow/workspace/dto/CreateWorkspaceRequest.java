package com.pavan.devflow.workspace.dto;

import com.pavan.devflow.workspace.enums.WorkspaceVisibility;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class CreateWorkspaceRequest {

	@NotBlank(message = "Workspace name is required")
	private String name;

	private String description;

	@NotNull(message = "Visibility is required")
	private WorkspaceVisibility visibility;
}
