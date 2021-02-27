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
}
