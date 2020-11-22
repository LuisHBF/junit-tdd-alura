package br.com.caelum.leilao.servico;

import java.util.List;

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
		
		leilao.propoe(new Lance(joao,3500D));
		leilao.propoe(new Lance(jose,5000D));
		leilao.propoe(new Lance(maria, 6000D));
		
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
    
    @Test
    public void deveEntenderLeilaoComApenasUmLance() {
    	Usuario joao = new Usuario("João");
    	Leilao leilao = new Leilao("PS3 Novo");
    	
    	leilao.propoe(new Lance(joao, 1000D));
    	
    	Avaliador leiloeiro = new Avaliador();
    	leiloeiro.avalia(leilao);
    	
    	Double valorEsperado = 1000D;
    	
    	Assert.assertEquals(valorEsperado, leiloeiro.getMaiorLance());
    	Assert.assertEquals(valorEsperado, leiloeiro.getMenorLance());
    	Assert.assertEquals(valorEsperado, leiloeiro.getMediaLances());
    }
    
    @Test
    public void deveEncontrarOsTresMaioresLances() {
    	Usuario joao = new Usuario("João");
    	Usuario maria = new Usuario("Maria");
    	Leilao leilao = new Leilao("PS4 Novo");
    	
    	leilao.propoe(new Lance(joao, 1500D));
    	leilao.propoe(new Lance(joao, 1700D));
    	leilao.propoe(new Lance(joao, 2100D));
    	
    	leilao.propoe(new Lance(maria, 3000D));
    	leilao.propoe(new Lance(maria, 3100D));

    	Avaliador leiloeiro = new Avaliador();
    	leiloeiro.avalia(leilao);
    	
    	List<Lance> maiores = leiloeiro.getTresMaioresLances();
    	
    	Assert.assertEquals(3, maiores.size());
    	Assert.assertEquals((Double) 3100D, maiores.get(0).getValor());
    	Assert.assertEquals((Double) 3000D, maiores.get(1).getValor());
    	Assert.assertEquals((Double) 2100D, maiores.get(2).getValor());
    }
    
    @Test
    public void deveEntenderLeilaoComLancesEmOrdemAleatoria() {
    	Usuario joao = new Usuario("João");
    	Usuario maria = new Usuario("Maria");
    	Leilao leilao = new Leilao("PS4 Novo");
    	
    	leilao.propoe(new Lance(joao, 1000D));
    	leilao.propoe(new Lance(joao, 900D));
    	leilao.propoe(new Lance(joao, 3500D));
    	
    	leilao.propoe(new Lance(maria, 3200D));
    	leilao.propoe(new Lance(maria, 250D));

    	Avaliador leiloeiro = new Avaliador();
    	leiloeiro.avalia(leilao);
    	
    	Assert.assertEquals((Double) 3500D, leiloeiro.getMaiorLance());
    	Assert.assertEquals((Double) 250D, leiloeiro.getMenorLance());
    }
    
    @Test
    public void deveEntenderLeilaoComLancesEmOrdemDescrescente() {
    	Usuario joao = new Usuario("João");
    	Usuario maria = new Usuario("Maria");
    	Leilao leilao = new Leilao("PS4 Novo");
    	
    	leilao.propoe(new Lance(joao, 3500D));
    	leilao.propoe(new Lance(joao, 3000D));
    	leilao.propoe(new Lance(joao, 2500D));
    	
    	leilao.propoe(new Lance(maria, 2000D));
    	leilao.propoe(new Lance(maria, 1500D));

    	Avaliador leiloeiro = new Avaliador();
    	leiloeiro.avalia(leilao);
    	
    	Assert.assertEquals((Double) 3500D, leiloeiro.getMaiorLance());
    	Assert.assertEquals((Double) 1500D, leiloeiro.getMenorLance());
    }
    
    @Test
    public void deveDevolverTodosLancesCasoNaoHajaNoMinimo3() {
    	Usuario joao = new Usuario("João");
    	Leilao leilao = new Leilao("PS4 Novo");
    	
    	leilao.propoe(new Lance(joao, 3500D));
    	leilao.propoe(new Lance(joao, 3000D));


    	Avaliador leiloeiro = new Avaliador();
    	leiloeiro.avalia(leilao);
    	
    	List<Lance> lances = leiloeiro.getTresMaioresLances();
    	
    	Assert.assertEquals(2, lances.size());
    }
    
    @Test
    public void deveDevolverListaVaziaCasoNaoHajaLances() {
    	Leilao leilao = new Leilao("PS4 Novo");
    	
    	Avaliador leiloeiro = new Avaliador();
    	leiloeiro.avalia(leilao);
    	
    	List<Lance> lances = leiloeiro.getTresMaioresLances();
    	
    	Assert.assertEquals(0, lances.size());
    }
    
}
