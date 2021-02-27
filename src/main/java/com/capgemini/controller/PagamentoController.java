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

import com.capgemini.model.entidade.Pix;
import com.capgemini.model.filtro.PagamentoFiltro;
import com.capgemini.model.in.PixIn;
import com.capgemini.service.PagamentoService;

@RestController
@RequestMapping("/pagamentos")
public class PagamentoController {
	
	@Autowired
	private PagamentoService pagamentoService;
	
	//Método responsavel por buscar os pagamentos de todos os clientes, com a porcentagem do valor pago em cada transação levando em conta o montante
	@RequestMapping(method =  RequestMethod.GET) 
	public List<Pix> lista() {
		return pagamentoService.buscaPagamentos();
	}
		
	//Método responsavel por buscar pagamento pelo id
	@RequestMapping(value = "/buscaPagamentoPorId/{id}", method =  RequestMethod.GET)
	public ResponseEntity<Pix> buscaPorId(@PathVariable(value = "id") long id) {
		return pagamentoService.buscaPagamentoPorId(id);
	}
	
	//Método responsavel por salvar os pagamentos dos clientes e retorna dados do pagamento salvo
	@RequestMapping(method =  RequestMethod.POST) 
	@ResponseStatus(HttpStatus.CREATED)
	public Pix salvaPagamento(@RequestBody PixIn pixIn) {
		return pagamentoService.salvaPagamento(pixIn);
	}
		
	//Método responsavel por buscar os pagamentos dos clientes usando data de referencia e o id do cliente, com a porcentagem do valor de cada pagamento levando em conta todos os pagamentos realizados pelo cliente no mês
	@RequestMapping(value = "/buscaPagamentosPorDataEIdCliente", method =  RequestMethod.POST) 
	public List<Pix> buscaPagamentosPorDataEIdCliente(@RequestBody PagamentoFiltro filtro) {
		return pagamentoService.buscaPagamentosPorDataEIdCliente(filtro);
    }
}
