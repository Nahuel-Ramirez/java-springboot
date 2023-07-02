package com.springcurso.demo.dao;

import java.util.List;

import com.springcurso.demo.models.Usuario;

public interface UsuarioDao {
	
	List<Usuario> getUsuarios();

	void eliminarUsuario(Long id);

	void registrar(Usuario usuario);

	Usuario obtenerUsuarioPorCredenciales(Usuario usuario);


	
}
