package clusteringAGM;


import java.awt.Point;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;

public class Grafo {

	private LinkedList<Vertice> grafo; // Dentro de esta lista ya estarian los vertices cada uno con su coordenada.
	private LinkedList<Arista> listaAristas; // List con aristas del grafo. Tiene dentro de arista el peso.
	//private HashMap <Arista, Integer> grafoAGM;

	/**
	 * <b>Constructor: </b></br>
	 * <u>Constructor de grafo.</u>
	 * 
	 **/

	public Grafo(){
		
		grafo = new LinkedList<Vertice>();
		listaAristas = new LinkedList<Arista>();
		
	}

	/**
	 * <b>insertarVertice(): </b></br>
	 * <u>Metodo que inserta un nuevo vertice en el grafo con su posicion correspondiente.</u>
	 * 
	 * @param nombreDelVertice
	 * <i>Nombre del vertice.</i>
	 * @param coordenadaX
	 * <i>Coordenada del vertice en X.</i>
	 * @param coordenadaY
	 * <i>Coordenada del vertice en Y.</i>
	 * @throws Exception El nombre ya existe en el grafo
	 **/
	
	public void insertarVertice(String nombreDelVertice, double coordenadaX, double coordenadaY) throws Exception {
		
		// No puede haber dos vertices con el mismo nombre.
		if(existeVertice(nombreDelVertice))
			throw new Exception("El nombre ya existe en el grafo");
		
		Vertice nuevoVertice = new Vertice(nombreDelVertice);
		nuevoVertice.insertarCoordenadas(coordenadaX, coordenadaY);
		grafo.add(nuevoVertice);

	}

	/**
	 * <b>agregarArista(): </b></br>
	 * <u>Metodo que une dos vertice en el grafo, y crea la arista correspondiente.</u>
	 * 
	 * @param vertice1
	 * <i>Nombre del Vertice 1 de la arista.</i>
	 * @param vertice2
	 * <i>Nombre del Vertice 2 de la arista.</i>
	 **/
	
	public void agregarArista(Vertice vertice1, Vertice vertice2) {
		
		Arista nuevaArista = new Arista(vertice1, vertice2);
		listaAristas.add(nuevaArista);
		

	}	
	
	
	public LinkedList<Vertice> getVerticesGrafo() {
		return grafo;
	}

	
	/** 
	 * <b>getListaAristas(): </b></br>
	 * <u>Devuelve la lista de aristas del grafo.</u>
	 **/
	
	public LinkedList<Arista> getListaAristas() {
		return listaAristas;
	}
	
	public boolean existeAristaEnGrafo(Vertice vertice1, Vertice vertice2) {
		
		boolean existeArista = false;
		for(int i = 0; i < listaAristas.size(); i++){
			
			Arista arista = listaAristas.get(i);
			if(arista.getVertice1Artista() == vertice1 && arista.getVertice2Artista() == vertice2)
				existeArista = true;
			
		}
		
		return existeArista;
	}
	
// https://es.wikibooks.org/wiki/Programaci%C3%B3n_en_Java/Ap%C3%A9ndices/Implementaci%C3%B3n_del_Algoritmo_de_Kruskal_en_Java
	public void arbolGeneradorMinimo(int cantidadVertices) {
		List<Point> listaVertices = new ArrayList<>();
		while(listaVertices.size() < cantidadVertices) {
			
		}
/*		for(Arista arista: listaAristas.keySet()) {
		}*/
	}
	
	/**
	 * <b>rellenarVecinosDeNodo(): </b></br>
	 * <u>Metodo que inserta los vecinos de un nodo.</u>
	 * 
	 * @param nombreDelNodo    
	 * <i>Nombre del nodo al que le insertan su vecinos.</i>
	 * @param nombresDeVecinos
	 *  <i>Array con nombres de los vecinos del nodo.</i>
	 * 
	 **/

	public void rellenarVecinosDeVertice(String nombreDelNodo, String[] nombresDeVecinos) {

		int indexNodo = obtenerIndexDeNodo(nombreDelNodo);
		Vertice nodoParaInsertarVecinos = grafo.get(indexNodo);

            for(String nombresDeVecino : nombresDeVecinos) {
                nodoParaInsertarVecinos.insertarVecinoConPeso(nombresDeVecino, null);
            }

	}

	/**
	 * <b>obtenerVecinosDeVertice(): </b></br>
	 * <u>Metodo que da todos los vecinos de un vertice.</u>
	 * 
	 * @param nombreDeVertice <u>Nombre del vertice del que se busca sus
	 *                        vecinos.</u>
	 *
	 * @return Array de string de vecinos del correspondiente vertice del grafo.
	 *
	 **/

	public String[] obtenerVecinosDeVertice(String nombreDeNodo) {

		int indexNodo = obtenerIndexDeNodo(nombreDeNodo);
		Vertice nodoParaInsertarVecinos = grafo.get(indexNodo);

		String[] vecinosDeNodo = nodoParaInsertarVecinos.darVecinos();

		return vecinosDeNodo;

	}

	/**
	 * <b>darVecinosDeNodo(): </b></br>
	 * <u>Metodo que busca el index del nodo en la lista.</u>
	 * 
	 * @param nombreDelNodo <u>Nombre del nodo del que se busca su index en la
	 *                      lista.</u>
	 *
	 * @return int del index correspondiente en la lista de nodos del grafo.
	 *
	 **/

	private int obtenerIndexDeNodo(String nombreDelNodo) {

		int index = 0;

		for (int i = 0; i < grafo.size(); i++) {

			if (grafo.get(i).getNombre().equals(nombreDelNodo))
				index = i;

		}

		return index;
	}

	private boolean existeVertice(String nombreDeVertice) {
		
		boolean existe = false;
		
		for(int i = 0; i < grafo.size(); i++) {
			if(grafo.get(i).getNombre().equals(nombreDeVertice))
				existe = true;
		}
		
		return existe;
	}
	
	
	// Getters && Setters
	public int getCantidadDeVertices() {
		return grafo.size();
	}

	
}
