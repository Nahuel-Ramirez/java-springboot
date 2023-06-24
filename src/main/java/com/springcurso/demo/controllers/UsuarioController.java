package com.springcurso.demo.controllers;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.springcurso.demo.dao.UsuarioDao;
import com.springcurso.demo.models.Usuario;

@RestController
public class UsuarioController {
	
	@Autowired
	private UsuarioDao usuarioDao;

	
	@GetMapping("api/usuarios")
	@ResponseBody
	public List<Usuario> getUsuarios() {
		return usuarioDao.getUsuarios();
	}		
	
}
