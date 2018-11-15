package com.simms.springboot.app.models.dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.simms.springboot.app.models.entity.Cliente;

@Repository
public class ClienteDaoImpl implements IClienteDao{
	
 @PersistenceContext
	private EntityManager em;	

	@SuppressWarnings("unchecked")
@Transactional(readOnly=true)
	@Override
	public List<Cliente> findAll() {
		
		return em.createQuery("from Cliente").getResultList();
	}
	
	@Override
	@Transactional(readOnly=true)
	public Cliente findOne(Long id) {
		// TODO Auto-generated method stub
		return em.find(Cliente.class, id);
	}

	@Transactional
	@Override
	public void save(Cliente cliente) {
		
		if(cliente.getId() != null && cliente.getId()>0) {
			em.merge(cliente); //edita
		}else {

			em.persist(cliente);//guarda
			
		}
	
	}



	@Override
	@Transactional
	public void delete(Long id) {
		Cliente cliente = findOne(id);
		em.remove(cliente);
		
		// optimizado
		/*
		 * em.remover(findoner(id));
		 * */
		
	}
	


}
