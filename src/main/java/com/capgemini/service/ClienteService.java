package com.capgemini.service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.capgemini.model.entidade.Cliente;
import com.capgemini.model.in.ClienteIn;
import com.capgemini.model.out.ClienteOut;
import com.capgemini.repository.ClienteRepository;

@Service
public class ClienteService {
	
	@Autowired
	private ClienteRepository clienteRepository;
	
	//Método traz todos os clientes cadastrados
	public List<ClienteOut> buscaClientes() {
		return clienteRepository.findAll().stream().map(cliente -> convertToDto(cliente)).collect(Collectors.toList());
	}

	//Método salva o novo pagamento pix e retorna o pagamento salvo
	public ClienteOut salvaCliente(ClienteIn clienteIn) {
		return convertToDto(clienteRepository.save(convertToEntity(clienteIn)));
	}
	
	//Método responsavel por atualizar dados do cliente, primeiro ele busca o cliente pelo id e atualizar os dados se o cliente existir, se não retorna status 404 mostrando que não existe cliente cadastrado com esse id 
	public ResponseEntity<ClienteOut> atualiza(long id, ClienteIn newCliente) {
        Optional<Cliente> oldCliente = clienteRepository.findById(id);
        if(oldCliente.isPresent()){
        	Cliente cliente = oldCliente.get();
        	if(newCliente.getNome() != null) {
        		cliente.setNome(newCliente.getNome());
        	} 
        	if(newCliente.getCpf() != null) {
        		cliente.setCpf(newCliente.getCpf());
        	}
            clienteRepository.save(cliente);
            return new ResponseEntity<ClienteOut>(convertToDto(cliente), HttpStatus.OK);
        }
        else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }
	
	//Método busca os dados do cliente pelo id, se não existir cliente com esse id retorna status 404 mostrando que aquele id não existe 
	public ResponseEntity<ClienteOut> buscaClientePorId(long id) {
        Optional<Cliente> cliente = clienteRepository.findById(id);
        if(cliente.isPresent()){
            return new ResponseEntity<ClienteOut>(convertToDto(cliente.get()), HttpStatus.OK);
        }
        else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }
	
	//Método traz cliente pelo cpf
	public List<ClienteOut> buscaClientePorNome(String cpf) {
		return clienteRepository.buscaClientePorNome(cpf).stream().map(cliente -> convertToDto(cliente)).collect(Collectors.toList());
	}
	
	//Método responsável por converter Dto de entrada em entidade
	private Cliente convertToEntity(ClienteIn clienteIn) {
		ModelMapper modelMapper = new ModelMapper();
		return modelMapper.map(clienteIn, Cliente.class);
	}
		
	
	//Método responsável por converter entidade em Dto de saida
	private ClienteOut convertToDto(Cliente cliente) {
		ModelMapper modelMapper = new ModelMapper();
		return modelMapper.map(cliente, ClienteOut.class);
	}
}
