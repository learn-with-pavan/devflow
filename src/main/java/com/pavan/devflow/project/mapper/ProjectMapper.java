package com.pavan.devflow.project.mapper;

import org.springframework.stereotype.Component;

import com.pavan.devflow.project.dto.CreateProjectRequest;
import com.pavan.devflow.project.dto.ProjectResponse;
import com.pavan.devflow.project.entity.Project;
import com.pavan.devflow.user.entity.User;
import com.pavan.devflow.workspace.entity.Workspace;

@Component
public class ProjectMapper {
	public Project toEntity(CreateProjectRequest request, Workspace workspace, User owner, String slug) {

		Project project = new Project();
		project.setName(request.getName());
		project.setDescription(request.getDescription());
		project.setSlug(slug);
		project.setStatus(request.getStatus());
		project.setPriority(request.getPriority());
		project.setColor(request.getColor());
		project.setIcon(request.getIcon());
		project.setStartDate(request.getStartDate());
		project.setEndDate(request.getEndDate());
		project.setWorkspace(workspace);
		project.setOwner(owner);
		project.setArchived(false);
		return project;
	}

	public ProjectResponse toResponse(Project project) {

		return ProjectResponse.builder()

				.id(project.getId()).name(project.getName()).slug(project.getSlug())
				.description(project.getDescription()).status(project.getStatus()).priority(project.getPriority())
				.color(project.getColor()).icon(project.getIcon()).archived(project.isArchived())
				.workspaceName(project.getWorkspace().getName())
				.ownerName(project.getOwner().getFirstName() + " " + project.getOwner().getLastName())
				.createdAt(project.getCreatedAt()).updatedAt(project.getUpdatedAt())

				.build();

	}
}
