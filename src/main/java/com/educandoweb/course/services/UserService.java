package com.educandoweb.course.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.educandoweb.course.entities.User;
import com.educandoweb.course.repositories.UserRepository;
import com.educandoweb.course.services.exceptions.DatabaseException;
import com.educandoweb.course.services.exceptions.ResourceNotFoundException;


@Service
public class UserService {
 
	@Autowired
	private UserRepository repository;
	
	public List<User> findAll(){
		return repository.findAll();
	}
	
	public User findById(Long id) {
		Optional<User > obj = repository.findById(id);
		return obj.orElseThrow(() -> new ResourceNotFoundException(id));
	}
	
	// inserir no banco de dados
	public User insert(User obj) {
		
		return repository.save(obj);
	}
	
	public void delete(Long id){
	    try {
	        if(!repository.existsById(id)) throw new ResourceNotFoundException(id);
	        repository.deleteById(id);
	    }catch (ResourceNotFoundException e){
	        throw new DatabaseException(e.getMessage());
	    }
	}
	
	public User update(Long id, User obj) {
		User entity = repository.getReferenceById(id);
		updateData(entity, obj);
		return repository.save(entity);
	}

	private void updateData(User entity, User obj) {
		entity.setName(obj.getName());
		entity.setName(obj.getEmail());
		entity.setName(obj.getPhone());
	}
}
