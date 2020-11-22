package br.com.caelum.leilao.dominio;

import org.junit.Assert;
import org.junit.Test;

public class LeilaoTest {

	@Test
	public void deveReceberUmLance() {
		Leilao leilao = new Leilao("Teclado Mecânico");
		Assert.assertEquals(0, leilao.getLances().size());
		
		leilao.propoe(new Lance(new Usuario("Luis"), 350D));
		
		Assert.assertEquals(1, leilao.getLances().size());
		Assert.assertEquals((Double) 350D, leilao.getLances().get(0).getValor());
	}
	
	@Test
	public void deveReceberVariosLances() {
		Leilao leilao = new Leilao("Teclado Mecânico");
		
		leilao.propoe(new Lance(new Usuario("Luis"), 350D));
		leilao.propoe(new Lance(new Usuario("Joao"), 450D));
		
		Assert.assertEquals(2, leilao.getLances().size());
		Assert.assertEquals((Double) 350D, leilao.getLances().get(0).getValor());
		Assert.assertEquals((Double) 450D, leilao.getLances().get(1).getValor());

	}
	
	@Test
	public void naoDeveAceitarDoisLancesSeguidosDoMesmoUsuario() {
		Leilao leilao = new Leilao("Teclado Mecânico");
		Usuario usuario = new Usuario("Luis");
		
		leilao.propoe(new Lance(usuario, 350D));
		leilao.propoe(new Lance(usuario, 450D));
		
		Assert.assertEquals(1, leilao.getLances().size());
		Assert.assertEquals((Double) 350D, leilao.getLances().get(0).getValor());
	}
	
	@Test
	public void naoDeveAceitarMaisDoQue5LancesDeUmMesmoUsuario() {
		Leilao leilao = new Leilao("Teclado Mecânico");
		Usuario luis = new Usuario("Luis");
		Usuario joao = new Usuario("Joao");
		
		leilao.propoe(new Lance(luis, 350D));
		leilao.propoe(new Lance(joao, 450D));
		
		leilao.propoe(new Lance(luis, 550D));
		leilao.propoe(new Lance(joao, 650D));
		
		leilao.propoe(new Lance(luis, 750D));
		leilao.propoe(new Lance(joao, 850D));
		
		leilao.propoe(new Lance(luis, 950D));
		leilao.propoe(new Lance(joao, 1050D));
		
		leilao.propoe(new Lance(luis, 1150D));
		leilao.propoe(new Lance(joao, 1250D));
		
		leilao.propoe(new Lance(luis, 1350D));
		
		Assert.assertEquals(10, leilao.getLances().size());
		Assert.assertEquals((Double) 1250D, leilao.getLances().get(leilao.getLances().size() -1).getValor());
	}
}
