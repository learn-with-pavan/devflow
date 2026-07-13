package com.pavan.devflow.workspace.service;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.pavan.devflow.common.exception.ResourceNotFoundException;
import com.pavan.devflow.security.config.util.SecurityUtils;
import com.pavan.devflow.user.entity.User;
import com.pavan.devflow.workspace.entity.Workspace;
import com.pavan.devflow.workspace.repository.WorkspaceRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class WorkspaceAccessServiceImpl implements WorkspaceAccessService {

	private final WorkspaceRepository workSpaceRepository;
	private final SecurityUtils securityUtils;

	@Override
	public Workspace getOwnedWorkspace(Long workSpaceId) {
		User currentUser = securityUtils.getCurrentUser();
		return workSpaceRepository.findByIdAndOwner(workSpaceId, currentUser)
				.orElseThrow(() -> new ResourceNotFoundException("Workspace not found"));
	}

}
