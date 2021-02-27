package com.capgemini.service;

import java.math.BigDecimal;
import java.math.MathContext;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Optional;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.capgemini.model.entidade.Cliente;
import com.capgemini.model.entidade.Pix;
import com.capgemini.model.filtro.PagamentoFiltro;
import com.capgemini.model.in.PixIn;
import com.capgemini.repository.ClienteRepository;
import com.capgemini.repository.PagamentoRepository;

@Service
public class PagamentoService {
	
	@Autowired
	private PagamentoRepository pagamentoRepository;
	
	@Autowired
	private ClienteRepository clienteRepository;
	
	public Pix salvaPagamento(PixIn pixIn) {
		return pagamentoRepository.save(convertToEntity(pixIn));
	}
	
	public List<Pix> buscaPagamentos() {
		return calculaPorcentagem(pagamentoRepository.findAll());
	}
	
	public List<Pix> buscaPagamentosPorDataECliente(PagamentoFiltro filtro){
		LocalDate data = LocalDate.parse(filtro.getData(), DateTimeFormatter.ofPattern("dd/MM/yyyy"));
		LocalDate dataInicial = data.withDayOfMonth(1);
		LocalDate dataFim = data.withDayOfMonth(data.lengthOfMonth());
		return calculaPorcentagem(pagamentoRepository.buscarPagamentoPorData(dataInicial, dataFim, filtro.getId()));
	}
	
	private List<Pix> calculaPorcentagem(List<Pix> pagamentos){
		double somaPagamentos = pagamentos.stream().map(pagamento -> pagamento.getValor()).mapToDouble(BigDecimal::doubleValue).sum();
		pagamentos.forEach(pagamento -> pagamento.setPorcentagem(new BigDecimal((100/somaPagamentos) * pagamento.getValor().doubleValue(), MathContext.DECIMAL64)));
		return pagamentos;
	}
	
	private Pix convertToEntity(PixIn pixIn){
		ModelMapper modelMapper = new ModelMapper();
		Pix pix = modelMapper.map(pixIn, Pix.class);
		pix.setDataPagamento(LocalDateTime.now());
		Optional<Cliente> cliente = clienteRepository.findById(pixIn.getClienteId());
		if(cliente.isPresent()) {
			pix.setCliente(cliente.get());
		}
	    return pix;
	}
	
}
