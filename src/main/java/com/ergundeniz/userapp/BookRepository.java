package com.ergundeniz.userapp;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface BookRepository extends JpaRepository<Book, Long> {
	
	/*
	 * parametre olarak aldığı mail bilgisiyle veri tabanına 
	 * sorgu çekip kullanıcının tüm kitapları getir.
	 */
	@Query("SELECT u FROM Book u WHERE u.email = ?1")
	List<Book> findAllBookByEmail(String email);

}
