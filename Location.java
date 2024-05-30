package Phase2;

public class Location implements Comparable<Location>{
	String name;
	BinarySearchTree dates;
	
	
	public Location(String name) {
		super();
		this.name = name;
		dates = new BinarySearchTree();
	}

	@Override
	public int compareTo(Location o) {
		return this.name.compareToIgnoreCase(o.getName());
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public BinarySearchTree getDates() {
		return dates;
	}

	public void setDates(BinarySearchTree Dates) {
		this.dates = Dates;
	}
	public boolean equals(Object o) {
		if(o instanceof Location) return name.equals(((Location)o).getName());
		return false;
	}
	public String toString() {
		return name;
	}
}
