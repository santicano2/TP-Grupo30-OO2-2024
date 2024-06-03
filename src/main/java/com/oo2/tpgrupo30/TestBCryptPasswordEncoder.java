package com.oo2.tpgrupo30;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

public class TestBCryptPasswordEncoder {

	public static void main(String[] args) {
		BCryptPasswordEncoder pe = new BCryptPasswordEncoder();
		System.out.println("Contraseña de user: " + pe.encode("user"));
		System.out.println("Contraseña de admin: " + pe.encode("admin"));
	}
}