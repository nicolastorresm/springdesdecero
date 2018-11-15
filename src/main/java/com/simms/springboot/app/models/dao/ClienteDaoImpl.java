package com.simms.springboot.app.models.dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.stereotype.Repository;


import com.simms.springboot.app.models.entity.Cliente;

@Repository
public class ClienteDaoImpl implements IClienteDao{
	
 @PersistenceContext
	private EntityManager em;	

	@SuppressWarnings("unchecked")

	@Override
	public List<Cliente> findAll() {
		
		return em.createQuery("from Cliente").getResultList();
	}
	
	@Override

	public Cliente findOne(Long id) {
		// TODO Auto-generated method stub
		return em.find(Cliente.class, id);
	}


	@Override
	public void save(Cliente cliente) {
		
		if(cliente.getId() != null && cliente.getId()>0) {
			em.merge(cliente); //edita
		}else {

			em.persist(cliente);//guarda
			
		}
	
	}



	@Override

	public void delete(Long id) {
		Cliente cliente = findOne(id);
		em.remove(cliente);
		
		// optimizado
		/*
		 * em.remover(findoner(id));
		 * */
		
	}
	


}
