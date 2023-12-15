package com.shopme.admin.user;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase.Replace;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.test.annotation.Rollback;

import com.shopme.common.entity.Role;
import com.shopme.common.entity.User;

@DataJpaTest
@AutoConfigureTestDatabase(replace = Replace.NONE)
@Rollback(false)
public class UserRepositoryTests {

	
	@Autowired
	private UserRepository repo;
	
	@Autowired
	private TestEntityManager entityManager;
	
	@Test
	public void testCreateNewUserWithOneRole() {
		Role roleAdmin = entityManager.find(Role.class, 5);
		User userNamHM = new User("nam@gmail.com", "faiz2020", "faizan", "khan");
		userNamHM.addRole(roleAdmin);
		
		User savedUser = repo.save(userNamHM);
		
		assertThat(savedUser.getId()).isGreaterThan(0);
	}
	
	@Test
	public void testCreateNewUserWithTwoRole() {
	
		User userRavi = new User("ravi@gmail.com", "ravi2023", "Ravi", "Kumar");
		Role roleEditor = new Role(2);
		
		Role roleAssistant = new Role(4);
		
		
		userRavi.addRole(roleAssistant);
		userRavi.addRole(roleEditor);
		
		User saveUser = repo.save(userRavi);
		
		assertThat(saveUser.getId()).isGreaterThan(0);
	}
	
	@Test 
	public void testListAllUsers() {
		Iterable<User> listUsers = repo.findAll();
		listUsers.forEach(user -> System.out.println(user));
		
	}
	
	@Test
	public void testGetUserById() {
		User userRavi = repo.findById(4).get();
		System.out.println(userRavi);
		assertThat(userRavi).isNotNull();
		
	}
	
	
	@Test
	public void testUpdateUserDetails() {
		User userRavi = repo.findById(4).get();
		userRavi.setEnabled(true);
		userRavi.setEmail("ravi2023@mail.com");
		
		repo.save(userRavi);
	}
	
	   
}
