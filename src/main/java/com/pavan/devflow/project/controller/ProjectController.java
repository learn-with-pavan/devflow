package com.pavan.devflow.project.controller;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.pavan.devflow.common.constants.AppConstants;
import com.pavan.devflow.common.response.ApiResponse;
import com.pavan.devflow.common.response.ResponseBuilder;
import com.pavan.devflow.project.dto.CreateProjectRequest;
import com.pavan.devflow.project.dto.ProjectResponse;
import com.pavan.devflow.project.dto.UpdateProjectRequest;
import com.pavan.devflow.project.service.ProjectService;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api/v1/project")
@RequiredArgsConstructor
public class ProjectController {
	private final ProjectService projectService;

	@PostMapping
	public ResponseEntity<ApiResponse<ProjectResponse>> createProject(
			@Valid @RequestBody CreateProjectRequest request) {

		return ResponseEntity.status(HttpStatus.CREATED)
				.body(ResponseBuilder.success(AppConstants.PROJECT_CREATED, projectService.createProject(request)));
	}

	@GetMapping("/{id}")
	public ResponseEntity<ApiResponse<ProjectResponse>> getProject(@PathVariable Long id) {

		return ResponseEntity.ok(ResponseBuilder.success(AppConstants.PROJECT_FETCHED, projectService.getProject(id)));
	}

	@GetMapping("/workspace/{workspaceId}")
	public ResponseEntity<ApiResponse<List<ProjectResponse>>> getProjects(@PathVariable Long workspaceId) {

		return ResponseEntity
				.ok(ResponseBuilder.success(AppConstants.PROJECTS_FETCHED, projectService.getProjects(workspaceId)));
	}

	@PutMapping("/{id}")
	public ResponseEntity<ApiResponse<ProjectResponse>> updateProject(@PathVariable Long id,
			@Valid @RequestBody UpdateProjectRequest request) {

		return ResponseEntity
				.ok(ResponseBuilder.success(AppConstants.PROJECT_UPDATED, projectService.updateProject(id, request)));
	}

	@PatchMapping("/{id}/archive")
	public ResponseEntity<ApiResponse<Void>> archiveProject(@PathVariable Long id) {

		projectService.archiveProject(id);

		return ResponseEntity.ok(ResponseBuilder.success(AppConstants.PROJECT_ARCHIVED, null));
	}

	@DeleteMapping("/{id}")
	public ResponseEntity<ApiResponse<Void>> deleteProject(@PathVariable Long id) {

		projectService.deleteProject(id);

		return ResponseEntity.ok(ResponseBuilder.success(AppConstants.PROJECT_DELETED, null));
	}
}
