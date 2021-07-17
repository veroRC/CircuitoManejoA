package core;

public class ListLinked<T> {
	protected Node<T> first;
	public ListLinked() {
		this.first = null;
	}
	boolean isEmptyList(){
		if (first == null)
            return true;
        else
            return false;
	}
	
	public T searchData (T data) {
		Node<T> nodo = this.first;
		while(nodo != null && !nodo.data.equals(data))
			nodo = nodo.getNext();
		if (nodo != null)
			return nodo.data;
		return null;
	}

	public int search(T data) {
		Node<T> nodo = this.first;
		int pos =0;
		while (nodo != null && !nodo.data.equals(data)) {
			nodo= nodo.getNext();
			pos++;
		}
        if(nodo == null) return -1;
        return pos;
		
	}
	public void insertFirst(T data) {
        Node<T> temp;
        temp = new Node<T>(data,first);
        first = temp;
    }
    
    public void insertLast(T data) {
        if(this.isEmptyList())
            this.insertFirst(data);
        else {
            Node<T> aux = this.first;
            while(aux.getNext() != null)
                aux = aux.getNext();
            aux.setNext(new Node<T>(data));
        }
    }
    
    public void remove(T data) {
    	if(this.isEmptyList()) {
    		return;
    	}
        else {
            Node<T> aux = this.first;
            Node<T> ant = null;
            while((!aux.getData().equals(data)) && aux.getNext() != null) {
            	ant = aux;
            	aux = aux.getNext();
            }
            if (aux.getData().equals(data) && aux == this.first) {
            	this.first = aux.getNext();
            	aux = null;
            } else if (aux.getData().equals(data)) {
            	ant.setNext(aux.getNext());
            	aux = null;
            }
        }
    }
    
    @Override
    public String toString() {
        String str = "";
        Node<T> aux = this.first;
        while (aux != null) {
        	str += aux.getData();
        	aux = aux.getNext();
        }
        return str;
    }



}