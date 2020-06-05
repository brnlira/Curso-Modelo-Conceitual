package com.brunomarqueslirainformatica.cursoSpringExercicio1.dto;

import java.io.Serializable;

import com.brunomarqueslirainformatica.cursoSpringExercicio1.domain.Pedido;

public class PedidoDTO implements Serializable {
	private static final long serialVersionUID = 1L;

	private Integer id;
	
	public PedidoDTO() {
	}
	
	public PedidoDTO(Pedido obj) {
		id = obj.getId();
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}
}
