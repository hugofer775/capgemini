package com.capgemini.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.capgemini.model.entidade.Cliente;
import com.capgemini.repository.ClienteRepository;

@Service
public class ClienteService {
	
	@Autowired
	private ClienteRepository clienteRepository;

	//Método responsavel por atualizar dados do cliente, primeiro ele busca o cliente pelo id e atualizar os dados se o cliente existir, se não retorna status 404 mostrando que não existe cliente cadastrado com esse id 
	public ResponseEntity<Cliente> atualiza(long id, Cliente newCliente) {
        Optional<Cliente> oldPessoa = clienteRepository.findById(id);
        if(oldPessoa.isPresent()){
        	Cliente cliente = oldPessoa.get();
        	if(newCliente.getNome() != null) {
        		cliente.setNome(newCliente.getNome());
        	} 
        	if(newCliente.getCpf() != null) {
        		cliente.setCpf(newCliente.getCpf());
        	}
            clienteRepository.save(cliente);
            return new ResponseEntity<Cliente>(cliente, HttpStatus.OK);
        }
        else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }
	
	//Método busca os dados do cliente pelo id, se não existir cliente com esse id retorna status 404 mostrando que aquele id não existe 
	public ResponseEntity<Cliente> buscaClientePorId(long id) {
        Optional<Cliente> cliente = clienteRepository.findById(id);
        if(cliente.isPresent()){
            return new ResponseEntity<Cliente>(cliente.get(), HttpStatus.OK);
        }
        else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }
}
