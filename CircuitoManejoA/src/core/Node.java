package core;

public class Node<T> {

	public T data;
	protected Node<T> siguiente;
	

	public Node(T data, Node<T> siguiente) {
		this.data = data;
		this.siguiente = siguiente;
		
	}

	public Node(T data) {
		this.data = data;
		this.siguiente = null;
	}

	public Node<T> getNext() {
		// TODO Auto-generated method stub
		return siguiente;
	}

	public T getData() {
		// TODO Auto-generated method stub
		return data;
	}

	public void setNext(Node<T> siguiente) {
		this.siguiente = siguiente;
		
	}
	

}
