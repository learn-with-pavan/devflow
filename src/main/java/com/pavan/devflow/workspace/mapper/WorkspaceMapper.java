package com.pavan.devflow.workspace.mapper;

import org.springframework.stereotype.Component;

import com.pavan.devflow.user.entity.User;
import com.pavan.devflow.workspace.dto.CreateWorkspaceRequest;
import com.pavan.devflow.workspace.dto.WorkspaceResponse;
import com.pavan.devflow.workspace.entity.Workspace;

@Component
public class WorkspaceMapper {
	
	public Workspace toEntity(CreateWorkspaceRequest request, User owner, String slug) {

		Workspace workspace = new Workspace();

		workspace.setName(request.getName());
		workspace.setDescription(request.getDescription());
		workspace.setVisibility(request.getVisibility());
		workspace.setSlug(slug);
		workspace.setOwner(owner);

		return workspace;
	}

	public WorkspaceResponse toResponse(Workspace workspace) {

		return WorkspaceResponse.builder().id(workspace.getId()).name(workspace.getName())
				.description(workspace.getDescription()).slug(workspace.getSlug()).visibility(workspace.getVisibility())
				.ownerName(workspace.getOwner().getFirstName() + " " + workspace.getOwner().getLastName())
				.createdAt(workspace.getCreatedAt()).build();
	}

}
