package com.ergundeniz.userapp;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

public class CustomUserDetailsService implements UserDetailsService {
	
	private static final String USER_NOT_FOUND_MSG = "User with email: %s not found.";
	
	@Autowired
	private UserRepository repository;

	/*
	 * veritabanından uygun kullanıcıyı bulan metot. Kullanıcı 
	 * bulunamazsa UsernameNotFoundException fırlatır.
	 */
	@Override
	public UserDetails loadUserByUsername(String email) 
			throws UsernameNotFoundException {
		
		User user = repository.findByEmail(email);
		
		if (user == null) {
			throw new UsernameNotFoundException(String.format(USER_NOT_FOUND_MSG, email));
		}
		return new CustomUserDetails(user);
	}

}
