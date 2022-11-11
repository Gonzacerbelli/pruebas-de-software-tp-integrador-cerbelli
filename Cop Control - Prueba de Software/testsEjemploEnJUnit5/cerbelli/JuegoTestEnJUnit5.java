package cerbelli;

import pista.Pista;
import pista.PistaSimple;
import pista.PosicionesEntradaVaciaException;
import avion.AvionSimple;
import copControl.Dificultad;
import copControl.Juego;
import copControl.Jugador;
import copControl.Mapa;
import copControl.Nivel;
import copControl.Posicion;
import avion.Avion;

//librerias de Junit 5
import static org.junit.jupiter.api.Assertions.*;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
//

public class JuegoTestEnJUnit5{
	
	Mapa mapaDeJuego;
	
	@BeforeEach
	public void setUp(){
		
		mapaDeJuego = new Mapa();
	}
	
	@Test
	public void testChocoConOtroAvion() {
		
		Posicion posicionInicial = new Posicion(30, 30);
		Posicion posicionFinal = new Posicion(3, 9);
		AvionSimple avion = new AvionSimple(posicionInicial, posicionFinal, mapaDeJuego);
		AvionSimple avion2 = new AvionSimple(posicionInicial, posicionFinal, mapaDeJuego);
		
		Dificultad dificultad = new Dificultad(2, 0, 3);
		Nivel nivel = new Nivel(mapaDeJuego, dificultad);
		List<Nivel> niveles = new ArrayList<Nivel>();
		niveles.add(nivel);

		Jugador jugador = new Jugador("gonzalo");
		Juego juego = new Juego(jugador, niveles);
		
		List<Pista> pistas = new ArrayList<Pista>();
		Pista pista;
		try {
			pista = new PistaSimple(posicionInicial);
			pistas.add(pista);
		} catch (PosicionesEntradaVaciaException e) {
			e.printStackTrace();
		}
		
		List<Avion> avionesEnAire = new ArrayList<Avion>();
		
		avionesEnAire.add(avion);
		avionesEnAire.add(avion2);
		
		mapaDeJuego.setPistas(pistas);
		mapaDeJuego.colocarAvionEnAire(avion);
		mapaDeJuego.colocarAvionEnAire(avion2);
		
		juego.huboChoque();
		
		assertTrue(nivel.huboChoque());
		
		assertFalse(juego.estaJugandose());
		
	}
	
