package com.pavan.devflow.workspace.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.pavan.devflow.workspace.entity.Workspace;
import java.util.Optional;
import java.util.List;
import com.pavan.devflow.user.entity.User;



public interface WorkspaceRepository extends JpaRepository<Workspace, Long> {
	boolean existsBySlug(String slug);
	Optional<Workspace> findBySlug(String slug);
	Optional<Workspace> findByIdAndOwner(Long id, User owner);
	List<Workspace> findAllByOwner(User owner);
}
