package com.brunomarqueslirainformatica.cursoSpringExercicio1.services;

import java.io.Serializable;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.brunomarqueslirainformatica.cursoSpringExercicio1.domain.Pedido;
import com.brunomarqueslirainformatica.cursoSpringExercicio1.repositories.PedidoRepository;
import com.brunomarqueslirainformatica.cursoSpringExercicio1.services.exceptions.ObjectNotFoundException;

@Service
public class PedidoService implements Serializable {
	private static final long serialVersionUID = 1L;
	
	@Autowired
	private PedidoRepository repo;

	public Pedido find(Integer id) {
		Optional<Pedido> obj = repo.findById(id);
		return obj.orElseThrow(() -> new ObjectNotFoundException(
				"Objeto não encontrado! Id: " + id + ", Tipo: " + Pedido.class.getName(), null));
	}
}
