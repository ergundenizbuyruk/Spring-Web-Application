package com.ergundeniz;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase.Replace;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.test.annotation.Rollback;
import com.ergundeniz.userapp.Book;
import com.ergundeniz.userapp.BookRepository;
import com.ergundeniz.userapp.User;
import com.ergundeniz.userapp.UserRepository;

@DataJpaTest
@AutoConfigureTestDatabase(replace = Replace.NONE)
@Rollback(false)
public class UserRepositryTest {
	
	@Autowired
	private UserRepository repository;
	
	@Autowired
	private BookRepository bookRepository;
	
	@Autowired
	private TestEntityManager testEntityManager;
	
	/*
	 * Kullanıcıyı veritabanına kaydedip kaydetmediginin testi.
	 */
	@Test
	public void testCreateUser() {
		User user = new User();
		user.setEmail("ergunndenizbuyruk@gmail.com");
		user.setPassword("123456789");
		user.setFirstName("Ergün Deniz");
		user.setLastName("Buyruk");
		
		
		User savedUser = repository.save(user);
		
		User existUser = testEntityManager.find(User.class, savedUser.getId());
		
		assertThat(existUser.getEmail()).isEqualTo(user.getEmail());
	}
	
	/*
	 * Kitabı veritabanına kaydedip kaydetmediginin testi.
	 */
	@Test
	public void testCreateBook() {
		Book book = new Book();
		book.setName("Denemeler");
		book.setAuthor("Montaigne");
		book.setIsbn("154351431415");
		book.setEmail("ergun2127@gmail.com");
		
		
		Book savedBook = bookRepository.save(book);
		
		Book existBook = testEntityManager.find(Book.class, savedBook.getId());
		
		assertThat(existBook.getEmail()).isEqualTo(book.getEmail());
	}
	
	/*
	 * Kullanıcıyı veritabanından bulup bulamadıgının testi.
	 */
	@Test
	public void testFindUserByEmail() {
		String email = "aaa@aaa.com";
		
		User user = repository.findByEmail(email);
		
		assertThat(user).isNotNull();
	}
}
