package br.edu.utfpr.dv.bcb;

import java.util.Date;

public class ItemBCB {
	
	private Date data;
	private double valor;
	
	public ItemBCB(Date data, double valor) {
		this.setData(data);
		this.setValor(valor);
	}
	
	public Date getData() {
		return data;
	}
	public void setData(Date data) {
		this.data = data;
	}
	public double getValor() {
		return valor;
	}
	public void setValor(double valor) {
		this.valor = valor;
	}

}
