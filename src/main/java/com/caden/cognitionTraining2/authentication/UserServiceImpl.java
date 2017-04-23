package com.caden.cognitionTraining2.authentication;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.caden.cognitionTraining2.model.User;
import com.caden.cognitionTraining2.repository.RoleRepository;
import com.caden.cognitionTraining2.repository.UserRepository;

import java.util.HashSet;

@Service
public class UserServiceImpl implements UserService {
	@Autowired
	private UserRepository userRepository;
	@Autowired
	private RoleRepository roleRepository;

	@Override
	public void save(User user) {
		user.setPassword(user.getPassword());
		user.setRoles(new HashSet<>(roleRepository.findAll()));
		userRepository.save(user);
	}

	@Override
	public User findByUsername(String username) {
		return userRepository.findByUsername(username);
	}
}
