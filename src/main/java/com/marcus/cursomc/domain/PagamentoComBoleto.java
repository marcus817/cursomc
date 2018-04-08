package com.marcus.cursomc.domain;

import java.util.Date;

import javax.persistence.Entity;

import com.marcus.cursomc.resources.enums.EstadoPagamento;

@Entity
public class PagamentoComBoleto extends Pagamento {

	private static final long serialVersionUID = 1L;
	
	private Date dataVencimento;
	private Date dataPagamento;
	
	
	
	public PagamentoComBoleto() {
		super();
		
	}
	public PagamentoComBoleto(Integer id, EstadoPagamento estadoPagamento, Pedido pedido, Date dataVencimento, Date dataPagemento) {
		super(id, estadoPagamento, pedido);
		this.dataPagamento = dataPagemento;
		this.dataVencimento = dataVencimento;
	}
	public Date getDataVencimento() {
		return dataVencimento;
	}
	public void setDataVencimento(Date dataVencimento) {
		this.dataVencimento = dataVencimento;
	}
	public Date getDataPagamento() {
		return dataPagamento;
	}
	public void setDataPagamento(Date dataPagamento) {
		this.dataPagamento = dataPagamento;
	}
	
	
}
