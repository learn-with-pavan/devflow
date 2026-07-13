package com.pavan.devflow.workspace.service;

import java.util.List;

import com.pavan.devflow.workspace.dto.CreateWorkspaceRequest;
import com.pavan.devflow.workspace.dto.UpdateWorkspaceRequest;
import com.pavan.devflow.workspace.dto.WorkspaceResponse;

public interface WorkspaceService {
	WorkspaceResponse createWorkspace(CreateWorkspaceRequest request);
	WorkspaceResponse getWorkspace(Long id);
	List<WorkspaceResponse> getMyWorkSpaces();
	WorkspaceResponse updateWorkspace(Long id, UpdateWorkspaceRequest request);
	void deleteWorkspace(Long id);
}
