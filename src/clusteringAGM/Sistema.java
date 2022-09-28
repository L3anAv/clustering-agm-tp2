package clusteringAGM;

import java.awt.Point;
import java.util.HashMap;

import javax.swing.JLabel;

public class Sistema {

	private Grafo grafo;
	private HashMap<String, Point> listaVertices;

	public Sistema() {

		listaVertices = new HashMap<>();
	}

	public void agregarVertice(String nombre, Integer posicionX, Integer posicionY) {
		Point coordenada = new Point(posicionX, posicionY);
		listaVertices.put(nombre, coordenada);

	}

	public void crearGrafo() {
		grafo = new Grafo();
		// tengo que recorrer listaVertices y agregar cada coordenada (x,y)
		// https://www.discoduroderoer.es/formas-de-recorrer-un-hashmap-en-java/

		for (int i = 0; i < listaVertices.size(); ++i) {
			for (String vertice1 : listaVertices.keySet()) {
				for (String vertice2 : listaVertices.keySet()) {
					if (vertice1 != vertice2) {
						grafo.agregarArista(listaVertices.get(vertice1), listaVertices.get(vertice2));
					}

				}
			}
		}
	}

	public HashMap<String, Point> getListaVertices() {
		return listaVertices;
	}

}