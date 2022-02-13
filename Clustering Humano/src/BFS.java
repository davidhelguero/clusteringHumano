import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;

public class BFS 
{
	private static ArrayList<Integer> L;
	private static boolean[] marcados;
	
	public static boolean esConexo(Grafo g) 
	{
		if (g==null)
			throw new IllegalArgumentException("Se intento consultar con un grafo null!" );
		
		if (g.vertices() == 0)
			return true;
		
		return alcanzables(g, 0).size() == g.vertices();
	}

	static Set<Integer> alcanzables(Grafo g, int origen) {
		Set<Integer> ret = new HashSet<Integer>();
		inicializar(g, origen);
		
		while (L.size() >0){
			int i =	L.get(0);
			marcados[i] = true;
			ret.add(i);
			agregarVecinosPendientes(g, i);
			L.remove(0);			
		}
		return ret;
	}

	private static void inicializar(Grafo g, int origen) {
		L = new ArrayList<Integer>();	
		L.add(origen);
		marcados = new boolean[g.vertices()];
	}

	private static void agregarVecinosPendientes(Grafo g, int i) {
		for (int vertice : g.vecinos(i))
			if (marcados[vertice] == false && L.contains(vertice) == false)
				L.add(vertice);
	}
}
