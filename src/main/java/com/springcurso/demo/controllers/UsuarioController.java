package com.springcurso.demo.controllers;


import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.argon2.Argon2PasswordEncoder;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.springcurso.demo.dao.UsuarioDao;
import com.springcurso.demo.models.Usuario;





@RestController
public class UsuarioController {
	
	@Autowired
	private UsuarioDao usuarioDao;

	@GetMapping("api/usuarios/{id}")
	@ResponseBody
	public Usuario getUsuario(@PathVariable Long id) {
		Usuario usuario = new Usuario();
		usuario.setId(id);
		usuario.setNombre("Nahuel");
		usuario.setApellido("Ramirez");
		usuario.setEmail("nahuel-ramirez@hotmail.com");
		usuario.setTelefono("1152635845");
		usuario.setPassword("123");
		return usuario;
	}
	
	@GetMapping("api/usuarios")
	@ResponseBody
	public List<Usuario> getUsuarios() {
		return usuarioDao.getUsuarios();
	}		
	
	@PostMapping("api/usuarios")
	public void registrarUsuario(@RequestBody Usuario usuario) {
		
		//instancia de Argon2
		Argon2PasswordEncoder encoder = new Argon2PasswordEncoder();
		
		//hasheo la contraseña
		String passwordHash = encoder.encode(usuario.getPassword());
		usuario.setPassword(passwordHash);
		usuarioDao.registrar(usuario);
	}	
	
	@DeleteMapping("api/usuarios/{id}")
	@ResponseBody
	public void eliminar(@PathVariable Long id) {
		usuarioDao.eliminarUsuario(id);
	}
	
	
}
