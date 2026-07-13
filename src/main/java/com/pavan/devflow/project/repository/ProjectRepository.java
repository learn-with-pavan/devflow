package com.pavan.devflow.project.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.pavan.devflow.project.entity.Project;
import com.pavan.devflow.workspace.entity.Workspace;
import java.util.List;
import java.util.Optional;

import com.pavan.devflow.user.entity.User;

public interface ProjectRepository extends JpaRepository<Project, Long> {
	boolean existsBySlug(String slug);

	List<Project> findAllByWorkspaceAndOwner(Workspace workspace, User owner);

	List<Project> findByOwner(User owner);

	Optional<Project> findByIdAndOwner(Long id, User owner);
}
