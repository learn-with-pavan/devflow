package com.pavan.devflow.project.service;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.pavan.devflow.common.exception.ResourceNotFoundException;
import com.pavan.devflow.common.util.SlugService;
import com.pavan.devflow.project.dto.CreateProjectRequest;
import com.pavan.devflow.project.dto.ProjectResponse;
import com.pavan.devflow.project.dto.UpdateProjectRequest;
import com.pavan.devflow.project.entity.Project;
import com.pavan.devflow.project.mapper.ProjectMapper;
import com.pavan.devflow.project.repository.ProjectRepository;
import com.pavan.devflow.security.config.util.SecurityUtils;
import com.pavan.devflow.user.entity.User;
import com.pavan.devflow.workspace.entity.Workspace;
import com.pavan.devflow.workspace.service.WorkspaceAccessService;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class ProjectServiceImpl implements ProjectService {

	private final ProjectRepository projectRepository;
	private final ProjectMapper projectMapper;
	private final WorkspaceAccessService workspaceAccessService;
	private final SecurityUtils securityUtils;
	private final SlugService slugService;

	@Override
	public ProjectResponse createProject(CreateProjectRequest request) {
		User currentUser = securityUtils.getCurrentUser();
		Workspace workSpace = workspaceAccessService.getOwnedWorkspace(request.getWorkspaceId());
		String slug = slugService.generateProjectSlug(request.getName());
		Project project = projectMapper.toEntity(request, workSpace, currentUser, slug);

		Project response = projectRepository.save(project);
		return projectMapper.toResponse(response);
	}

	@Override
	@Transactional(readOnly = true)
	public ProjectResponse getProject(Long id) {
		User currentUser = securityUtils.getCurrentUser();
		Project project = projectRepository.findByIdAndOwner(id, currentUser)
				.orElseThrow(() -> new ResourceNotFoundException("Project not found"));
		return projectMapper.toResponse(project);
	}

	@Override
	@Transactional(readOnly = true)
	public List<ProjectResponse> getProjects(Long workspaceId) {
		Workspace workSpace = workspaceAccessService.getOwnedWorkspace(workspaceId);
		User currentUser = securityUtils.getCurrentUser();

		return projectRepository.findAllByWorkspaceAndOwner(workSpace, currentUser).stream()
				.map(projectMapper::toResponse).collect(Collectors.toList());
	}

	@Override
	public ProjectResponse updateProject(Long id, UpdateProjectRequest request) {
		User currentUser = securityUtils.getCurrentUser();

		Project project = projectRepository.findByIdAndOwner(id, currentUser)
				.orElseThrow(() -> new ResourceNotFoundException("Project not found"));

		project.setName(request.getName());
		project.setDescription(request.getDescription());
		project.setPriority(request.getPriority());
		project.setStatus(request.getStatus());
		project.setColor(request.getColor());
		project.setIcon(request.getIcon());
		project.setStartDate(request.getStartDate());
		project.setEndDate(request.getEndDate());
		project.setArchived(request.getArchived());
		
		Project updatedProject = projectRepository.save(project);
		return projectMapper.toResponse(updatedProject);

	}

	@Override
	public void deleteProject(Long id) {
		User currentUser = securityUtils.getCurrentUser();

		Project project = projectRepository.findByIdAndOwner(id, currentUser)
				.orElseThrow(() -> new ResourceNotFoundException("Project not found"));

		projectRepository.delete(project);
	}

	@Override
	public void archiveProject(Long id) {
		User currentUser = securityUtils.getCurrentUser();

		Project project = projectRepository.findByIdAndOwner(id, currentUser)
				.orElseThrow(() -> new ResourceNotFoundException("Project not found"));

		project.setArchived(true);

		projectRepository.save(project);

	}

}
