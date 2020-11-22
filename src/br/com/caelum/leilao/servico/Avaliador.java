package br.com.caelum.leilao.servico;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import br.com.caelum.leilao.dominio.Lance;
import br.com.caelum.leilao.dominio.Leilao;

public class Avaliador {

	private Double maiorLance; 
	private Double menorLance;
	private Double mediaLances;
	private List<Lance> tresMaioresLances = new ArrayList<Lance>();
	
	public void avalia(Leilao leilao) {
		
		if(leilao.getLances().size() == 0) {
			throw new RuntimeException("Não é possível avaliar um leião sem lances!");
		}
		
		Optional<Lance> maiorLance = leilao.getLances().stream().max(Comparator.comparing(l -> l.getValor()));
		this.maiorLance = maiorLance.isPresent() ? maiorLance.get().getValor() : 0L;
		
		Optional<Lance> menorLance = leilao.getLances().stream().min(Comparator.comparing(l -> l.getValor()));
		this.menorLance = menorLance.isPresent() ? menorLance.get().getValor() : 0L;
 		
		this.mediaLances = leilao.getLances().stream().mapToDouble(Lance::getValor).average().orElse(0D);
		
		this.tresMaioresLances = leilao.getLances().stream().sorted((p1,p2) -> p2.getValor().compareTo(p1.getValor()))
				.collect(Collectors.toList());
		
		this.tresMaioresLances = tresMaioresLances.subList(0, this.tresMaioresLances.size() > 3 ? 3 : this.tresMaioresLances.size());
		
	}

	public Double getMaiorLance() {
		return maiorLance;
	}

	public Double getMenorLance() {
		return menorLance;
	}

	public Double getMediaLances() {
		return mediaLances;
	}

	public List<Lance> getTresMaioresLances() {
		return tresMaioresLances;
	}

	
	
}
