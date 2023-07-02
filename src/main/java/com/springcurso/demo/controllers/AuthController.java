package com.springcurso.demo.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.springcurso.demo.dao.UsuarioDao;
import com.springcurso.demo.models.Usuario;
import com.springcurso.demo.utils.Base64Util;
import com.springcurso.demo.utils.JWTUtils;

@RestController
public class AuthController {
	
	@Autowired
	private UsuarioDao usuarioDao;
	
	@Autowired
	private JWTUtils jwtUtils;
	
	@Autowired
	private Base64Util base64Util;

	@PostMapping("api/login")
	public String iniciarSesion(@RequestBody Usuario usuario) {
		Usuario usuarioLogueado = usuarioDao.obtenerUsuarioPorCredenciales(usuario);
		if(usuarioLogueado != null) {
			String tokenJwt = base64Util.create(String.valueOf(usuarioLogueado.getId()), usuarioLogueado.getEmail());
			return tokenJwt;
		}
		return "FAIL";
	}	
	
}
