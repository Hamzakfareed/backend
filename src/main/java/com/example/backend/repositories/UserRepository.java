package com.example.backend.repositories;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.backend.modals.User;

//REPOSITORY CLASS WHICH COMMUNICATE WITH USER TABLE IN DATABASE 
@Repository
public interface UserRepository extends JpaRepository<User,Long>{

	Optional<User> findByEmail(String username);

}
