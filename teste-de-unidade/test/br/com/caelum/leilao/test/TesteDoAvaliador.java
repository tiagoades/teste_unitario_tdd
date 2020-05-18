package br.com.caelum.leilao.test;

import org.junit.Assert;
import org.junit.Test;

import br.com.caelum.leilao.dominio.Lance;
import br.com.caelum.leilao.dominio.Leilao;
import br.com.caelum.leilao.dominio.Usuario;
import br.com.caelum.leilao.servico.Avaliador;

public class TesteDoAvaliador {

	@Test
	public void deveEntenderLancesEmOrdemCrescente() {

		// Monta Cenario: 3 lances em ordem crescente
		Usuario joao = new Usuario("Joao");
		Usuario jose = new Usuario("Jose");
		Usuario maria = new Usuario("Maria");

		Leilao leilao = new Leilao("Playstation 3 Novo");

		leilao.propoe(new Lance(joao, 250.0));
		leilao.propoe(new Lance(jose, 300.0));
		leilao.propoe(new Lance(maria, 400.0));

		// Executa a Ação
		Avaliador leiloeiro = new Avaliador();
		leiloeiro.avalia(leilao);

		// Valida a Saída

		double maiorEsperado = 400;
		double menorEsperado = 250;
		double mediaEsperada = 316;

		Assert.assertEquals(maiorEsperado, leiloeiro.getMaiorLance(), 0.00001);
		Assert.assertEquals(menorEsperado, leiloeiro.getMenorLance(), 0.00001);
		Assert.assertEquals(mediaEsperada, leiloeiro.getMediaDeTodos(), 0.00001);

		//System.out.println(leiloeiro.getMediaDeTodos());
	}

}
