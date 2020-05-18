package br.com.caelum.leilao.test;

import static org.junit.Assert.assertEquals;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;

import java.util.List;

import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import br.com.caelum.leilao.builder.CriadorDeLeilao;
import br.com.caelum.leilao.dominio.Lance;
import br.com.caelum.leilao.dominio.Leilao;
import br.com.caelum.leilao.dominio.Usuario;
import br.com.caelum.leilao.servico.Avaliador;

public class AvaliadorTest {

	private Avaliador leiloeiro;
	private Usuario joao;
	private Usuario jose;
	private Usuario maria;
	private Usuario ewerton;

	@BeforeClass
	public static void testandoBeforeClass() {
		System.out.println("before class");
	}

	@AfterClass
	public static void testandoAfterClass() {
		System.out.println("after class");
	}

	@Before
	public void setUp() {

		this.leiloeiro = new Avaliador();
		this.joao = new Usuario("Joao");
		this.jose = new Usuario("Jose");
		this.maria = new Usuario("Maria");
		this.ewerton = new Usuario("Ewerton");
		System.out.println("fim");

	}

	@Test
	public void deveCalcularAMedia() {

		// Monta Cenario: 3 lances em ordem crescente
//		Usuario joao = new Usuario("Joao");
//		Usuario jose = new Usuario("Jose");
//		Usuario maria = new Usuario("Maria");

//		Leilao leilao = new Leilao("Playstation 3 Novo");
//
//		leilao.propoe(new Lance(joao, 250.0));
//		leilao.propoe(new Lance(jose, 300.0));
//		leilao.propoe(new Lance(maria, 400.0));

		Leilao leilao = new CriadorDeLeilao().para("Playstation 3 Novo").lance(joao, 250.0).lance(jose, 300.0)
				.lance(maria, 400.0).constroi();

//		criaAvaliador();
		// leiloeiro = new Avaliador();
		leiloeiro.avalia(leilao);

		// comparando a saida com o esperado
		assertEquals(316.6666666666667, leiloeiro.getMediaDeTodos(), 0.00001);
	}

	@Test(expected = RuntimeException.class)
	public void testeMediaDeZeroLance() {

		// Monta Cenario: 3 lances em ordem crescente
//		Usuario ewertom = new Usuario("Ewertom");

		// Leilao leilao = new Leilao("Iphone 7");
		Leilao leilao = new CriadorDeLeilao().para("Iphone 7").constroi();

		// Executa a Ação
//		criaAvaliador();
		// Avaliador leiloeiro = new Avaliador();
		leiloeiro.avalia(leilao);

		// Comparando a saida com o esperado
		assertEquals(Double.NaN, leiloeiro.getMediaDeTodos(), 0.0001);
	}

	@Test
	public void deveEntenderLeilaoComApenasUmLance() {

		// Monta Cenario: 3 lances em ordem crescente
//		Usuario joao = new Usuario("João");

//		Leilao leilao = new Leilao("Playstation 3 Novo");
//		leilao.propoe(new Lance(joao, 1000.0));

		Leilao leilao = new CriadorDeLeilao().para("Playstation 3 Novo").lance(joao, 1000.0).constroi();

		// Executa a Ação
//		criaAvaliador();
		// Avaliador leiloeiro = new Avaliador();
		leiloeiro.avalia(leilao);

		// Comparando a saida com o esperado
		assertEquals(1000.0, leiloeiro.getMediaDeTodos(), 0.0001);
		assertEquals(1000.0, leiloeiro.getMediaDeTodos(), 0.0001);
	}

	@Test
	public void deveEncontrarOsTresMaioresLances() {

		// Monta Cenario: 3 lances em ordem crescente
//		Usuario joao = new Usuario("João");
//		Usuario maria = new Usuario("Maria");

//		Leilao leilao = new Leilao("Playstation 3 Novo");
//		leilao.propoe(new Lance(joao, 100.0));
//		leilao.propoe(new Lance(maria, 200.0));
//		leilao.propoe(new Lance(joao, 300.0));
//		leilao.propoe(new Lance(maria, 400.0));

		Leilao leilao = new CriadorDeLeilao().para("Playstation 3 Novo")
				.lance(joao, 100.0)
				.lance(maria, 200.0)
				.lance(joao, 300.0)
				.lance(maria, 400.0)
				.constroi();

		// Executa a Ação
//		criaAvaliador();
		// Avaliador leiloeiro = new Avaliador();
		leiloeiro.avalia(leilao);

		// Comparando a saida com o esperado
		List<Lance> maiores = leiloeiro.getTresMaiores();

		assertEquals(3, maiores.size());
		
//		assertEquals(400.0, maiores.get(0).getValor(), 0.00001);
//		assertEquals(300.0, maiores.get(1).getValor(), 0.00001);
//		assertEquals(200.0, maiores.get(2).getValor(), 0.00001);
		
	       assertThat(maiores, hasItems(
	                new Lance(maria, 400), 
	                new Lance(joao, 300),
	                new Lance(maria, 200)
	        ));  
	}



