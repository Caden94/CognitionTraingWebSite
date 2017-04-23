package com.caden.cognitionTraining2.authentication;

import com.caden.cognitionTraining2.model.User;

public interface UserService {
	void save(User user);

	User findByUsername(String username);
}
