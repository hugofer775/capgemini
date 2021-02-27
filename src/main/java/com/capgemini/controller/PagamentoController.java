package com.capgemini.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
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
	
	@RequestMapping(method =  RequestMethod.GET) 
	public List<Pix> lista() {
		return  pagamentoService.buscaPagamentos();
	}
	
	@RequestMapping(method =  RequestMethod.POST) 
	@ResponseStatus(HttpStatus.CREATED)
	public Pix adiciona(@RequestBody PixIn pixIn) {
		return pagamentoService.salvaPagamento(pixIn);
	}
	
	@RequestMapping(value = "/buscaPagamentosPorData", method =  RequestMethod.POST) 
	public List<Pix> buscaPagamentosPorData(@RequestBody PagamentoFiltro filtro) {
		return pagamentoService.buscaPagamentosPorDataECliente(filtro);
    }
}
