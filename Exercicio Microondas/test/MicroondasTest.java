import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

import java.util.Scanner;

import org.junit.jupiter.api.Test;

class MicroondasTest {

	@Test
	public void testMicroondas() {
		Microondas m = new Microondas();
		assertNotNull(m);
	}

	@Test
	public void testInvalidTempo() {
		Microondas m = new Microondas();
		assertEquals(m.setTempo(-120), 0);
	}

	@Test
	public void testValidTempo() {
		Microondas m = new Microondas();
		assertEquals(m.setTempo(150), 1);
	}

	@Test
	public void testPausado() {
		Microondas m = new Microondas();
		assertEquals(m.getPausado(), true);
	}

	@Test
	public void testFechado() {
		Microondas m = new Microondas();
		assertEquals(m.getFechado(), true);
	}

	@Test
	public void testAbrir() {
		Microondas m = new Microondas();
		m.abrir();
		assertFalse(m.getFechado());
	}

	@Test
	public void testLigadoFechado() {
		Microondas m = new Microondas();
		m.ligar();
		assertFalse(m.getPausado());
	}

	@Test
	public void testLigadoAberto() {
		Microondas m = new Microondas();
		m.abrir();
		m.ligar();
		assertTrue(m.getPausado());
	}

	@Test
	public void testAbrirLigado() {
		Microondas m = new Microondas();
		m.ligar();
		m.abrir();
		assertTrue(m.getPausado());
	}

	@Test
	public void testLigarPausar() {
		Microondas m = new Microondas();
		m.ligar();
		m.pausar();
		assertTrue(m.getPausado());
	}

	@Test
	public void testReiniciar() {
		Microondas m = new Microondas();
		m.ligar();
		m.pausar();
		m.pausar();
		assertTrue(m.getReiniciado());
	}

	@Test
	public void testTempo() {
		Microondas m = new Microondas();
		Scanner sc = new Scanner(System.in);
		System.out.println("Digite o tempo:");
		int t = sc.nextInt();
		m.setTempo(t);
		assertEquals(m.getMinutos(), t / 100);
		assertEquals(m.getSegundos(), t % 100);
		sc.close();
	}
}
