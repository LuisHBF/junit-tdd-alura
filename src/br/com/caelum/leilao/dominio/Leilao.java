package br.com.caelum.leilao.dominio;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class Leilao {

	private String descricao;
	private List<Lance> lances;

	public Leilao(String descricao) {
		this.descricao = descricao;
		this.lances = new ArrayList<Lance>();
	}

	public void propoe(Lance lance) {

		if (lances.isEmpty() || this.podeProporLance(lance)) {
			lances.add(lance);
		}
	}

	public void dobraLance(Usuario usuario) {
		List<Lance> lancesDoUsuario = this.getLancesDeUmUsuario(usuario);
		if(lancesDoUsuario.size() > 0) {
			Lance ultimoLanceDoUsuario = lancesDoUsuario.get(lancesDoUsuario.size() - 1);
			Lance novoLance = new Lance(usuario, ultimoLanceDoUsuario.getValor() * 2);
			this.propoe(novoLance);
		}
	}

	private List<Lance> getLancesDeUmUsuario(Usuario usuario) {
		return this.lances.stream().filter(l -> l.getUsuario().equals(usuario)).collect(Collectors.toList());
	}
	
	private boolean podeProporLance(Lance lance) {
		Boolean possuiMaisQue5Lances = lances.stream().filter(l -> l.getUsuario().equals(lance.getUsuario()))
				.collect(Collectors.toList()).size() >= 5;
				
		Boolean ultimoLancePertenceAoMesmoUsuario = lance.getUsuario().equals(getUltimoLance().getUsuario());
		
		return !(possuiMaisQue5Lances || ultimoLancePertenceAoMesmoUsuario);
	}

	private Lance getUltimoLance() {
		return this.lances.get(lances.size() - 1);
	}

	public String getDescricao() {
		return descricao;
	}

	public List<Lance> getLances() {
		return this.lances;

	}

}
