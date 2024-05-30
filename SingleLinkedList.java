package Phase2;

public class SingleLinkedList {
	private SNode first, last;
	private int count=-1;


	public SNode getFirst() {
		if(first==null) return null;
		return first;
	}

	public SNode getLast() {
		if(last==null) return null;
		return last;
	}

	public boolean addFirst(Object data) {
		if(((Martyr)data).equals((Martyr)first.getKey())) return false;
		SNode node = new SNode(data);
		if(first==null) first=last=node;
		else {
			node.setNext(first);
			first=node;
		}
		count++;
		return true;
	}
	public boolean addFirstStack(Object data) {
		SNode node = new SNode(data);
		if(count==-1) first=last=node;
		else {
			node.setNext(first);
			first=node;
		}
		count++;
		return true;
	}

	public boolean addLast(Object data) {
		SNode node = new SNode(data);
		if(first==null) first=last=node;
		else {
			last.setNext(node);
			last=node;
		}
		count++;
		return true;
	}

	public void add(Object data, int index) {
		SNode SNode = new SNode(data);
		if(index<=0) addFirst(data);
		else if(index>count) addLast(data);
		else {
			SNode temp = first;
			for(int i=0; i<index-1; i++) temp=temp.getNext();
			SNode.setNext(temp.getNext());
			temp.setNext(SNode);
			count++;
		}
	}
	
	public boolean find(Martyr data) {
		SNode node = first;
		while(node!=null) {
			if(((Martyr)node.getKey()).getName().equals(data.getName())) return true;
			node=node.getNext();
		}
		return false;
	}
	public boolean add(Object data) {
		if(first==null) {
			first=last=new SNode(data); 
			return true;
		}
		if(((Martyr)data).compareByAge((Martyr)first.getKey())<=0) return addFirst(data);
		if(((Martyr)data).compareByAge((Martyr)last.getKey())>0) return addLast(data);
		SNode temp=first;
		while(temp.getNext()!=null) {
			if(temp.getNext().getKey().equals(data)) return false;
			if(((Martyr)temp.getNext().getKey()).compareByAge((Martyr)data)>0) break;
			temp=temp.getNext();
		}
		SNode newNode = new SNode(data);
		newNode.setNext(temp.getNext());
		temp.setNext(newNode);
		count++;
		return true;
	}

	public boolean removeFirst() {
		if(first==null) return false;
		if(first==last) first=last=null;
		else {
			SNode temp = first;
			first=first.getNext();
			temp.setNext(null);
		}
		count--;
		return true;
	}

	public boolean removeLast() {
		if(first==null) return false;
		if(first==last) first=last=null;
		else {
			SNode temp=first;
			for(int i=0; i<count-1; i++) temp=temp.getNext();
			temp.setNext(null);
			last=temp;
		}
		count--;
		return true;
	}

	public boolean remove(int index) {
		if(index<0 || index>count) return false;
		if(index==0) return removeFirst();
		if(index==count) return removeLast();
		else {
			SNode temp = first;
			for(int i=0; i<index-1; i++) temp=temp.getNext();
			SNode trash = temp.getNext();
			temp.setNext(temp.getNext().getNext());
			trash.setNext(null);
			count--;
			return true;
		}
	}

	public boolean remove(Object data) {
		if(first==null) return false;
		if(first.getKey().equals(data)) return removeFirst();
		if(last.getKey().equals(data)) return removeLast();
		else {
			SNode temp = first;
			for(int i=0; i<count; i++) {
				if(temp.getKey().equals(data)) return remove(i);
				temp=temp.getNext();
			}
		}
		return false;
	}

	public String printList() {
		SNode temp = first;
		String s="";
		for(int i=0; i<=count; i++) {
			if(i!=count) s+=temp.getKey()+" -> ";
			else s+=temp.getKey();
			temp=temp.getNext();
		}
		return s;
	}

//	public void sortByAge(SingleLinkedList sll){
//		SNode node = first;
//		int i=0;
//		while(node!=null){
//			SNode temp = node.getNext(), n=node;
//			while(temp!=null){
//				if(n.getKey().getAge()>temp.getKey().getAge()) n=temp;
//				temp = temp.getNext();
//			}
//			SubNode sub = n.getHead();
//			sll.remove(n.getKey());
//			sll.add(n.getKey(), i);
//			while(sub!=null) {
//				sll.addSubject(sub.getKey(), n.getKey().getName());
//				sub = sub.getNext();
//			}
//			node=first;
//			for(int j=0; j<=i; j++) node=node.getNext();
//			i++;
//		}
//	}
//
//	public double ageAverage(){
//		SNode node=first;
//		int ages=0;
//		while(node!=null){
//			ages+=node.getKey().getAge();
//			node=node.getNext();
//		}
//		if(this.size()>0) return (double)ages/this.size();
//		return 0;
//	}
//
//	public void deleteAgeOver(int a){
//		int i=0;
//		SNode node=first, temp;
//		boolean f;
//		while(node!=null){
//			f=false;
//			temp=node.getNext();
//			if(node.getKey().getAge()>a) {f=true; remove(i);}
//			node=temp;
//			if(!f) i++;
//		}
//	}
//	
//	public boolean addSubject(Subject data, String name) {
//		SubNode node = new SubNode(data);
//		SNode ptr = first;
//		while(ptr!=null) {
//			if(ptr.getKey().getName().equals(name)) break;
//			ptr=ptr.getNext();
//		}
//		if(ptr==null) return false;
//		SubNode temp = ptr.getHead();
//		if(temp==null) {
//			ptr.setHead(node);
//			return true;
//		}
//		while(temp.getNext()!=null) temp=temp.getNext();
//		temp.setNext(node);
//		return true;		
//	}
//	
//	public double calcAverage(String name) {
//		SNode ptr = first;
//		while(ptr!=null) {
//			if(ptr.getKey().getName().equals(name)) break;
//			ptr = ptr.getNext();
//		}
//		if(ptr==null) return 0;
//		SubNode head = ptr.getHead();
//		if(head==null) return 0;
//		int sum=0, num=0;
//		while(head!=null) {
//			sum+=head.getKey().getMark();
//			num++;
//			head=head.getNext();
//		}
//		return (double)sum/num;
//		
//	}
	public void print(SNode node) {
		if(node==null) return;
		System.out.println(node.getKey());
		node = node.getNext();
		if(node!=null) node=node.getNext();
		print(node);
	}
	public void reverse(SingleLinkedList sll) {
		if(first==null) return;
		SNode current = first, pr=null, nx=null;
		while(current!=null) {
			nx = current.getNext();
			current.setNext(pr);
			pr=current;
			current=nx;
		}
		first = pr;
	}
	
	public void reverseRecursively(SNode current, SNode pr) {
		if(current==null) {
			SNode temp=first;
			first=last;
			last=temp;
			return;
		}
		SNode t=current.getNext();
		current.setNext(pr);
		reverseRecursively(t,  current);
	}
	public int size() {
		return count+1;
	}
}
