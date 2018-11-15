package com.simms.springboot.app.controllers;


import java.util.Map;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.bind.support.SessionStatus;

import com.simms.springboot.app.models.dao.IClienteDao;
import com.simms.springboot.app.models.entity.Cliente;

@Controller
@SessionAttributes("cliente")
public class ClienteController {
	
	@Autowired
	private IClienteDao clienteDao;
	
	
	@RequestMapping(value="/listar", method=RequestMethod.GET)
	public String listar(Model model) {
		model.addAttribute("titulo", "Listado de clientes");
		model.addAttribute("clientes",clienteDao.findAll());
		return "listar";
		
	}
	
	
	// mostrar formulario guardar cliente al usuario
	
	@RequestMapping(value="/form")
	public String crear (Map<String, Object> model) {
		Cliente cliente = new Cliente();
		model.put("cliente", cliente);
		model.put("titulo", "Formulario de Cliente");
		return "form";
	}
	
	
	@RequestMapping(value="/form/{id}")
	public String editar (@PathVariable(value="id") Long id,  Map<String, Object> model) {
		Cliente cliente = null;
		if (id>0) {
			cliente = clienteDao.findOne(id);
		}else {
			return "redirect:/listar";
		}
		
		model.put("cliente", cliente);
		model.put("titulo", "Editar cliente");
		
		return "form";
		
	}
	
	//enviar desde submit los datos del formulario ingresado por usuario
	@RequestMapping(value="/form", method=RequestMethod.POST)
	public String guardar(@Valid Cliente cliente, BindingResult result, Model model,SessionStatus status  ) {
		model.addAttribute("titulo", "Formulario de Cliente");
		if (result.hasErrors()) {
			return "form";
		}
		clienteDao.save(cliente);
		status.setComplete();
		
		return "redirect:listar";
	}
	
	@RequestMapping(value="/eliminar/{id}")
	public String eliminar(@PathVariable(value="id") Long id){
		if (id>0) {
			clienteDao.delete(id);
		}
		return "redirect:/listar";
		
	}
  
}
