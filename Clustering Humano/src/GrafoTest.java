import static org.junit.Assert.*;

import java.util.HashSet;

import org.junit.Test;

public class GrafoTest {

	@Test (expected=IllegalArgumentException.class)
	public void agregarAristaVerticeIgual()
	{
		Grafo g = new Grafo(5);
		g.agregarArista(0, 0);
	}

	@Test (expected=IllegalArgumentException.class)
	public void agregarAristaFueraDeRangoSuperior()
	{
		Grafo g = new Grafo(5);
		g.agregarArista(5, 0);
	}

	@Test (expected=IllegalArgumentException.class)
	public void agregarAristaFueraDeRangoSuperiorJ()
	{
		Grafo g = new Grafo(5);
		g.agregarArista(0, 5);
	}
	
	@Test (expected=IllegalArgumentException.class)
	public void agregarAristaFueraDeRangoInferior()
	{
		Grafo g = new Grafo(5);
		g.agregarArista(-1, 0);
	}

	@Test (expected=IllegalArgumentException.class)
	public void agregarAristaFueraDeRangoInferiorJ()
	{
		Grafo g = new Grafo(5);
		g.agregarArista(0, -1);
	}

	@Test
	public void agregarAristaTest() 
	{
		Grafo g = new Grafo(5); // Setup
		g.agregarArista(0, 1); // Exercise
		assertTrue(g.existeArista(0, 1)); // Verify
	}
	
	@Test
	public void agregarAristaSimetriaTest() 
	{
		Grafo g = new Grafo(5); // Setup
		g.agregarArista(0, 1); // Exercise
		assertTrue(g.existeArista(1, 0)); // Verify
	}
	
	@Test
	public void eliminarAristaTest() 
	{
		Grafo g = new Grafo(5);
		g.agregarArista(0, 1);
		g.eliminarArista(0, 1);
		assertFalse(g.existeArista(0, 1));
	}
	
	@Test
	public void gradoTest()
	{
		Grafo g = grafoEjemplo();
		assertEquals(2, g.grado(1));
	}

	@Test
	public void gradoCeroTest()
	{
		Grafo g = grafoEjemplo();
		assertEquals(0, g.grado(4));
	}
	
	@Test
	public void vecinoTest()
	{
		Grafo g = new Grafo(4);
		g.agregarArista(0, 1);
		g.agregarArista(0, 2);
		g.agregarArista(0, 3);
		
		HashSet<Integer> vecinos = (HashSet<Integer>)g.vecinos(0);
		
		assertEquals(3, vecinos.size());
		assertTrue(g.vecinos(0).contains(1));
		assertTrue(g.vecinos(0).contains(2));
		assertTrue(g.vecinos(0).contains(3));
	}
	
	@Test (expected = IllegalArgumentException.class)
	public void agregarAristaConPesoDeVerticeNoExistente() {
		Grafo g = new Grafo(1);
		g.agregarArista(0, 1, 10);
	}
	
	@Test (expected = IllegalArgumentException.class)
	public void pesoDeAristaNoExistente() {
		Grafo g = new Grafo(1);
		assertEquals(10, g.pesoDeArista(0, 1));
	}
	
	@Test
	public void pesoDeArista() {
		Grafo g = new Grafo(2);
		g.agregarArista(0, 1, 10);
		assertEquals(10, g.pesoDeArista(0, 1));
	}
	
	@Test
	public void noTieneAristas() {
		Grafo g = new Grafo(3);
		assertFalse(g.tieneAristas());
	}
	
	public void tieneAristas() {
		Grafo g = grafoEjemplo();
		assertTrue(g.tieneAristas());
	}
	
	@Test (expected = RuntimeException.class)
	public void pesoDeAristaMasGrandeDeGrafoSinAristas() {
		Grafo g = new Grafo(1);
		assertEquals(0, g.pesoDeAristaMasGrande());
	}
	
	@Test
	public void pesoDeAristaMasGrande() {
		Grafo g = grafoEjemplo1();
		assertEquals(15, g.pesoDeAristaMasGrande());
	}
	
	@Test
	public void grafoNoConexo() {
		Grafo g = new Grafo(5);
		assertFalse(g.esConexo());
	}

	@Test
	public void grafoConexo() {
		Grafo g = grafoEjemplo1();
		assertTrue(g.esConexo());
	}
	

	@Test
	public void generarAGMdeGrafoDeUnVertice() {
		Grafo g = new Grafo(1);
		assertEquals(1, g.generarAGM().vertices());
	}
	
	@Test (expected = RuntimeException.class)
	public void generarAGMdeGrafoNoConexo() {
		Grafo g = new Grafo(5);
		g.generarAGM();
	}
	
	@Test
	public void generarAGM() {
		Grafo g = grafoEjemplo1();
		assertTrue(g.generarAGM().esConexo());
		assertTrue(g.generarAGM().existeArista(0, 1) && g.generarAGM().existeArista(0, 2));
	}
	
	@Test (expected = RuntimeException.class)
	public void eliminarAristaDeMayorPesoDeGrafoDeUnVertice() {
		Grafo g = new Grafo(1);
		Grafo.eliminarAristaDeMayorPeso(g);
	}
	
	@Test
	public void vertice1DeAristaDeMayorPesoEliminada() {
		Grafo g = grafoEjemplo1();
		assertEquals(1, Grafo.eliminarAristaDeMayorPeso(g)[0]);
	}
	
	@Test
	public void vertice2DeAristaDeMayorPesoEliminada() {
		Grafo g = grafoEjemplo1();
		assertEquals(2, Grafo.eliminarAristaDeMayorPeso(g)[1]);
	}
	private Grafo grafoEjemplo() {
		Grafo g = new Grafo(5);
		g.agregarArista(0, 1);
		g.agregarArista(1, 2);
		g.agregarArista(2, 3);
		g.agregarArista(3, 0);
		return g;
	}
	
	private Grafo grafoEjemplo1() {
		Grafo g = new Grafo(3);
		g.agregarArista(0, 1, 10);
		g.agregarArista(0, 2, 5);
		g.agregarArista(2, 1, 15);
		return g;
	}
}
