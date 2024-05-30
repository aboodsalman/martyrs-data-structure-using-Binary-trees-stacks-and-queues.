package Phase2;

public class Martyr implements Comparable<Martyr>{
	private String name, gender;
	private int age;
	
	public Martyr(String name, String gender, int age) {
		super();
		this.name = name;
		this.gender = gender;
		this.age = age;
	}
	
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getGender() {
		return gender;
	}
	public void setGender(String gender) {
		this.gender = gender;
	}
	public int getAge() {
		return age;
	}
	public void setAge(int age) {
		this.age = age;
	}

	@Override
	public int compareTo(Martyr o) {
		return name.compareToIgnoreCase(o.getName());
	}
	
	public int compareByAge(Martyr o) {
		if(this.age!=o.getAge()) return this.age-o.getAge();
		return this.gender.compareToIgnoreCase(o.getGender());
	}
	public String getData() {
		return name+","+age+","+gender;
	}
	public boolean equals(Object o) {
		if(o instanceof Martyr) return name.equals(((Martyr)o).getName());
		return false;
	}
	public String toString() {
		return name;
	}
}
