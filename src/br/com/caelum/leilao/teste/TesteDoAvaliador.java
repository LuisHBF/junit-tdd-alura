package br.com.caelum.leilao.teste;

import org.junit.Assert;
import org.junit.Test;

import br.com.caelum.leilao.dominio.Lance;
import br.com.caelum.leilao.dominio.Leilao;
import br.com.caelum.leilao.dominio.Usuario;
import br.com.caelum.leilao.servico.Avaliador;

public class TesteDoAvaliador {

	@Test
	public void deveEntenderLancesEmOrdemCrescente() {
		Usuario joao = new Usuario("João");
		Usuario jose = new Usuario("José");
		Usuario maria = new Usuario("Maria");
		
		Leilao leilao = new Leilao("Playstation 5 novo");
		
		leilao.propoe(new Lance(joao,5000));
		leilao.propoe(new Lance(jose,3500));
		leilao.propoe(new Lance(maria, 6000));
		
		Avaliador leiloeiro = new Avaliador();
		
		leiloeiro.avalia(leilao);
		
		Double maiorEsperado = 6000D;
		Double menorEsperado = 3500D;
		
		Assert.assertEquals(maiorEsperado, leiloeiro.getMaiorLance());
		Assert.assertEquals(menorEsperado, leiloeiro.getMenorLance());
	}
}
