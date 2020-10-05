package com.mytask.app.controller;

import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.mytask.app.config.JwtUtils;
import com.mytask.app.model.ERole;
import com.mytask.app.model.JwtResponse;
import com.mytask.app.model.LoginRequest;
import com.mytask.app.model.MessageResponse;
import com.mytask.app.model.Role;
import com.mytask.app.model.SignupRequest;
import com.mytask.app.model.Task;
import com.mytask.app.model.User;
import com.mytask.app.repository.RoleRepository;
import com.mytask.app.repository.TaskRepository;
import com.mytask.app.repository.UserRepository;
import com.mytask.app.service.TaskService;
import com.mytask.app.service.UserDetailsImpl;



@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/api/auth")
public class AuthController {
	@Autowired
	AuthenticationManager authenticationManager;

	@Autowired
	UserRepository userRepository;

	@Autowired
	RoleRepository roleRepository;

	@Autowired
	PasswordEncoder encoder;

	@Autowired
	JwtUtils jwtUtils;
	
	@Autowired
	TaskRepository taskRepo;

	@Autowired 
	TaskService taskService;
	@PostMapping("/signin")
	public ResponseEntity<?> authenticateUser(@Valid @RequestBody LoginRequest loginRequest) {
		Authentication authentication = authenticationManager.authenticate(
				new UsernamePasswordAuthenticationToken(loginRequest.getUsername(), loginRequest.getPassword()));

		SecurityContextHolder.getContext().setAuthentication(authentication);
		String jwt = jwtUtils.generateJwtToken(authentication);
		
		UserDetailsImpl userDetails = (UserDetailsImpl) authentication.getPrincipal();		
		List<String> roles = userDetails.getAuthorities().stream()
				.map(item -> item.getAuthority())
				.collect(Collectors.toList());

		List<Task> listOfTask= taskService.getAllTasks().stream().filter(task->task.getRoles().equals(roles.get(0))).collect(Collectors.toList()); 
		return ResponseEntity.ok(new JwtResponse(jwt, 
												 userDetails.getId(), 
												 userDetails.getUsername(), 
												 userDetails.getEmail(), 
												 listOfTask));
		
		
	}

	@PostMapping("/signup")
	public ResponseEntity<?> registerUser(@Valid @RequestBody SignupRequest signUpRequest) throws Exception {
		if (userRepository.existsByUsername(signUpRequest.getUsername())) {
			return ResponseEntity
					.badRequest()
					.body(new MessageResponse("Error: Username is already taken!"));
		}

		if (userRepository.existsByEmail(signUpRequest.getEmail())) {
			return ResponseEntity
					.badRequest()
					.body(new MessageResponse("Error: Email is already in use!"));
		}

		
		User user = new User(signUpRequest.getUsername(), 
							 signUpRequest.getEmail(),
							 encoder.encode(signUpRequest.getPassword()));

		String strRoles = signUpRequest.getRoles();
		Set<Role> roles = new HashSet<>();
 
		if (strRoles == null) {
			Role userRole = roleRepository.findByName(ERole.Task_creator)
					.orElseThrow(() -> new RuntimeException("Error: Role is not found."));
			roles.add(userRole);
		} else {
			
		
				
				if(strRoles.equals("Validation_Officer")) {

					System.out.println(signUpRequest.getEmail()+ " " + signUpRequest.getPassword() + " "+signUpRequest.getRoles());
					
					Role adminRole = roleRepository.findByName(ERole.Validation_Officer)
							.orElseThrow(() -> new Exception("Error: Role is not found."));
					roles.add(adminRole);
				}
				
				if(strRoles.equals("Final_Approve")) {
					Role modRole = roleRepository.findByName(ERole.Final_Approve)
							.orElseThrow(() -> new Exception("Error: Role is not found."));
					roles.add(modRole);

				}
				if(strRoles.equals("Risk_Officer")) {
					Role modRole = roleRepository.findByName(ERole.Risk_Officer)
							.orElseThrow(() -> new Exception("Error: Role is not found."));
					roles.add(modRole);

				} else {
			
					Role userRole = roleRepository.findByName(ERole.Task_creator)
							.orElseThrow(() -> new Exception("Error: Role is not found."));
					roles.add(userRole);
				}
				
			
		}

		user.setRoles(roles);
		userRepository.save(user);

		return ResponseEntity.ok(new MessageResponse("User registered successfully!"));
	}
}