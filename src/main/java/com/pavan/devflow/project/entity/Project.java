package com.pavan.devflow.project.entity;

import java.time.LocalDate;

import com.pavan.devflow.common.entity.AuditableEntity;
import com.pavan.devflow.project.enums.ProjectPriority;
import com.pavan.devflow.project.enums.ProjectStatus;
import com.pavan.devflow.user.entity.User;
import com.pavan.devflow.workspace.entity.Workspace;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.FetchType;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "projects")
@Getter
@Setter
@NoArgsConstructor
public class Project extends AuditableEntity {

	@Column(nullable = false, length = 150)
	private String name;

	@Column(nullable = false, unique = true)
	private String slug;

	@Column(columnDefinition = "TEXT")
	private String description;

	@Enumerated(EnumType.STRING)
	@Column(nullable = false)
	private ProjectStatus status;

	@Enumerated(EnumType.STRING)
	@Column(nullable = false)
	private ProjectPriority priority;

	@Column(length = 20)
	private String color;

	@Column(length = 100)
	private String icon;

	private LocalDate startDate;
	private LocalDate endDate;

	@Column(nullable = false)
	private boolean archived = false;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "workspace_id", nullable = false)
	private Workspace workspace;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "owner_id", nullable = false)
	private User owner;
}
