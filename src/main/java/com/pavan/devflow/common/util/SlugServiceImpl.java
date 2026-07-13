package com.pavan.devflow.common.util;

import org.springframework.stereotype.Service;

import com.pavan.devflow.project.repository.ProjectRepository;
import com.pavan.devflow.workspace.repository.WorkspaceRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class SlugServiceImpl implements SlugService {
	private final WorkspaceRepository workspaceRepository;

	private final ProjectRepository projectRepository;

	@Override
	public String generateWorkspaceSlug(String name) {

		String baseSlug = SlugUtil.toSlug(name);

		String slug = baseSlug;

		int counter = 1;

		while (workspaceRepository.existsBySlug(slug)) {

			slug = baseSlug + "-" + counter++;

		}

		return slug;
	}

	@Override
	public String generateProjectSlug(String name) {

		String baseSlug = SlugUtil.toSlug(name);

		String slug = baseSlug;

		int counter = 1;

		while (projectRepository.existsBySlug(slug)) {

			slug = baseSlug + "-" + counter++;

		}

		return slug;
	}
}