	@Test
	public void testeDeLancesAleatorios() {

		// Monta Cenario: 3 lances em ordem crescente
//		Usuario joao = new Usuario("João");
//		Usuario maria = new Usuario("Maria");
//		Usuario jose = new Usuario("Jose");
//		Usuario ewerton = new Usuario("Ewerton");

//		Leilao leilao = new Leilao("Playstation 3 Novo");
//		leilao.propoe(new Lance(joao, 200.0));
//		leilao.propoe(new Lance(maria, 450.0));
//		leilao.propoe(new Lance(jose, 120.0));
//		leilao.propoe(new Lance(ewerton, 700.0));
//		leilao.propoe(new Lance(joao, 630.0));
//		leilao.propoe(new Lance(maria, 230.0));

		Leilao leilao = new CriadorDeLeilao().para("Playstation 3 Novo").lance(joao, 200.0).lance(maria, 450.0)
				.lance(jose, 120.0).lance(ewerton, 700.0).lance(joao, 630.0).lance(maria, 230.0).constroi();

		// Executa a Ação
//		criaAvaliador();
		// Avaliador leiloeiro = new Avaliador();
		leiloeiro.avalia(leilao);

		// Comparando a saida com o esperado

		assertEquals(700.0, leiloeiro.getMaiorLance(), 0.00001);
		assertEquals(120.0, leiloeiro.getMenorLance(), 0.00001);

	}

	@Test
	public void testeDeLancesDecrecentes() {

		// Monta Cenario: 3 lances em ordem crescente
//		Usuario joao = new Usuario("João");
//		Usuario maria = new Usuario("Maria");
//		Usuario jose = new Usuario("Jose");
//		Usuario ewerton = new Usuario("Ewerton");

//		Leilao leilao = new Leilao("Playstation 3 Novo");
//		leilao.propoe(new Lance(joao, 400.0));
//		leilao.propoe(new Lance(maria, 300.0));
//		leilao.propoe(new Lance(jose, 200.0));
//		leilao.propoe(new Lance(ewerton, 100.0));
//		leilao.propoe(new Lance(joao, 50.0));
//		leilao.propoe(new Lance(maria, 25.0));

		Leilao leilao = new CriadorDeLeilao().para("Playstation 3 Novo").lance(joao, 400.0).lance(maria, 300.0)
				.lance(jose, 200.0).lance(ewerton, 100.0).lance(joao, 50.0).lance(maria, 25.0).constroi();

		// Executa a Ação
//		criaAvaliador();
		// Avaliador leiloeiro = new Avaliador();
		leiloeiro.avalia(leilao);

		// Comparando a saida com o esperado

		assertEquals(400.0, leiloeiro.getMaiorLance(), 0.00001);
		assertEquals(25.0, leiloeiro.getMenorLance(), 0.00001);

	}

	@Test
	public void deveEncontrarOsCincoMaioresLances() {

		// Monta Cenario: 3 lances em ordem crescente
//		Usuario joao = new Usuario("João");
//		Usuario maria = new Usuario("Maria");

//		Leilao leilao = new Leilao("Playstation 3 Novo");
//		leilao.propoe(new Lance(joao, 100.0));
//		leilao.propoe(new Lance(maria, 200.0));
//		leilao.propoe(new Lance(joao, 300.0));
//		leilao.propoe(new Lance(maria, 400.0));
//		leilao.propoe(new Lance(joao, 500.0));

		Leilao leilao = new CriadorDeLeilao().para("Playstation 3 Novo").lance(joao, 100.0).lance(maria, 200.0)
				.lance(joao, 300.0).lance(maria, 400.0).lance(joao, 500.0).constroi();

		// Executa a Ação
//		criaAvaliador();
		// Avaliador leiloeiro = new Avaliador();
		leiloeiro.avalia(leilao);

		// Comparando a saida com o esperado
		List<Lance> maiores = leiloeiro.getTresMaiores();

		assertEquals(3, maiores.size());
		assertEquals(500.0, maiores.get(0).getValor(), 0.00001);
		assertEquals(400.0, maiores.get(1).getValor(), 0.00001);
		assertEquals(300.0, maiores.get(2).getValor(), 0.00001);
	}

	@Test
	public void deveEncontrarOsDoisMaioresLances() {

		// Monta Cenario: 3 lances em ordem crescente
//		Usuario joao = new Usuario("João");
//		Usuario maria = new Usuario("Maria");

//		Leilao leilao = new Leilao("Playstation 3 Novo");
//		leilao.propoe(new Lance(joao, 100.0));
//		leilao.propoe(new Lance(maria, 200.0));

		Leilao leilao = new CriadorDeLeilao().para("Playstation 3 Novo").lance(joao, 100.0).lance(maria, 200.0)
				.constroi();

		// Executa a Ação
//		criaAvaliador();
		// Avaliador leiloeiro = new Avaliador();
		leiloeiro.avalia(leilao);

		// Comparando a saida com o esperado
		List<Lance> maiores = leiloeiro.getTresMaiores();

		assertEquals(2, maiores.size());
		assertEquals(200.0, maiores.get(0).getValor(), 0.00001);
		assertEquals(100.0, maiores.get(1).getValor(), 0.00001);

	}

	@Test(expected = RuntimeException.class)
	public void deveEncontrarNenhumLance() {

		// Leilao leilao = new Leilao("Playstation 3 Novo");

		Leilao leilao = new CriadorDeLeilao().para("Playstation 3 Novo").constroi();

		// Executa a Ação
//		criaAvaliador();
		// Avaliador leiloeiro = new Avaliador();
		leiloeiro.avalia(leilao);

		// Comparando a saida com o esperado
		List<Lance> maiores = leiloeiro.getTresMaiores();

		assertEquals(0, maiores.size());

	}

	@Test(expected = RuntimeException.class)
	public void naoDeveAvaliarLeiloesSemNenhumLanceDado() {

		Leilao leilao = new CriadorDeLeilao().para("Playstation 3 Novo").constroi();

		leiloeiro.avalia(leilao);

	}
	
    @Test(expected=IllegalArgumentException.class)
    public void deveRecusarLancesComValorDeZero() {
        new Lance(new Usuario("John Doe"),0);
    }

    @Test(expected=IllegalArgumentException.class)
    public void deveRecusarLancesComValorNegativo() {
        new Lance(new Usuario("John Doe"), -10);
    }
}
