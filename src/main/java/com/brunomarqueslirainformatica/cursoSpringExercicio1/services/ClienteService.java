package com.brunomarqueslirainformatica.cursoSpringExercicio1.services;

import java.io.Serializable;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.brunomarqueslirainformatica.cursoSpringExercicio1.domain.Cliente;
import com.brunomarqueslirainformatica.cursoSpringExercicio1.repositories.ClienteRepository;
import com.brunomarqueslirainformatica.cursoSpringExercicio1.services.exceptions.ObjectNotFoundException;

@Service
public class ClienteService implements Serializable {
	private static final long serialVersionUID = 1L;

	@Autowired
	private ClienteRepository repo;

	public Cliente find(Integer id) {
		Optional<Cliente> obj = repo.findById(id);
		return obj.orElseThrow(() -> new ObjectNotFoundException(
				"Objeto não encontrado! Id: " + id + ", Tipo: " + Cliente.class.getName(), null));
	}
	
	public List<Cliente> findAll() {
		return repo.findAll();
	}
	
	public Cliente insert(Cliente obj){
		obj.setId(null);
		return repo.save(obj);
	}
	
	public Cliente update(Cliente obj){
		repo.findById(obj.getId());
		return repo.save(obj);
	}
	
	public void delete(Integer id){
		find(id);
		repo.deleteById(id);
	}

}
