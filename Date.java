package Phase2;

public class Date implements Comparable<Date>{
	private String name;
	private SingleLinkedList martyrs;
	
	public Date(String name) {
		super();
		this.name = name;
		martyrs = new SingleLinkedList();
	}
	
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public SingleLinkedList getMartyrs() {
		return martyrs;
	}
	public void setMartyrs(SingleLinkedList martyrs) {
		this.martyrs = martyrs;
	}

	@Override
	public int compareTo(Date o) {
		String[] a1 = this.name.split("/");
		String[] a2 = o.getName().split("/");
		java.util.Date d1=new java.util.Date(Integer.parseInt(a1[2]), Integer.parseInt(a1[0]), Integer.parseInt(a1[1])),
				d2=new java.util.Date(Integer.parseInt(a2[2]), Integer.parseInt(a2[0]), Integer.parseInt(a2[1]));
		return d1.compareTo(d2);
	}
	
	public double averageAges() {
		double sum=0, counter=0;
		SNode node = martyrs.getFirst();
		while(node!=null) {
			sum+=((Martyr)node.getKey()).getAge();
			counter++;
			node=node.getNext();
		}
		return ((double)(int)((sum/counter)*100))/100;
	}
	public Martyr getOldest() {
		if(martyrs.getLast()==null) return null;
		return (Martyr)martyrs.getLast().getKey();
	}
	public Martyr getYoungest() {
		if(martyrs.getFirst()==null) return null;
		return (Martyr)martyrs.getFirst().getKey();
	}
	public boolean equals(Object o) {
		if(o instanceof Date) return name.equals(((Date)o).getName());
		return false;
	}
	
	public String toString() {
		return name;
	}
}
