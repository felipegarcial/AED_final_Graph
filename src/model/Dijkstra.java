package model;

import java.util.ArrayList;
import java.util.List;
import java.util.PriorityQueue;
import java.util.Scanner;

class Dijkstra {
	private final int MAX_VERTEX_NUMBER;
	private final int INFINITY_DISTANCE;

	private ListAdjacency<List<Node>> adyList;
	private int[] distance;
	private boolean[] visited;
	private PriorityQueue<Node> pQueue;
	private int vertexNumber;
	private int[] prev;
	private boolean dijkstraExcuted;

	Dijkstra(int vertexNumber) {
		MAX_VERTEX_NUMBER = 10005;
		INFINITY_DISTANCE = 1 << 10;

		adyList = new ListAdjacency<List<Node>>();
		distance = new int[MAX_VERTEX_NUMBER];
		visited = new boolean[MAX_VERTEX_NUMBER];
		pQueue = new PriorityQueue<Node>();
		prev = new int[MAX_VERTEX_NUMBER];
		vertexNumber = 0;
		dijkstraExcuted = false;
		this.vertexNumber = vertexNumber;
		
		for (int i = 0; i <= vertexNumber; ++i) {
			adyList.add(new ArrayList<Node>());
		}
		
	}

	// En el caso de java usamos una clase que representara el pair de C++
	class Node implements Comparable<Node> {
		int first, second;

		Node(int d, int p) { // constructor
			this.first = d;
			this.second = p;
		}

		public int compareTo(Node other) { // es necesario definir un comparador para el correcto funcionamiento del
											// PriorityQueue
			if (second > other.second)
				return 1;
			if (second == other.second)
				return 0;
			return -1;
		}
	};

	// función de inicialización
	private void init() {
		for (int i = 0; i <= vertexNumber; ++i) {
			distance[i] = INFINITY_DISTANCE; // inicializamos todas las distances con valor INFINITY_DISTANCEinito
			visited[i] = false; // inicializamos todos los vértices como no visiteds
			prev[i] = -1; // inicializamos el prev del vertice i con -1
		}
	}

	// Paso de relajacion
	private void relajacion(int actual, int adyListacente, int peso) {
		// Si la distance del origen al vertice actual + peso de su arista es menor a la
		// distance del origen al vertice adyListacente
		if (distance[actual] + peso < distance[adyListacente]) {
			distance[adyListacente] = distance[actual] + peso; // relajamos el vertice actualizando la distance
			prev[adyListacente] = actual; // a su vez actualizamos el vertice prev
			pQueue.add(new Node(adyListacente, distance[adyListacente])); // agregamos adyListacente a la cola de prioridad
		}
	}

	void dijkstra(int inicial) {
		init(); // inicializamos nuestros arreglos
		pQueue.add(new Node(inicial, 0)); // Insertamos el vértice inicial en la Cola de Prioridad
		distance[inicial] = 0; // Este paso es importante, inicializamos la distance del inicial como 0
		int actual, adyListacente, peso;
		while (!pQueue.isEmpty()) { // Mientras cola no este vacia
			actual = pQueue.element().first; // Obtengo de la cola el nodo con menor peso, en un comienzo será el inicial
			pQueue.remove(); // Sacamos el elemento de la cola
			if (visited[actual])
				continue; // Si el vértice actual ya fue visited entonces sigo sacando elementos de la
							// cola
			visited[actual] = true; // Marco como visited el vértice actual

			for (int i = 0; i < adyList.get(actual).size(); ++i) { // reviso sus adyListacentes del vertice actual
				adyListacente = adyList.get(actual).get(i).first; // id del vertice adyListacente
				peso = adyList.get(actual).get(i).second; // peso de la arista que une actual con adyListacente ( actual ,
														// adyListacente )
				if (!visited[adyListacente]) { // si el vertice adyListacente no fue visited
					relajacion(actual, adyListacente, peso); // realizamos el paso de relajacion
				}
			}
		}

		System.out.printf("distances mas cortas iniciando en vertice %d\n", inicial);
		for (int i = 1; i <= vertexNumber; ++i) {
			System.out.printf("Vertice %d , distance mas corta = %d\n", i, distance[i]);
		}
		dijkstraExcuted = true;
	}

	void addEdge(int origen, int destino, int peso, boolean dirigido) {
		adyList.get(origen).add(new Node(destino, peso)); // grafo diridigo
		if (!dirigido)
			adyList.get(destino).add(new Node(origen, peso)); // no dirigido
	}

	void printShortestPath() {
		if (!dijkstraExcuted) {
			System.out.println(
					"Es necesario ejecutar el algorithmo de Dijkstra antes de poder imprimir el camino mas corto");
			return;
		}
		Scanner sc = new Scanner(System.in); // para lectura de datos
		System.out.println("\n**************Impresion de camino mas corto**************");
		System.out.printf("Ingrese vertice destino: ");
		int destino;
		destino = sc.nextInt();
		print(destino);
		System.out.printf("\n");
	}

	// Impresion del camino mas corto desde el vertice inicial y final ingresados
	void print(int destino) {
		if (prev[destino] != -1) // si aun poseo un vertice prev
			print(prev[destino]); // recursivamente sigo explorando
		System.out.printf("%d ", destino); // terminada la recursion imprimo los vertices recorridos
	}

	public int getNumberOfVertices() {
		return vertexNumber;
	}

	public void setNumberOfVertices(int vertexNumber) {
		this.vertexNumber = vertexNumber;
	}
}