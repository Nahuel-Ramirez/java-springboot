package com.springcurso.demo.controllers;

import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.springcurso.demo.models.Usuario;

@RestController
public class UsuarioController {
	
	@RequestMapping(value = "user/{id}")
	public Usuario getUser(@PathVariable Long id) {
		Usuario usuario = new Usuario();
		usuario.setId(id);
		usuario.setNombre("Nahuel");
		usuario.setApellido("Ramirez");
		usuario.setEmail("nahuel-ramirez@hotmail.com");
		usuario.setTelefono("11736052");
		usuario.setPassword("123456789");
		return usuario;
	}
	
	@RequestMapping(value = "usuario1")
	public Usuario updateUser() {
		Usuario usuario = new Usuario();
		usuario.setNombre("Nahuel");
		usuario.setApellido("Ramirez");
		usuario.setEmail("nahuel-ramirez@hotmail.com");
		usuario.setTelefono("11736052");
		usuario.setPassword("123456789");
		return usuario;
	}
	
	@RequestMapping(value = "usuario2")
	public Usuario deleteUser() {
		Usuario usuario = new Usuario();
		usuario.setNombre("Nahuel");
		usuario.setApellido("Ramirez");
		usuario.setEmail("nahuel-ramirez@hotmail.com");
		usuario.setTelefono("11736052");
		usuario.setPassword("123456789");
		return usuario;
	}
		
	
}
