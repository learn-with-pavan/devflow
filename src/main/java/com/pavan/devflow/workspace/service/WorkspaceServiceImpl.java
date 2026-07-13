package com.pavan.devflow.workspace.service;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.pavan.devflow.common.exception.ResourceNotFoundException;
import com.pavan.devflow.common.util.SlugUtil;
import com.pavan.devflow.security.config.util.SecurityUtils;
import com.pavan.devflow.user.entity.User;
import com.pavan.devflow.workspace.dto.CreateWorkspaceRequest;
import com.pavan.devflow.workspace.dto.UpdateWorkspaceRequest;
import com.pavan.devflow.workspace.dto.WorkspaceResponse;
import com.pavan.devflow.workspace.entity.Workspace;
import com.pavan.devflow.workspace.mapper.WorkspaceMapper;
import com.pavan.devflow.workspace.repository.WorkspaceRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class WorkspaceServiceImpl implements WorkspaceService {

	private final WorkspaceRepository workSpaceRepository;
	private final WorkspaceMapper mapper;
	private final SecurityUtils securityUtils;

	@Override
	public WorkspaceResponse createWorkspace(CreateWorkspaceRequest request) {
		User currentUser = securityUtils.getCurrentUser();
		String slug = SlugUtil.toSlug(request.getName());
		Workspace workSpace = mapper.toEntity(request, currentUser, slug);
		Workspace savedResponse = workSpaceRepository.save(workSpace);

		return mapper.toResponse(savedResponse);
	}

	@Override
	@Transactional(readOnly = true)
	public WorkspaceResponse getWorkspace(Long id) {
		User currentUser = securityUtils.getCurrentUser();
		Workspace workSpace = workSpaceRepository.findByIdAndOwner(id, currentUser)
				.orElseThrow(() -> new ResourceNotFoundException("Workspace not found"));

		return mapper.toResponse(workSpace);
	}

	@Override
	@Transactional(readOnly = true)
	public List<WorkspaceResponse> getMyWorkSpaces() {
		User currentUser = securityUtils.getCurrentUser();
		return workSpaceRepository.findAllByOwner(currentUser).stream().map(mapper::toResponse)
				.collect(Collectors.toList());
	}

	@Override
	public WorkspaceResponse updateWorkspace(Long id, UpdateWorkspaceRequest request) {
		User currentUser = securityUtils.getCurrentUser();
		Workspace workSpace = workSpaceRepository.findByIdAndOwner(id, currentUser)
				.orElseThrow(() -> new ResourceNotFoundException("Workspace not found"));
		workSpace.setName(request.getName());
		workSpace.setDescription(request.getDescription());
		workSpace.setVisibility(request.getVisibility());

		Workspace updatedWorkspace = workSpaceRepository.save(workSpace);
		return mapper.toResponse(updatedWorkspace);
	}

	@Override
	public void deleteWorkspace(Long id) {
		User currentUser = securityUtils.getCurrentUser();
		Workspace workSpace = workSpaceRepository.findByIdAndOwner(id, currentUser)
				.orElseThrow(() -> new ResourceNotFoundException("Workspace not found"));
		workSpaceRepository.delete(workSpace);
	}

}
