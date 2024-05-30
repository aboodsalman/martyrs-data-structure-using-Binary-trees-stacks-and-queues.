package Phase2;

public class District implements Comparable<District>{
	private String name;
	private BinarySearchTree locations;
	
	
	public District(String name) {
		super();
		this.name = name;
		locations = new BinarySearchTree();
	}

	@Override
	public int compareTo(District o) {
		return this.name.compareToIgnoreCase(o.getName());
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public BinarySearchTree getLocations() {
		return locations;
	}

	public void setLocations(BinarySearchTree locations) {
		this.locations = locations;
	}
	public boolean equals(Object o) {
		if(o instanceof District) return name.equals(((District)o).getName());
		return false;
	}
	public String toString() {
		return name;
	}
	
}
