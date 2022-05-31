package com.ergundeniz.userapp;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface UserRepository extends JpaRepository<User, Long> {
	
	/*
	 * parametre olarak aldığı mail bilgisiyle veri tabanına sorgu çekip uygun kullanıcıyı getir.
	 */
	@Query("SELECT u FROM User u WHERE u.email = ?1")
	User findByEmail(String email);
	
}
