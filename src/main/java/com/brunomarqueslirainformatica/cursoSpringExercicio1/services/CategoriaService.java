package com.brunomarqueslirainformatica.cursoSpringExercicio1.services;

import java.io.Serializable;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.stereotype.Service;

import com.brunomarqueslirainformatica.cursoSpringExercicio1.domain.Categoria;
import com.brunomarqueslirainformatica.cursoSpringExercicio1.dto.CategoriaDTO;
import com.brunomarqueslirainformatica.cursoSpringExercicio1.repositories.CategoriaRepository;
import com.brunomarqueslirainformatica.cursoSpringExercicio1.services.exceptions.ObjectNotFoundException;

@Service
public class CategoriaService implements Serializable {
	private static final long serialVersionUID = 1L;
	
	@Autowired
	private CategoriaRepository repo;

	public Categoria find(Integer id) {
		Optional<Categoria> obj = repo.findById(id);
		return obj.orElseThrow(() -> new ObjectNotFoundException(
				"Objeto não encontrado! Id: " + id + ", Tipo: " + Categoria.class.getName(), null));
	}

	public List<Categoria> findAll() {
		return repo.findAll();
	}
	
	public Categoria insert(Categoria obj){
		obj.setId(null);
		return repo.save(obj);
	}
	
	public Categoria update(Categoria obj){
		Categoria newObj = find(obj.getId());
		updateData(newObj, obj);
		return repo.save(newObj);
	}
	
	public void delete(Integer id){
		find(id);
		repo.deleteById(id);
	}
	
	public Page<Categoria> findPage(Integer page, Integer linesPerPage, String direction, String orderBy){
		PageRequest pageRequest = PageRequest.of(page, linesPerPage, Direction.valueOf(direction), orderBy);
		return repo.findAll(pageRequest);
	}
	
	public Categoria fromDTO(CategoriaDTO obj) {
		return new Categoria(obj.getId(), obj.getNome());
	}

	private void updateData(Categoria newObj, Categoria obj) {
		newObj.setNome(obj.getNome());
	}
}
