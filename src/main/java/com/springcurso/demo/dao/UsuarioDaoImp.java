package com.springcurso.demo.dao;

import java.util.List;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.springcurso.demo.models.Usuario;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;

@Repository
@Transactional
public class UsuarioDaoImp implements UsuarioDao{

	@PersistenceContext
	EntityManager em;
	
	@Override
	@Transactional
	public List<Usuario> getUsuarios() {
		String query = "FROM Usuario";
		var result = em.createQuery(query, Usuario.class).getResultList();
		System.out.println(result);
		return result;
	}

	@Override
	@Transactional
	public void eliminarUsuario(Long id) {
		Usuario usuario = em.find(Usuario.class, id);
		if (usuario != null) {
			em.remove(usuario);		
		}else {
			System.out.println("No se encontro el usuario");
		}
	}

	@Override
	@Transactional
	public void registrar(Usuario usuario) {
		em.merge(usuario);
	}
	
}
