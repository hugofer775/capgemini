package com.capgemini.repository;

import java.time.LocalDate;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.capgemini.model.entidade.Pix;

@Repository
public interface PagamentoRepository extends JpaRepository<Pix, Long>{

	@Query(value = "SELECT * FROM Pix p "
			+ "INNER JOIN Cliente c ON p.cliente_id = c.id "
			+ "WHERE DATE(p.data_pagamento) >= :dataInicio AND DATE(p.data_pagamento) <= :dataFim AND c.id = :id "
			+ "ORDER BY p.id ", nativeQuery = true)
	List<Pix> buscarPagamentoPorData(LocalDate dataInicio, LocalDate dataFim, long id);
}
