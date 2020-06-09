package com.brunomarqueslirainformatica.cursoSpringExercicio1.services;

import java.io.Serializable;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.brunomarqueslirainformatica.cursoSpringExercicio1.domain.Cidade;
import com.brunomarqueslirainformatica.cursoSpringExercicio1.domain.Cliente;
import com.brunomarqueslirainformatica.cursoSpringExercicio1.domain.Endereco;
import com.brunomarqueslirainformatica.cursoSpringExercicio1.domain.enums.TipoCliente;
import com.brunomarqueslirainformatica.cursoSpringExercicio1.dto.ClienteDTO;
import com.brunomarqueslirainformatica.cursoSpringExercicio1.dto.ClienteNewDTO;
import com.brunomarqueslirainformatica.cursoSpringExercicio1.repositories.ClienteRepository;
import com.brunomarqueslirainformatica.cursoSpringExercicio1.repositories.EnderecoRepository;
import com.brunomarqueslirainformatica.cursoSpringExercicio1.services.exceptions.ObjectNotFoundException;

@Service
public class ClienteService implements Serializable {
	private static final long serialVersionUID = 1L;

	@Autowired
	private ClienteRepository repo;

	@Autowired
	private EnderecoRepository endrepo;
	
	public Cliente find(Integer id) {
		Optional<Cliente> obj = repo.findById(id);
		return obj.orElseThrow(() -> new ObjectNotFoundException(
				"Objeto não encontrado! Id: " + id + ", Tipo: " + Cliente.class.getName(), null));
	}
	
	@Transactional(propagation = Propagation.SUPPORTS)
	public Cliente insert(Cliente obj){
		obj.setId(null);
		obj = repo.save(obj);
		endrepo.saveAll(obj.getEnderecos());
		return obj;
	}
	
	public Cliente update(Cliente obj){
		Cliente newObj = find(obj.getId());
		updateData(newObj, obj);
		return repo.save(newObj);
	}
	
	public void delete(Integer id){
		find(id);
		try {
			repo.deleteById(id);
		}
		catch (DataIntegrityViolationException e) {
			throw new DataIntegrityViolationException("Não é possível excluir porque há entidades relacionadas.");
		}
	}
	
	public List<Cliente> findAll() {
		return repo.findAll();
	}
	
	public Page<Cliente> findPage(Integer page, Integer linesPerPage, String direction, String orderBy){
		PageRequest pageRequest = PageRequest.of(page, linesPerPage, Direction.valueOf(direction), orderBy);
		return repo.findAll(pageRequest);
	}
	
	public Cliente fromDTO(ClienteDTO objDto) {
		return new Cliente(objDto.getId(), objDto.getNome(), objDto.getEmail(), null, null);
	}
	
	public Cliente fromDTO(ClienteNewDTO objNewDTO) {
		Cliente cli = new Cliente(null, objNewDTO.getNome(), objNewDTO.getEmail(), objNewDTO.getCpfouCnpj(), TipoCliente.toEnum(objNewDTO.getTipo()));
		Cidade cid = new Cidade(objNewDTO.getCidadeId(), null, null);
		Endereco end = new Endereco(null, objNewDTO.getLogradouro(), objNewDTO.getNumero(), objNewDTO.getComplemento(), objNewDTO.getBairro(), objNewDTO.getCod_postal(), cli, cid);
		cli.getEnderecos().add(end);
		cli.getTelefones().add(objNewDTO.getTelefone1());
		if (objNewDTO.getTelefone2() != null) {
		cli.getTelefones().add(objNewDTO.getTelefone2());}
		if (objNewDTO.getTelefone3() != null) {
			cli.getTelefones().add(objNewDTO.getTelefone3());}
		return cli;
	}
	
	private void updateData(Cliente newObj, Cliente obj) {
		newObj.setNome(obj.getNome());
		newObj.setEmail(obj.getEmail());
	}
}
