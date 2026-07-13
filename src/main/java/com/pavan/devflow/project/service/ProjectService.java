package com.pavan.devflow.project.service;

import java.util.List;

import com.pavan.devflow.project.dto.CreateProjectRequest;
import com.pavan.devflow.project.dto.ProjectResponse;
import com.pavan.devflow.project.dto.UpdateProjectRequest;

public interface ProjectService {

	ProjectResponse createProject(CreateProjectRequest request);

	ProjectResponse getProject(Long id);

	List<ProjectResponse> getProjects(Long workspaceId);

	ProjectResponse updateProject(Long id, UpdateProjectRequest request);

	void deleteProject(Long id);

	void archiveProject(Long id);
}
