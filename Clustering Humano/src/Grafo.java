import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;

/*
  REPRESENTACIÓN CON LISTA DE VECINOS.
*/
public class Grafo 
{
	private ArrayList<Set<Integer>> _vecinos;
	private int _vertice;
	private Integer[][] pesosAristas;
	
	public Grafo(int vertices)
	{
		_vecinos = new ArrayList<Set<Integer>>(vertices);
		
		for (int i = 0; i < vertices; i++)
			_vecinos.add(new HashSet<Integer>());	

		_vertice = vertices;
		pesosAristas = new Integer[vertices][vertices];
		
		for(int i=0; i<pesosAristas.length; i++) {
			for(int j=0; j<pesosAristas[0].length; j++)
				pesosAristas[i][j] = 0;
		}
	}
	
	public boolean esConexo() {
		for(Set<Integer> vecinos: _vecinos) {
			if(vecinos.isEmpty())
				return false;
		}
		return true;	
	}
	
	public void agregarArista ( int i, int j){
		verificarArista(i, j, "agregar");
		_vecinos.get(i).add(j);
		_vecinos.get(j).add(i);
	}

	//EN ESTE PROYECTO LAS ARISTAS TIENEN PESO
	public void agregarArista (int i, int j, int peso) {
		verificarArista(i, j, "agregar");
		_vecinos.get(i).add(j);
		_vecinos.get(j).add(i);
		pesosAristas[i][j] = peso;
		pesosAristas[j][i] = peso;
	}
	
	public boolean tieneAristas() {
		for(Set<Integer> vecinos: _vecinos) {
			if(!vecinos.isEmpty())
				return true;
		}
		return false;
			
	}
	
	public void eliminarArista(int i, int j){
		verificarArista(i, j, "eliminar");
		_vecinos.get(i).remove(j);
		_vecinos.get(j).remove(i);
		pesosAristas[i][j] = 0;
		pesosAristas[j][i] = 0;
	}
	
	public boolean existeArista (int i, int j){
		verificarArista(i, j, "consultar");
		return _vecinos.get(i).contains(j);
	}

	public Set<Integer> vecinos(int i){
		verificarVertice(i, " un vertice ");
		return _vecinos.get(i);
	}

	public int grado (int i){ 
		return _vecinos.get(i).size();
	}
		
	private void verificarArista(int i, int j, String tipo) {
		if (i == j)
			throw new IllegalArgumentException("Se intento "+tipo+" una arista con i=j : "+i +"/"  + j);
		
		verificarVertice(i, tipo);
		verificarVertice(j, tipo);
	}

	private void verificarVertice(int i, String tipo) {
		if (i < 0 || i >= _vertice)
			throw new IllegalArgumentException("Se intento usar "+tipo+" con valores, fuera de rango: "+ i);
	}

	public int vertices(){
		return _vertice;
	}
	
	public boolean esClique( Set<Integer> conjunto) {
		if (conjunto == null)
			throw new IllegalArgumentException("El conjunto no puede ser null");

		for (int v: conjunto)
		   verificarVertice(v," Clique");
		
		if (conjunto.isEmpty())
			return true;
		
		for (int v: conjunto)
		for (int otro: conjunto) 
			if (v != otro)
				if (existeArista(v,otro) == false)
					return false;
		
		return true;
	}
	
	public int pesoDeArista(int i, int j) {
		verificarArista(i, j, "consultar peso de ");
		return pesosAristas[i][j];
	}
	
	public int pesoDeAristaMasGrande() {
		if(!tieneAristas())
			throw new RuntimeException("El grafo no tiene aristas");
		int max = 0;
		for(int i=0; i<pesosAristas.length; i++) {
			for(int j=0; j<pesosAristas[0].length; j++) {
				if(pesosAristas[i][j] > max)
					max = pesosAristas[i][j];
			}
		}
		return max;
	}
	
	//DECISION DE IMPLEMENTACION: GENERAR AGM EN GRAFO YA QUE EN ESTE PROYECTO NO SE REALIZAN OTRAS ACCIONES PROPIAS DE AGM
	public Grafo generarAGM() {
		Grafo t = new Grafo(vertices());
		
		if(vertices() == 1)
			return t;
		if(!esConexo())
			throw new RuntimeException("El grafo no es conexo");
		
		Set<Integer> verticesEnT = new HashSet<>();
		verticesEnT.add(0);
		int i = 0;
		int pesoMin = pesoDeAristaMasGrande()+1;
		int verticeDeT = 0;
		int verticeDeG = 0;
		
		while(i < vertices()-1) {
			for(Integer verticeEnT: verticesEnT) {
				for(Integer vecino: vecinos(verticeEnT)) {
					if(!t.existeArista(verticeEnT, vecino) && pesoDeArista(verticeEnT, vecino) < pesoMin) {
						pesoMin = pesoDeArista(verticeEnT, vecino);
						verticeDeT = verticeEnT;
						verticeDeG = vecino;
					}
				}
			}
			verticesEnT.add(verticeDeG);
			t.agregarArista(verticeDeT, verticeDeG, pesoMin);
			pesoMin = pesoDeAristaMasGrande()+1;
			i++;
		}
		return t;
	}
	
	public static int[] eliminarAristaDeMayorPeso(Grafo g) {
		if(!g.tieneAristas())
			throw new RuntimeException("El grafo no tiene aristas");
		if(g.vertices() == 1)
			throw new RuntimeException("El grafo tiene un solo vertice");
		int pesoMax = -1;
		int vertice_i, vertice_j;
		vertice_i = vertice_j = 0;
		
		for(int i=0; i<g.vertices(); i++) {
			for(int j: g.vecinos(i)) {
				if(g.pesoDeArista(i,j) > pesoMax) {
					pesoMax = g.pesoDeArista(i,j);
					vertice_i = i;
					vertice_j = j;
				}
			}
		}
		g.eliminarArista(vertice_i, vertice_j);
		return new int[]{vertice_i, vertice_j};
	}

}
