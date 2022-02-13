import static org.junit.Assert.*;

import java.util.HashMap;

import org.junit.Test;

public class ClusteringTest {

	@Test (expected = IllegalArgumentException.class)
	public void agregarPersonaConNombreInvalido() {
		Clustering c = new Clustering();
		c.agregarPersona("", 2, 2, 2, 2);
	}
	
	@Test (expected = IllegalArgumentException.class)
	public void agregarPersonaConDeporteInvalido() {
		Clustering c = new Clustering();
		c.agregarPersona("David", 0, 2, 3, 4);
	}
	
	@Test (expected = IllegalArgumentException.class)
	public void agregarPersonaConMusicaInvalido() {
		Clustering c = new Clustering();
		c.agregarPersona("David", 2, -1, 1, 5);
	}
	
	@Test (expected = IllegalArgumentException.class)
	public void agregarPersonaConEspectaculoInvalido() {
		Clustering c = new Clustering();
		c.agregarPersona("David", 2, 1, 6, 4);
	}
	
	@Test (expected = IllegalArgumentException.class)
	public void agregarPersonaConCienciaInvalido() {
		Clustering c = new Clustering();
		c.agregarPersona("David", 2, 2, 2, 7);
	}
	
	@Test (expected = RuntimeException.class)
	public void generarGruposConUnaSolaPersona() {
		Clustering c = new Clustering();
		c.agregarPersona("David", 2, 2, 2, 2);
		c.generarGrupos();
	}
	
	@Test
	public void generarGrupos() {
		Clustering c = new Clustering();
		c.agregarPersona("David", 2, 2, 2, 2);
		c.agregarPersona("Ezequiel", 2, 2, 2, 2);
		c.generarGrupos();
		String[] x = {"David"};
		String[] y = {"Ezequiel"};
		assertArrayEquals(x, c.getGrupo1().toArray());
		assertArrayEquals(y, c.getGrupo2().toArray());
	}
}
