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
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
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
	
	//Método salva o novo pagamento pix
	public Pix salvaPagamento(PixIn pixIn) {
		return pagamentoRepository.save(convertToEntity(pixIn));
	}
	
	//Método busca os dados do pagamento pelo id, se não existir pagamento com esse id retorna status 404 mostrando que pagamento com esse id não existe 
	public ResponseEntity<Pix> buscaPagamentoPorId(long id) {
		Optional<Pix> pix = pagamentoRepository.findById(id);
		if(pix.isPresent()){
			return new ResponseEntity<Pix>(pix.get(), HttpStatus.OK);
		}
		else {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
	}
	
	//Método traz todos os pagamentos realizados pelos clientes e as porcentagens pagos levanto em consideração todos os pagamentos
	public List<Pix> buscaPagamentos() {
		return calculaPorcentagem(pagamentoRepository.findAll());
	}
	
	// Método pega a data enviada em String e converte ela para LocalDate, em seguida pega o primeiro dia e ultimo dia do mês da data enviada, depois passa os paramentos para fazer a busca usando os o primeiro e ultimo dia do mês e o id do cliente para trazer os pagamentos feitos no mês da data enviada e a porcentagem do valor do pagamento levando em consideração todos os pagamentos realizados naquele mês
	public List<Pix> buscaPagamentosPorDataEIdCliente(PagamentoFiltro filtro){
		LocalDate data = LocalDate.parse(filtro.getData(), DateTimeFormatter.ofPattern("dd/MM/yyyy"));
		LocalDate dataInicio = data.withDayOfMonth(1);
		LocalDate dataFim = data.withDayOfMonth(data.lengthOfMonth());
		return calculaPorcentagem(pagamentoRepository.buscarPagamentoPorData(dataInicio, dataFim, filtro.getClienteId()));
	}
	
	//Método responsavel por calcular porcentagens, primeiro somando todos os valores pagos e calculando a porcentagem paga pelo cliente naquela tranferencia 
	private List<Pix> calculaPorcentagem(List<Pix> pagamentos){
		double somaPagamentos = pagamentos.stream().map(pagamento -> pagamento.getValor()).mapToDouble(BigDecimal::doubleValue).sum();
		pagamentos.forEach(pagamento -> pagamento.setPorcentagem(new BigDecimal((100/somaPagamentos) * pagamento.getValor().doubleValue(), MathContext.DECIMAL64)));
		return pagamentos;
	}
	
	//Método responsável por converter Dto de entrada em entidade
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
