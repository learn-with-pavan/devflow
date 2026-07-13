package com.pavan.devflow.workspace.service;

import com.pavan.devflow.workspace.entity.Workspace;

public interface WorkspaceAccessService {
	Workspace getOwnedWorkspace(Long workSpaceId);
}
