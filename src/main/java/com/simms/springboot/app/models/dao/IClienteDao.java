package com.simms.springboot.app.models.dao;

import java.util.List;

import com.simms.springboot.app.models.entity.Cliente;

public interface IClienteDao {
	
	public List<Cliente>findAll();

}
