package com.ergundeniz.controller;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import com.ergundeniz.userapp.Book;
import com.ergundeniz.userapp.BookRepository;
import com.ergundeniz.userapp.User;
import com.ergundeniz.userapp.UserRepository;

/**
 * 
 * @author ergun
 * Control sınıfı.
 *
 */
@Controller
public class AppController {
	

	/*
	 * users veritabanı için kullanılan user repository.
	 */
	@Autowired
	private UserRepository repository;
	
	/*
	 * books veritabanı için kullanılan book repository.
	 */
	@Autowired
	private BookRepository bookRepository;
	
	/*
	 * ana sayfa
	 */
	@GetMapping("")
	public String viewHomePage() {
		return "index";
	}
	
	/*
	 * kayıt olma ekranı.
	 */
	@GetMapping("/register")
	public String showSignUpForm(Model model) {
		model.addAttribute("user", new User());
		return "signup_form";
	}
	
	/*
	 * kitap ekleme ekranı
	 */
	@GetMapping("/add_book")
	public String showAddBookPage(Model model) {
		model.addAttribute("book", new Book());
		return "add_book";
	}
	
	/*
	 * kitapları listeleme ekranı.
	 */
	@GetMapping("/my_books")
	public String showBookList(Model model) {
		// bookRepository.findAllBookByEmail(user.getEmail());
		List<Book> listAllBooks = bookRepository.findAll();
		model.addAttribute("listAllBooks", listAllBooks);
		return "books";
	}
	
	/*
	 * sisteme kayıt olan userı veritabanına kaydet. 
	 */
	@PostMapping("/process_register")
	public String processRegistration(User user) {
		BCryptPasswordEncoder bCryptPasswordEncoder = new BCryptPasswordEncoder();
		user.setPassword(bCryptPasswordEncoder.encode(user.getPassword()));
		repository.save(user);
		return "register_success";
	}
	
	/*
	 * kayıt edilen kitabı veritabanına kaydet
	 */
	@PostMapping("/process_add_book")
	public String processAddBook(Book book) {
		bookRepository.save(book);
		return "process_add_book";
	}
		
	/*
	 * Tüm kullanıcıları listele.
	 */
	@GetMapping("/list_users")
	public String viewUsersList(Model model) {
		List<User> listAllUsers = repository.findAll();
		model.addAttribute("listUsers", listAllUsers);
		return "users";
	}

}
