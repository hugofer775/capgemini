package com.capgemini.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.capgemini.model.entidade.Cliente;
import com.capgemini.repository.ClienteRepository;
import com.capgemini.service.ClienteService;

@RestController
@RequestMapping("/clientes")
public class ClienteController {
	
	@Autowired
	private ClienteRepository clienteRepository;
	
	@Autowired
	private ClienteService clienteService;
	
	@RequestMapping(method =  RequestMethod.GET) 
	public List<Cliente> lista() {
		return  clienteRepository.findAll();
	}
	
	@RequestMapping(method =  RequestMethod.POST) 
	@ResponseStatus(HttpStatus.CREATED)
	public Cliente adiciona(@RequestBody Cliente cliente) {
		return clienteRepository.save(cliente);
	}
	
	@RequestMapping(value = "/{id}", method =  RequestMethod.PUT) 
	public ResponseEntity<Cliente> atualiza(@PathVariable(value = "id") long id, @RequestBody Cliente newCliente) {
        return clienteService.atualiza(id, newCliente);
    }
	
	@RequestMapping(value = "/{id}", method =  RequestMethod.DELETE)
	public void deleta(@PathVariable(value = "id") long id) {
		clienteRepository.deleteById(id);
    }
	
	@RequestMapping(value = "/buscaClientePorCpf/{cpf}", method =  RequestMethod.GET)
	public List<Cliente> buscaClientePorCpf(@PathVariable(value = "cpf") String cpf) {
       return clienteRepository.buscaClientePorNome(cpf);
    }

}
