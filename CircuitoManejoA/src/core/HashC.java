package core;
import java.util.ArrayList;

public class HashC <E extends Comparable<E>> {
	protected class Element{
		int mark;
		Register<E> reg;
		public Element(int mark, Register<E> reg) {
			this.mark = mark;
			this.reg = reg;
		}
	}
	
	protected ArrayList<Element>table;
	protected int m;
	
	public HashC(int n) {
		this.m = n;
		this.table = new ArrayList<Element>(m);
		for (int i=0; i<m; i++)
			this.table.add(new Element(0,null));
	}
	
	private int functionHash(int key) {
		return key % m;
	}
	
	private int linearProbing(int dressHash, int key) {
		int position = dressHash;
		do {
			Element item = this.table.get(position);
			if (item.mark == 0) {
				return position;
			} else {
				position = position + 1;
				if (position == this.m) {
					position = 0;
				}
			}
		} while (position != dressHash);
		return -1;
	}
	
	public void insert(int key, E reg) {
		int dressHash = this.functionHash(key);
		int position = this.linearProbing(dressHash, key);
		Register<E> nuevo = new Register<E>(key,reg);
		this.table.set(position, new Element(1,nuevo));
	}

	public E search(int key){
		int dressHash = this.functionHash(key);
		int pos = dressHash;
		do {
			Element item = this.table.get(pos);
			Register<E> x = new Register<E>(key,null);
			int compare = item.reg.compareTo(x);
			if (compare == 0) {
				return item.reg.value;
			} else {
				pos = pos + 1;
				if (pos == this.m) {
					pos = 0;
				}
			}
		} while (pos != dressHash);
		return null;
	}
	
	public String toString() {
		String s = "D.Real\tD.Hash\tRegister\n";
		int i = 0;
		for (Element item : table) {
			s+= (i++)+ "-->\t";
			if (item.mark == 1) {
				s += functionHash(item.reg.key) + "\t" + item.reg+"\n";
			} else {
				s +="empty\n";
			}
		}
		return s;
	}
}
