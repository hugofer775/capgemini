package com.capgemini.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.capgemini.model.entidade.Cliente;

@Repository
public interface ClienteRepository extends JpaRepository<Cliente, Long>{
	
	//Método consulta os clientes pelo cpf
	@Query(value = "SELECT * from Cliente where cpf like %:cpf%", nativeQuery = true)
	List<Cliente> buscaClientePorNome(String cpf);
}
