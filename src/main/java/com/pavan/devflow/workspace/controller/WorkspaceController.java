package com.pavan.devflow.workspace.controller;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.pavan.devflow.common.constants.AppConstants;
import com.pavan.devflow.common.response.ApiResponse;
import com.pavan.devflow.common.response.ResponseBuilder;
import com.pavan.devflow.workspace.dto.CreateWorkspaceRequest;
import com.pavan.devflow.workspace.dto.UpdateWorkspaceRequest;
import com.pavan.devflow.workspace.dto.WorkspaceResponse;
import com.pavan.devflow.workspace.service.WorkspaceService;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api/v1/workspaces")
@RequiredArgsConstructor
public class WorkspaceController {

	private final WorkspaceService workSpaceService;

	@PostMapping
	public ResponseEntity<ApiResponse<WorkspaceResponse>> createWorkSpace(
			@Valid @RequestBody CreateWorkspaceRequest request) {
		WorkspaceResponse response = workSpaceService.createWorkspace(request);
		return ResponseEntity.status(HttpStatus.CREATED)
				.body(ResponseBuilder.success(AppConstants.WORKSPACE_CREATED, response));

	}

	@GetMapping("/{id}")
	public ResponseEntity<ApiResponse<WorkspaceResponse>> getWorkSpace(@PathVariable Long id) {
		WorkspaceResponse response = workSpaceService.getWorkspace(id);
		return ResponseEntity.ok(ResponseBuilder.success(AppConstants.WORKSPACE_FETCHED, response));
	}

	@GetMapping
	public ResponseEntity<ApiResponse<List<WorkspaceResponse>>> getMyWorkSpace() {
		List<WorkspaceResponse> response = workSpaceService.getMyWorkSpaces();
		return ResponseEntity.ok(ResponseBuilder.success(AppConstants.WORKSPACES_FETCHED, response));
	}

	@PutMapping("/{id}")
	public ResponseEntity<ApiResponse<WorkspaceResponse>> updateWorkSpace(@PathVariable Long id,
			@Valid @RequestBody UpdateWorkspaceRequest request) {
		WorkspaceResponse response = workSpaceService.updateWorkspace(id, request);
		return ResponseEntity.status(HttpStatus.CREATED)
				.body(ResponseBuilder.success(AppConstants.WORKSPACE_UPDATED, response));

	}

	@DeleteMapping("/{id}")
	public ResponseEntity<ApiResponse<Void>> deleteWorkSpace(@PathVariable Long id) {
		workSpaceService.deleteWorkspace(id);
		return ResponseEntity.ok(ResponseBuilder.success(AppConstants.WORKSPACE_DELETED, null));
	}
}
