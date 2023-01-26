package com.jlfilho.course.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;

import com.jlfilho.course.domain.User;
import com.jlfilho.course.repositories.UserRepository;
import com.jlfilho.course.services.exceptions.DatabaseException;
import com.jlfilho.course.services.exceptions.ResourceNotFoundException;

@Service
public class UserService {
	
	@Autowired
	private UserRepository userRepository;
	
	public List<User> findAll(){
		return userRepository.findAll();
	}
	
	public User findById(Long id) {
		Optional<User> user = userRepository.findById(id); 
		return user.orElseThrow(() -> new ResourceNotFoundException(id)
				);
	}
	
	public User insert(User user) {
		return userRepository.save(user);
	}
	
	public void delete(Long id) {
		try {
			userRepository.deleteById(id);			
		} catch (EmptyResultDataAccessException e) {
			throw new ResourceNotFoundException(id);  
		} catch (DataIntegrityViolationException e) {
			throw new DatabaseException(e.getMessage());  
		}
	}
	
	public User update(Long id, User user) {
		User u = userRepository.getReferenceById(id);
		updateData(u, user);
		return userRepository.save(u);
	}

	private void updateData(User u, User user) {
		u.setName(user.getName());
		u.setEmail(user.getEmail());
		u.setPhone(user.getPhone());
	}

}
