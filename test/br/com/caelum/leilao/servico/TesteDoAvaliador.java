package br.com.caelum.leilao.servico;

import java.util.List;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import br.com.caelum.leilao.builder.CriadorDeLeilao;
import br.com.caelum.leilao.dominio.Lance;
import br.com.caelum.leilao.dominio.Leilao;
import br.com.caelum.leilao.dominio.Usuario;

public class TesteDoAvaliador {

	private Avaliador leiloeiro;
	private Usuario joao;
	private Usuario jose;
	private Usuario maria;

	@Before
	public void criaAvaliador() {
		this.leiloeiro = new Avaliador();
	}

	@Before
	public void criaUsuarios() {
		this.joao = new Usuario("João");
		this.jose = new Usuario("José");
		this.maria = new Usuario("Maria");
	}

	@Test
	public void deveEntenderLancesEmOrdemCrescente() {
		Leilao leilao = new CriadorDeLeilao().para("Playstation 5 Novo").lance(joao, 3500D).lance(jose, 5000D)
				.lance(maria, 6000D).constroi();
		leiloeiro.avalia(leilao);

		Assert.assertEquals((Double) 6000D, leiloeiro.getMaiorLance());
		Assert.assertEquals((Double) 3500D, leiloeiro.getMenorLance());
	}

	@Test
	public void deveCalcularAMedia() {
		Leilao leilao = new CriadorDeLeilao().para("PS4").lance(maria, 300D).lance(joao, 400D).lance(jose, 500D)
				.constroi();
		leiloeiro.avalia(leilao);

		Assert.assertEquals((Double) 400D, leiloeiro.getMediaLances());
	}

	@Test
	public void testaMediaDeZeroLance() {
		Leilao leilao = new CriadorDeLeilao().para("XBOX").constroi();
		leiloeiro.avalia(leilao);
		
		Assert.assertEquals((Double) 0D, leiloeiro.getMediaLances());

	}

	@Test
	public void deveEntenderLeilaoComApenasUmLance() {
		Leilao leilao = new CriadorDeLeilao().para("XBOX").lance(joao, 1000D).constroi();
		leiloeiro.avalia(leilao);

		Assert.assertEquals((Double) 1000D, leiloeiro.getMaiorLance());
		Assert.assertEquals((Double) 1000D, leiloeiro.getMenorLance());
		Assert.assertEquals((Double) 1000D, leiloeiro.getMediaLances());
	}

	@Test
	public void deveEncontrarOsTresMaioresLances() {
		Leilao leilao = new CriadorDeLeilao().para("PS4").lance(joao, 1500D).lance(maria, 1700D).lance(joao, 2100D)
				.lance(maria, 3000D).lance(joao, 3100D).constroi();

		leiloeiro.avalia(leilao);
		List<Lance> maiores = leiloeiro.getTresMaioresLances();
		
		Assert.assertEquals(3, maiores.size());
		Assert.assertEquals((Double) 3100D, maiores.get(0).getValor());
		Assert.assertEquals((Double) 3000D, maiores.get(1).getValor());
		Assert.assertEquals((Double) 2100D, maiores.get(2).getValor());
	}

	@Test
	public void deveEntenderLeilaoComLancesEmOrdemAleatoria() {
		Leilao leilao = new CriadorDeLeilao().para("PS4").lance(joao, 1000D).lance(maria, 900D).lance(joao, 3500D)
				.lance(maria, 3200D).lance(joao, 250D).constroi();
		leiloeiro.avalia(leilao);
		
		Assert.assertEquals((Double) 3500D, leiloeiro.getMaiorLance());
		Assert.assertEquals((Double) 250D, leiloeiro.getMenorLance());
	}

	@Test
	public void deveEntenderLeilaoComLancesEmOrdemDescrescente() {
		Leilao leilao = new CriadorDeLeilao().para("PS4").lance(joao, 3500D).lance(maria, 3000D).lance(joao, 2500D)
				.lance(maria, 2000D).lance(joao, 1500D).constroi();
		leiloeiro.avalia(leilao);
		
		Assert.assertEquals((Double) 3500D, leiloeiro.getMaiorLance());
		Assert.assertEquals((Double) 1500D, leiloeiro.getMenorLance());
	}

	@Test
	public void deveDevolverTodosLancesCasoNaoHajaNoMinimo3() {
		Leilao leilao = new CriadorDeLeilao().para("PS4").lance(joao, 3500D).lance(maria, 3000D).constroi();
		leiloeiro.avalia(leilao);
		List<Lance> lances = leiloeiro.getTresMaioresLances();
		
		Assert.assertEquals(2, lances.size());
	}

	@Test
	public void deveDevolverListaVaziaCasoNaoHajaLances() {
		Leilao leilao = new CriadorDeLeilao().para("PS4").constroi();
		leiloeiro.avalia(leilao);
		List<Lance> lances = leiloeiro.getTresMaioresLances();
		
		Assert.assertEquals(0, lances.size());
	}

}
