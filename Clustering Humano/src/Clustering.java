import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Set;

public class Clustering {
	private Grafo g;
	private HashMap<String, int[]> personas;
	private Grafo agm;
	private Set<Integer> grupo1;
	private Set<Integer> grupo2;
	private ArrayList<String> nombres = new ArrayList<>();
	
	public Clustering() {
		personas = new HashMap<>();
		grupo1 = new HashSet<Integer>();
		grupo2 = new HashSet<Integer>();
	}
	
	public void agregarPersona(String nombre, int deporte, int musica, int espectaculo, int ciencia) {
		if(parametrosSonValidos(nombre, deporte, musica, espectaculo, ciencia)) {
			int[] intereses = {deporte,musica,espectaculo,ciencia};
			personas.put(nombre, intereses);
		}
		else
			throw new IllegalArgumentException("El nombre no puede ser vacío y los niveles de interés deben ser entre 1 y 5");
	}

	private boolean parametrosSonValidos(String nombre, int deporte, int musica, int espectaculo, int ciencia) {
		return !nombre.isBlank() && esValido(deporte) && esValido(musica) && esValido(espectaculo) && esValido(ciencia);
	}

	private boolean esValido(int nivel) {
		return nivel>0 && nivel<6;
	}
	
	public void generarGrupos() {
		if(personas.size() < 2)
			throw new RuntimeException("El grafo tiene un solo vertice");
		generarGrafo();
		generarAGM();
		int[] vertices = eliminarAristaDeMayorPeso();
		grupo1 = generarGrupo(vertices[0]);
		grupo2 = generarGrupo(vertices[1]);
	}
	
	private void generarGrafo() {
		g = new Grafo(personas.size());
		 
		for(String nombre: personas.keySet())
			nombres.add(nombre);
			
		for(int i=0; i<nombres.size(); i++) {
			for(int j=i+1; j<nombres.size(); j++) 
					 g.agregarArista(i, j, similaridad(nombres.get(i),nombres.get(j)));
		}
	}
	
	private int similaridad(String p1, String p2) {
		return Math.abs(personas.get(p1)[0] - personas.get(p2)[0]) + 
			   Math.abs(personas.get(p1)[1] - personas.get(p2)[1]) + 
			   Math.abs(personas.get(p1)[2] - personas.get(p2)[2]) + 
			   Math.abs(personas.get(p1)[3] - personas.get(p2)[3]); 
	}

	private void generarAGM() {
		agm = g.generarAGM();
	}
	
	private int[] eliminarAristaDeMayorPeso() {
		return Grafo.eliminarAristaDeMayorPeso(agm);
	}
	
	private Set<Integer> generarGrupo(int i) {
		return BFS.alcanzables(agm, i);
	}
	
	public Set<String> getGrupo1() {
		Set<String> ret = new HashSet<String>();
		for(int posicion: grupo1) {
			ret.add(nombres.get(posicion));
		}
		return ret;
	}

	public Set<String> getGrupo2() {
		Set<String> ret = new HashSet<String>();
		for(int posicion: grupo2) {
			ret.add(nombres.get(posicion));
		}
		return ret;
	}

	public HashMap<String, int[]> getPersonas() {
		return personas;
	}
	
}
