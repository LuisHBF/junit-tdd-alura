package br.com.caelum.leilao.servico;

import org.junit.Assert;
import org.junit.Test;

import br.com.caelum.leilao.dominio.Lance;
import br.com.caelum.leilao.dominio.Leilao;
import br.com.caelum.leilao.dominio.Usuario;

public class TesteDoAvaliador {

	@Test
	public void deveEntenderLancesEmOrdemCrescente() {
		Usuario joao = new Usuario("João");
		Usuario jose = new Usuario("José");
		Usuario maria = new Usuario("Maria");
		
		Leilao leilao = new Leilao("Playstation 5 novo");
		
		leilao.propoe(new Lance(joao,3500));
		leilao.propoe(new Lance(jose,5000));
		leilao.propoe(new Lance(maria, 6000));
		
		Avaliador leiloeiro = new Avaliador();
		
		leiloeiro.avalia(leilao);
		
		Double maiorEsperado = 6000D;
		Double menorEsperado = 3500D;
		
		Assert.assertEquals(maiorEsperado, leiloeiro.getMaiorLance());
		Assert.assertEquals(menorEsperado, leiloeiro.getMenorLance());
	}
	
	@Test
    public void deveCalcularAMedia() {
        Usuario joao = new Usuario("Joao");
        Usuario jose = new Usuario("José");
        Usuario maria = new Usuario("Maria");

        Leilao leilao = new Leilao("Playstation 3 Novo");

        leilao.propoe(new Lance(maria,300D));
        leilao.propoe(new Lance(joao,400D));
        leilao.propoe(new Lance(jose,500D));

        Avaliador leiloeiro = new Avaliador();
        leiloeiro.avalia(leilao);

        Double mediaEsperada = 400D;

        Assert.assertEquals(mediaEsperada, leiloeiro.getMediaLances());
    }

	
    @Test
    public void testaMediaDeZeroLance(){

        Leilao leilao = new Leilao("XBOX");
        Avaliador avaliador = new Avaliador();
        avaliador.avalia(leilao);
        
        Double valorEsperado = 0D;
        
        Assert.assertEquals(valorEsperado, avaliador.getMediaLances());

    }
}
