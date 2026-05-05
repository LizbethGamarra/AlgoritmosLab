package Actividad3;

	public class Node<E> {
		public E data;
		public Node<E> next;
		public Node(E data) {
			this.data = data;
			this.next = null;
		}
		public E getData() { return data; }
		public Node<E> getNext() { return next; }
		public void setNext(Node<E> next) { this.next = next; }
}