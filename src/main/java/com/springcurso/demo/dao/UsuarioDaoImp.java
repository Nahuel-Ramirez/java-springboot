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
		return em.createQuery(query).getResultList();		
		
	}
	
	
	
}
