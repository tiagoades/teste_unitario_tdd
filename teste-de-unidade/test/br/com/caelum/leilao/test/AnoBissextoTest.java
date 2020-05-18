package br.com.caelum.leilao.test;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

import br.com.caelum.desafio.AnoBissexto;

public class AnoBissextoTest {

	@Test
	public void deveRetornaAnoBisexto() {

		AnoBissexto anoBissexto = new AnoBissexto();

		assertEquals(true, anoBissexto.getAnoBisexto(2016));
		assertEquals(true, anoBissexto.getAnoBisexto(2012));

	}

	@Test
	public void naoDeveRetornarAnoBissexto() {

		AnoBissexto anoBissexto = new AnoBissexto();

		assertEquals(true, anoBissexto.getAnoBisexto(2015));
		assertEquals(true, anoBissexto.getAnoBisexto(2011));

	}

}