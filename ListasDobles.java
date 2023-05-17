 
 

import java.util.Scanner;

 
 
class Node {
	protected int data;  // Almacena el valor del nodo
	protected Node next, prev;  // Referencias al nodo siguiente y anterior

	/* Constructor sin parámetros */
	public Node() {
		next = null;
		prev = null;
		data = 0;
	}

	/* Constructor con parámetros */
	public Node(int d, Node n, Node p) {
		data = d;
		next = n;
		prev = p;
	}

	/* Función para establecer la referencia al siguiente nodo */
	public void setLinkNext(Node n) {
		next = n;
	}

	/* Función para establecer la referencia al nodo anterior */
	public void setLinkPrev(Node p) {
		prev = p;
	}

	/* Función para obtener la referencia al siguiente nodo */
	public Node getLinkNext() {
		return next;
	}

	/* Función para obtener la referencia al nodo anterior */
	public Node getLinkPrev() {
		return prev;
	}

	/* Función para establecer el valor del nodo */
	public void setData(int d) {
		data = d;
	}

	/* Función para obtener el valor del nodo */
	public int getData() {
		return data;
	}
}


/* Clase linkedList */
class linkedList {
	protected Node start;  // Referencia al primer nodo de la lista
	protected Node end;  // Referencia al último nodo de la lista
	public int size;  // Tamaño de la lista

	/* Constructor */
	public linkedList() {
		start = null;
		end = null;
		size = 0;
	}

	/* Función para verificar si la lista está vacía */
	public boolean isEmpty() {
		return start == null;
	}

	/* Función para obtener el tamaño de la lista */
	public int getSize() {
		return size;
	}

/* Función para insertar un elemento al inicio */
public void insertAtStart(int val) {
    Node nptr = new Node(val, null, null); // Crear un nuevo nodo con el valor dado
    if (start == null) { // Si la lista está vacía
        start = nptr; // Establecer el nuevo nodo como el primer nodo
        end = start; // Establecer el nuevo nodo como el último nodo también (ya que es el único nodo en la lista)
    } else { // Si la lista no está vacía
        start.setLinkPrev(nptr); // Establecer el enlace previo del nodo existente en el inicio para que apunte al nuevo nodo
        nptr.setLinkNext(start); // Establecer el enlace siguiente del nuevo nodo para que apunte al nodo existente en el inicio
        start = nptr; // Establecer el nuevo nodo como el primer nodo
    }
    size++; // Incrementar el tamaño de la lista
}
/*
En insertAtStart, esta función inserta un nuevo nodo al inicio de la lista
enlazada doble. Si la lista está vacía, el nuevo nodo se convierte tanto
en el primer nodo como en el último nodo de la lista. Si la lista no está
vacía, el nuevo nodo se enlaza correctamente con el nodo existente en el 
inicio y se establece como el nuevo primer nodo. Al final, se incrementa
el tamaño de la lista en uno.
*/

 
/* Función para insertar un elemento al final */
public void insertAtEnd(int val) {
    Node nptr = new Node(val, null, null); // Crear un nuevo nodo con el valor dado
    if (start == null) { // Si la lista está vacía
        start = nptr; // Establecer el nuevo nodo como el primer nodo
        end = start; // Establecer el nuevo nodo como el último nodo también (ya que es el único nodo en la lista)
    } else { // Si la lista no está vacía
        nptr.setLinkPrev(end); // Establecer el enlace previo del nuevo nodo para que apunte al nodo existente en el final
        end.setLinkNext(nptr); // Establecer el enlace siguiente del nodo existente en el final para que apunte al nuevo nodo
        end = nptr; // Establecer el nuevo nodo como el último nodo
    }
    size++; // Incrementar el tamaño de la lista
}


/* Función para insertar un elemento en una posición */
/*
Dentro del `if(i == pos)` se realizan los cambios necesarios para insertar un nuevo nodo en una posición 
específica de la lista. Veamos cómo funciona paso a paso:

1. `Node tmp = ptr.getLinkNext();`: Se guarda una referencia al nodo siguiente al nodo actual en la variable
`tmp`. Esto es necesario para mantener la continuidad de la lista después de insertar el nuevo nodo.

2. `ptr.setLinkNext(nptr);`: Se establece el enlace siguiente del nodo actual (`ptr`) para que apunte al
nuevo nodo (`nptr`). Ahora el nodo actual apunta al nuevo nodo.

3. `nptr.setLinkPrev(ptr);`: Se establece el enlace previo del nuevo nodo (`nptr`) para que apunte al nodo 
actual (`ptr`). Ahora el nuevo nodo apunta al nodo anterior.

4. `nptr.setLinkNext(tmp);`: Se establece el enlace siguiente del nuevo nodo (`nptr`) para que apunte al nodo 
siguiente (`tmp`). Ahora el nuevo nodo apunta al nodo que originalmente seguía al nodo actual.

5. `tmp.setLinkPrev(nptr);`: Se establece el enlace previo del nodo siguiente (`tmp`) para que apunte al nuevo 
nodo (`nptr`). Ahora el nodo siguiente apunta al nuevo nodo.

En resumen, estos cambios realizados dentro del `if` se encargan de ajustar los enlaces de los nodos para insertar 
correctamente el nuevo nodo en la posición especificada. Se aseguran de que el enlace previo y el enlace siguiente
del nuevo nodo y los nodos adyacentes estén correctamente establecidos, manteniendo la estructura de la lista 
doblemente enlazada.

Recuerda que la variable `ptr` se utiliza como un puntero para recorrer la lista y encontrar el nodo en la posición
`pos`.
*/
public void insertAtPos(int val, int pos) {
    Node nptr = new Node(val, null, null); // Crear un nuevo nodo con el valor dado

    if (pos == 1) { // Si la posición es 1, insertar el nuevo nodo al inicio de la lista
        insertAtStart(val);
        return;
    }

    Node ptr = start; // Puntero para recorrer la lista
    for (int i = 2; i <= size; i++) { // Comenzando desde la posición 2 hasta el tamaño de la lista
        if (i == pos) { // Cuando se alcanza la posición deseada
            Node tmp = ptr.getLinkNext(); // Obtener el nodo siguiente al nodo actual
            ptr.setLinkNext(nptr); // Establecer el enlace siguiente del nodo actual para que apunte al nuevo nodo
            nptr.setLinkPrev(ptr); // Establecer el enlace previo del nuevo nodo para que apunte al nodo actual
            nptr.setLinkNext(tmp); // Establecer el enlace siguiente del nuevo nodo para que apunte al nodo siguiente
            tmp.setLinkPrev(nptr); // Establecer el enlace previo del nodo siguiente para que apunte al nuevo nodo
        }
        ptr = ptr.getLinkNext(); // Mover el puntero al siguiente nodo
    }
    size++; // Incrementar el tamaño de la lista
}


/* Función para eliminar un nodo en una posición */
public void deleteAtPos(int pos) {
    if (pos == 1) { // Si la posición es 1 (primer nodo)
        if (size == 1) { // Si la lista solo contiene un nodo
            start = null; // Se establece el inicio y fin a null, eliminando el nodo
            end = null;
            size = 0; // Se actualiza el tamaño a 0
            return; // Se finaliza la función
        }
        start = start.getLinkNext(); // Se actualiza el inicio para que apunte al siguiente nodo
        start.setLinkPrev(null); // Se establece el enlace previo del nuevo inicio a null
        size--; // Se reduce el tamaño de la lista
        return; // Se finaliza la función
    }
    if (pos == size) { // Si la posición es igual al tamaño (último nodo)
        end = end.getLinkPrev(); // Se actualiza el fin para que apunte al nodo previo
        end.setLinkNext(null); // Se establece el enlace siguiente del nuevo fin a null
        size--; // Se reduce el tamaño de la lista
    }
    Node ptr = start.getLinkNext(); // Puntero para recorrer la lista, se inicia desde el segundo nodo
    for (int i = 2; i <= size; i++) { // Comenzando desde la posición 2 hasta el tamaño de la lista
        if (i == pos) { // Cuando se alcanza la posición deseada
            Node p = ptr.getLinkPrev(); // Obtener el nodo previo al nodo actual
            Node n = ptr.getLinkNext(); // Obtener el nodo siguiente al nodo actual
            p.setLinkNext(n); // Establecer el enlace siguiente del nodo previo para que apunte al nodo siguiente
            n.setLinkPrev(p); // Establecer el enlace previo del nodo siguiente para que apunte al nodo previo
            size--; // Se reduce el tamaño de la lista
            return; // Se finaliza la función
        }
        ptr = ptr.getLinkNext(); // Mover el puntero al siguiente nodo
    }
}


