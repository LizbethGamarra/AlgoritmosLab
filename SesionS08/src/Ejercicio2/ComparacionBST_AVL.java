package Ejercicio2;

public class ComparacionBST_AVL {

	public static void main(String[] args) {

		// ======================================
		// CASO 1
		// Inserción ordenada
		// ======================================

		int caso1[] = { 10, 20, 30, 40, 50, 60 };

		// ======================================
		// CASO 2
		// Inserción desordenada
		// ======================================

		int caso2[] = { 30, 10, 20, 50, 40, 60 };

		System.out.println("====================================");

		System.out.println("CASO 1 - DATOS ORDENADOS");

		System.out.println("====================================");

		probarCaso(caso1);

		System.out.println("\n====================================");

		System.out.println("CASO 2 - DATOS DESORDENADOS");

		System.out.println("====================================");

		probarCaso(caso2);
	}

	// ==========================================
	// METODO DE PRUEBA
	// ==========================================

	public static void probarCaso(int datos[]) {

		ArbolBST bst = new ArbolBST();

		ArbolAVL avl = new ArbolAVL();

		// ======================================
		// INSERTAR EN AMBOS
		// ======================================

		for (int x : datos) {

			bst.insertar(x);

			avl.insertar(x);
		}

		// ======================================
		// RECORRIDOS
		// ======================================

		System.out.println("\nRECORRIDO BST:");

		bst.inorden();

		System.out.println("RECORRIDO AVL:");

		avl.inorden();

		// ======================================
		// ALTURAS
		// ======================================

		System.out.println("\nALTURA BST: " + bst.altura());

		System.out.println("ALTURA AVL: " + avl.altura());

		// ======================================
		// BUSQUEDAS
		// ======================================

		int buscar1 = 40;

		int buscar2 = 100;

		System.out.println("\nBUSCAR 40 EN BST: " + bst.buscar(buscar1));

		System.out.println("BUSCAR 40 EN AVL: " + avl.buscar(buscar1));

		System.out.println("\nBUSCAR 100 EN BST: " + bst.buscar(buscar2));

		System.out.println("BUSCAR 100 EN AVL: " + avl.buscar(buscar2));

		// ======================================
		// CONCLUSION
		// ======================================

		System.out.println("\nCONCLUSION:");

		System.out.println("El AVL mantiene una altura " + "balanceada gracias " + "a las rotaciones.");

		System.out.println("El BST puede desbalancearse " + "dependiendo del orden " + "de inserción.");
	}
}