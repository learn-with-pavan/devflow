package com.pavan.devflow.user.service;

import java.util.List;
import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import com.pavan.devflow.common.exception.EmailAlreadyExistsException;
import com.pavan.devflow.common.exception.ResourceNotFoundException;
import com.pavan.devflow.common.response.PageResponse;
import com.pavan.devflow.user.dto.CreateUserRequest;
import com.pavan.devflow.user.dto.UpdateUserRequest;
import com.pavan.devflow.user.dto.UserResponse;
import com.pavan.devflow.user.entity.User;
import com.pavan.devflow.user.mapper.UserMapper;
import com.pavan.devflow.user.repository.UserRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

	private final UserRepository userRepo;
	private final UserMapper mapper;

	@Override
	public UserResponse createUser(CreateUserRequest request) {
		User user = mapper.toEntity(request);

		if (userRepo.existsByEmail(request.getEmail())) {
			throw new EmailAlreadyExistsException("Email already exists: " + request.getEmail());
		}
		User savedUser = userRepo.save(user);

		return mapper.toResponse(savedUser);
	}

	@Override
	public UserResponse getUserById(Long id) {
		User user = userRepo.findById(id)
				.orElseThrow(() -> new ResourceNotFoundException("User not found with id : " + id));

		return mapper.toResponse(user);
	}

	@Override
	public PageResponse<UserResponse> getAllUsers(int page, int size, String sortBy, String direction) {
		Sort sort = direction.equalsIgnoreCase("desc") ? Sort.by(sortBy).descending() : Sort.by(sortBy).ascending();
		Pageable pageable = PageRequest.of(page, size, sort);
		Page<User> userPage = userRepo.findAll(pageable);
		List<UserResponse> users = userPage.getContent().stream().map(mapper::toResponse).toList();

		return PageResponse.<UserResponse>builder().content(users).page(userPage.getNumber()).size(userPage.getSize())
				.totalElements(userPage.getTotalElements()).totalPages(userPage.getTotalPages()).last(userPage.isLast())
				.build();
	}

	@Override
	public UserResponse updateUser(UpdateUserRequest request, Long id) {
		User user = userRepo.findById(id)
				.orElseThrow(() -> new ResourceNotFoundException("User not found with id : " + id));

		user.setFirstName(request.getFirstName());
		user.setLastName(request.getLastName());
		user.setEmail(request.getEmail());

		User updated = userRepo.save(user);

		return mapper.toResponse(updated);
	}

	@Override
	public void deleteUser(Long id) {
		User user = userRepo.findById(id)
				.orElseThrow(() -> new ResourceNotFoundException("User not found with id : " + id));

		userRepo.delete(user);
	}

}