	@Test
	public void testColocarAvion() {
		
		Posicion posicionInicial = new Posicion(30, 30);
		Posicion posicionFinal = new Posicion(3, 9);
		AvionSimple avion = new AvionSimple(posicionInicial, posicionFinal, mapaDeJuego);
		AvionSimple avion2 = new AvionSimple(posicionInicial, posicionFinal, mapaDeJuego);
		
		Dificultad dificultad = new Dificultad(2, 0, 3);
		Nivel nivel = new Nivel(mapaDeJuego, dificultad);
		List<Nivel> niveles = new ArrayList<Nivel>();
		niveles.add(nivel);

		Jugador jugador = new Jugador("gonzalo");
		Juego juego = new Juego(jugador, niveles);
		
		List<Pista> pistas = new ArrayList<Pista>();
		Pista pista;
		try {
			pista = new PistaSimple(posicionInicial);
			pistas.add(pista);
		} catch (PosicionesEntradaVaciaException e) {
			e.printStackTrace();
		}
		
		List<Avion> avionesEnAire = new ArrayList<Avion>();
		
		avionesEnAire.add(avion);
		avionesEnAire.add(avion2);
		
		mapaDeJuego.setPistas(pistas);
		mapaDeJuego.colocarAvionEnAire(avion);
		mapaDeJuego.colocarAvionEnAire(avion2);
		
		juego.colocarAvion();
		
		assertEquals(juego.getNivelActual(), nivel);
		
		assertEquals(juego.getCantidadAvionesAterrizados(), 0);
		
		assertEquals(nivel.getAvionEnPosicion(posicionInicial), avion);
		
	}
	
	
	@Test
	public void testAterrizarAviones() {
		
		Posicion posicionInicial = new Posicion(30, 30);
		Posicion posicionFinal = new Posicion(3, 9);
		AvionSimple avion = new AvionSimple(posicionInicial, posicionFinal, mapaDeJuego);
		
		Dificultad dificultad = new Dificultad(2, 0, 3);
		Nivel nivel = new Nivel(mapaDeJuego, dificultad);
		List<Nivel> niveles = new ArrayList<Nivel>();
		niveles.add(nivel);
		
		List<Pista> pistas = new ArrayList<Pista>();
		Pista pista;
		try {
			pista = new PistaSimple(posicionInicial);
			pistas.add(pista);
		} catch (PosicionesEntradaVaciaException e) {
			e.printStackTrace();
		}
		
		List<Avion> avionesEnAire = new ArrayList<Avion>();
		
		avionesEnAire.add(avion);
		
		mapaDeJuego.setPistas(pistas);
		mapaDeJuego.colocarAvionEnAire(avion);
		
		assertEquals(nivel.aterrizarAviones(), 1);
		
	}
	
	
	@Test
	public void testEstaJugandose() {
		
		Posicion posicionInicial = new Posicion(30, 30);
		Posicion posicionFinal = new Posicion(3, 9);
		AvionSimple avion = new AvionSimple(posicionInicial, posicionFinal, mapaDeJuego);
		
		Dificultad dificultad = new Dificultad(2, 0, 3);
		Nivel nivel = new Nivel(mapaDeJuego, dificultad);
		List<Nivel> niveles = new ArrayList<Nivel>();
		niveles.add(nivel);

		Jugador jugador = new Jugador("gonzalo");
		Juego juego = new Juego(jugador, niveles);
		
		List<Pista> pistas = new ArrayList<Pista>();
		Pista pista;
		try {
			pista = new PistaSimple(posicionInicial);
			pistas.add(pista);
		} catch (PosicionesEntradaVaciaException e) {
			e.printStackTrace();
		}
		
		List<Avion> avionesEnAire = new ArrayList<Avion>();
		
		avionesEnAire.add(avion);
		
		mapaDeJuego.setPistas(pistas);
		mapaDeJuego.colocarAvionEnAire(avion);
		
		nivel.aterrizarAviones();
		
		juego.vivir();
		

		assertEquals(juego.getNiveles(), niveles);
		
		assertEquals(juego.getJugador(), jugador);
		
		assertFalse(juego.esJuegoGanado());
		
		assertTrue(juego.estaJugandose());
		
	}
	
	@Test
	public void testVelocidadCorrecta() {
		
		Posicion posicionInicial = new Posicion(30, 30);
		Posicion posicionFinal = new Posicion(3, 9);
		AvionSimple avion = new AvionSimple(posicionInicial, posicionFinal, mapaDeJuego);
		
		Dificultad dificultad = new Dificultad(2, 0, 3);
		Nivel nivel = new Nivel(mapaDeJuego, dificultad);
		List<Nivel> niveles = new ArrayList<Nivel>();
		niveles.add(nivel);

		Jugador jugador = new Jugador("gonzalo");
		Juego juego = new Juego(jugador, niveles);
		
		List<Pista> pistas = new ArrayList<Pista>();
		Pista pista;
		try {
			pista = new PistaSimple(posicionInicial);
			pistas.add(pista);
		} catch (PosicionesEntradaVaciaException e) {
			e.printStackTrace();
		}
		
		List<Avion> avionesEnAire = new ArrayList<Avion>();
		
		avionesEnAire.add(avion);
		
		mapaDeJuego.setPistas(pistas);
		mapaDeJuego.colocarAvionEnAire(avion);
		
		nivel.aterrizarAviones();
		
		assertEquals(juego.getVelocidadActual(), nivel.getVelocidadDeAviones());
		
	}
	
	
}
