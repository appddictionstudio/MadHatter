package com.Madhatter.MadHatter.Repositories;


import java.util.List;
import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.Madhatter.MadHatter.models.User;


@Repository
public interface UserRepository extends JpaRepository<User, Long> {
	
    Optional<User> findByEmail(String email);

    Optional<User> findByUsernameOrEmail(String username, String email);

//    Optional<User> findByUsername(String username);
    
    Optional<User> findById(Integer Id);
    
    User findById(long Id);
    
    User findByUsername(String username);

    Boolean existsByUsername(String username);

    Boolean existsByEmail(String email);
    
    @Query("select u from User u")
    public List<User> getAllUsers();

	public List<User> findByNameContainingIgnoreCase(String searchString);
	

	public Page<User> findByNameContainingIgnoreCaseOrderByName(String searchString, Pageable pageable);
	

	public Page<User> findAll(Pageable pageable);
	
	@Query("SELECT u from User u WHERE u.username LIKE ?1")
	public User findByUsernameContainingIgnoreCase(String searchString);
	
	@Query("SELECT u from User u WHERE u.email LIKE ?1")
	public User findByEmailContainingIgnoreCase(String searchString);

}