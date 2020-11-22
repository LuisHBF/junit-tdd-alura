package br.com.caelum.leilao.servico;

import java.util.Comparator;
import java.util.Optional;

import br.com.caelum.leilao.dominio.Lance;
import br.com.caelum.leilao.dominio.Leilao;

public class Avaliador {

	private Double maiorLance; 
	private Double menorLance;
	
	public void avalia(Leilao leilao) {
		Optional<Lance> maiorLance = leilao.getLances().stream().max(Comparator.comparing(l -> l.getValor()));
		this.maiorLance = maiorLance.isPresent() ? maiorLance.get().getValor() : 0L;
		
		Optional<Lance> menorLance = leilao.getLances().stream().min(Comparator.comparing(l -> l.getValor()));
		this.menorLance = menorLance.isPresent() ? menorLance.get().getValor() : 0L;
 		
	}

	public Double getMaiorLance() {
		return maiorLance;
	}

	public Double getMenorLance() {
		return menorLance;
	}

	
	
}