	/* Función para mostrar el estado de la lista */
	public void display() {
		System.out.print("\nListas doblemente enlazadas(GRUPO 1) = ");
		if (size == 0) {
			System.out.print("empty\n");
			return;
		}
		if (start.getLinkNext() == null) {
			System.out.println(start.getData());
			return;
		}
		Node ptr = start;
		System.out.print(start.getData() + " <-> ");
		ptr = start.getLinkNext();

		while (ptr.getLinkNext() != null) {
			System.out.print(ptr.getData() + " <-> ");
			ptr = ptr.getLinkNext();
		}
		System.out.print(ptr.getData() + "\n");
	}
}

/* Class DoublsyLinkedList */

public class ListasDobles {
	public static void main(String[] args) {
		Scanner scan = new Scanner(System.in);

		/* Creating object of linkedList */
		linkedList list = new linkedList();
		System.out.println("PRUEBA DE LISTAS DOBLEMENTE ENLAZADAS\n");
		char ch;

		/* Perform list operations */

		do {
			System.out.println("\nListas doblemente enlazadas(GRUPO 01)\n");
			System.out.println("1. Insertar en el inicio");
			System.out.println("2. Insertar al final");
			System.out.println("3. Insertar en la posicion");
			System.out.println("4. Eliminar la posicion");
			System.out.println("5. Revisar si esta vacio");
			System.out.println("6. Obtener tamaño");

			int choice = scan.nextInt();

			switch (choice) {
			case 1:
				System.out.println("Escribe el elemento entero para insertarlo");
				list.insertAtStart(scan.nextInt());
				break;

			case 2:
				System.out.println("Escribe el elemento entero para insertarlo");
				list.insertAtEnd(scan.nextInt());
				break;

			case 3:
				System.out.println("Escribe el elemento entero para insertarlo");
				int num = scan.nextInt();
				System.out.println("Escribe la posicion");
				int pos = scan.nextInt();
				if (pos < 1 || pos > list.getSize())
					System.out.println("Posicion invalida\n");
				else
					list.insertAtPos(num, pos);
				break;

			case 4:
				System.out.println("Escribe la posicion");
				int p = scan.nextInt();
				if (p < 1 || p > list.getSize())
					System.out.println("Posicion invalida\n");
				else
					list.deleteAtPos(p);
				break;

			case 5:
				System.out.println("Estado vacio = " + list.isEmpty());
				break;

			case 6:
				System.out.println("Tamaño = " + list.getSize() + " \n");
				break;

			default:
				System.out.println("Entrada incorrecta \n ");
				break;

			}

			/* Display List */
			list.display();
			System.out.println("\nDeseas continuar (Escribe S o N) \n");
			ch = scan.next().charAt(0);

		} while (ch == 'S' || ch == 's');
	}
}