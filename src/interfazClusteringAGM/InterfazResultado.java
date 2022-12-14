package interfazClusteringAGM;

import javax.swing.JFrame;
import javax.swing.JLabel;

import clusteringAGM.Arista;
import clusteringAGM.Sistema;
import clusteringAGM.Vertice;
import java.awt.Panel;
import java.awt.Color;
import java.awt.Font;

import javax.swing.JSeparator;

public class InterfazResultado {

	private Sistema sistema;
	private JFrame frmResultado;

	public InterfazResultado(Sistema sistema) {
		this.sistema = sistema;
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frmResultado = new JFrame();
		frmResultado.setTitle("Resultado");
		frmResultado.setBounds(100, 100, 700, 450);
		frmResultado.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frmResultado.getContentPane().setLayout(null);
		
		dibujarAristas();
		dibujarVertices();
		frmResultado.revalidate();
		frmResultado.repaint();
	}

	private void dibujarVertices() {
		for(Vertice vertice: sistema.getGrafo().getVerticesGrafo()) {
			dibujarVertice(vertice);
		}
	}

	private void dibujarVertice(Vertice vertice) {
		Panel panel = new Panel();
		panel.setBackground(Color.BLACK);
		panel.setBounds(vertice.getcoordenadaVertice().x*10, vertice.getcoordenadaVertice().y*10, 10, 10);
		frmResultado.getContentPane().add(panel);
		
		JLabel nombreVertice = new JLabel(vertice.getNombre());
		nombreVertice.setFont(new Font("Tahoma", Font.PLAIN, 10));
		nombreVertice.setBounds(vertice.getcoordenadaVertice().x*10 + 15, vertice.getcoordenadaVertice().y*10 + 5, 30, 30);
		frmResultado.getContentPane().add(nombreVertice);
		
		String coordenada = "(" + vertice.getcoordenadaVertice().x + "," + vertice.getcoordenadaVertice().y + ")";
		JLabel coordenadaVertice = new JLabel((coordenada));
		coordenadaVertice.setFont(new Font("Tahoma", Font.PLAIN, 10));
		coordenadaVertice.setBounds(vertice.getcoordenadaVertice().x*10 + 25, vertice.getcoordenadaVertice().y*10 + 5, 40, 40);
		frmResultado.getContentPane().add(coordenadaVertice);
	}

	private void dibujarAristas() {
		for(Arista arista: sistema.getGrafo().getListaAristas()) {
			dibujarArista(arista);
		}
	}

	private void dibujarArista(Arista arista) {
		// Dejamos en coorX la coordenada que esta mas a la izquierda
		int coorX = 5;
		int coorXDestino = 5;
		int coorY = 5;
		int coorYDestino = 5;
		if (arista.getCoordenada1().x*10 < arista.getCoordenada2().x*10) {
			coorX += arista.getCoordenada1().x*10;
			coorY += arista.getCoordenada1().y*10;
			coorXDestino += arista.getCoordenada2().x*10;
			coorYDestino += arista.getCoordenada2().y*10;
		} else {
			coorX += arista.getCoordenada2().x*10;
			coorY += arista.getCoordenada2().y*10;
			coorXDestino += arista.getCoordenada1().x*10;
			coorYDestino += arista.getCoordenada1().y*10;
		}
		
		double difX = coorXDestino - coorX;
		double difY = (coorY < coorYDestino) ? coorYDestino - coorY : coorY - coorYDestino;
		double porcentajeDeAumentoParaX = difX / difY;
		double porcentajeDeAumentoParaY = difY / difX;
		double contadorAumentoX = porcentajeDeAumentoParaX;
		double contadorAumentoY = porcentajeDeAumentoParaY;
		
		while(coorX != coorXDestino || coorY != coorYDestino) {
			JSeparator separator = new JSeparator();
			separator.setBounds(coorX, coorY, 1, 1);
			separator.setForeground(Color.BLACK);
			separator.setBackground(Color.BLACK);
			frmResultado.getContentPane().add(separator);
			if (porcentajeDeAumentoParaX < porcentajeDeAumentoParaY) {
				if (contadorAumentoX >= 1){
					coorX++;
					contadorAumentoX--;
				}
				contadorAumentoX += porcentajeDeAumentoParaX;
				if (coorY < coorYDestino) {
					coorY++;
				} else if (coorY > coorYDestino) {
					coorY--;
				}
			} else if (porcentajeDeAumentoParaX > porcentajeDeAumentoParaY) {
				if (contadorAumentoY >= 1){
					if (coorY < coorYDestino) {
						coorY++;
					} else if (coorY > coorYDestino) {
						coorY--;
					}
					contadorAumentoY--;
				}
				contadorAumentoY += porcentajeDeAumentoParaY;
				if (coorX != coorXDestino) {
					coorX++;
				}
			} else {
				coorX++;
				if (coorY < coorYDestino) {
					coorY++;
				} else if (coorY > coorYDestino) {
					coorY--;
				}
			}
		}
	}

	public JFrame getFrame() {
		return frmResultado;
	}
}
