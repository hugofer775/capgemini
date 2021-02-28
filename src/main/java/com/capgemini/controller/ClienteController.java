package com.capgemini.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.capgemini.model.in.ClienteIn;
import com.capgemini.model.out.ClienteOut;
import com.capgemini.repository.ClienteRepository;
import com.capgemini.service.ClienteService;

@RestController
@RequestMapping("/clientes")
public class ClienteController {
	
	@Autowired
	private ClienteRepository clienteRepository;
	
	@Autowired
	private ClienteService clienteService;
	
	//Método responsavel por listar todos os clientes
	@RequestMapping(method =  RequestMethod.GET) 
	public List<ClienteOut> lista() {
		return clienteService.buscaClientes();
	}
	
	//Método responsavel por buscar cliente pelo id
	@RequestMapping(value = "/buscaClientePorId/{id}", method =  RequestMethod.GET)
	public ResponseEntity<ClienteOut> buscaPorId(@PathVariable(value = "id") long id) {
		return clienteService.buscaClientePorId(id);
	}
	
	//Método responsavel salvar novo cliente
	@RequestMapping(method =  RequestMethod.POST) 
	@ResponseStatus(HttpStatus.CREATED)
	public ClienteOut adiciona(@RequestBody ClienteIn cliente) {
		return clienteService.salvaCliente(cliente);
	}
	
	//Método responsavel por atualizar os dados do cliente e retornar o cliente atualizado
	@RequestMapping(value = "/{id}", method =  RequestMethod.PUT) 
	public ResponseEntity<ClienteOut> atualiza(@PathVariable(value = "id") long id, @RequestBody ClienteIn newCliente) {
        return clienteService.atualiza(id, newCliente);
    }
	
	//Método responsavel por delete cliente pelo id
	@RequestMapping(value = "/{id}", method =  RequestMethod.DELETE)
	public void deleta(@PathVariable(value = "id") long id) {
		clienteRepository.deleteById(id);
    }
	
	//Método responsavel por buscar cliente pelo cpf
	@RequestMapping(value = "/buscaClientePorCpf/{cpf}", method =  RequestMethod.GET)
	public List<ClienteOut> buscaClientePorCpf(@PathVariable(value = "cpf") String cpf) {
       return clienteService.buscaClientePorNome(cpf);
    }

}
