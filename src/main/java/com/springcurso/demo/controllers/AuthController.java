package com.springcurso.demo.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.springcurso.demo.dao.UsuarioDao;
import com.springcurso.demo.models.Usuario;

@RestController
public class AuthController {
	
	@Autowired
	private UsuarioDao usuarioDao;

	@PostMapping("api/login")
	public String registrarUsuario(@RequestBody Usuario usuario) {
		if(usuarioDao.verificarEmailPassword(usuario)) {
			return "OK";
		}
		return "FAIL";
	}	
	
}
