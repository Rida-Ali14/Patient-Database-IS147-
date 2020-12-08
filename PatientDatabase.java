import java.util.*;
public class PatientDatabase implements Database {
	Patient[] patients;
	int size;
	int index;
	static final int DEFAULT = 1;

	static class Patient {
		String name;
		int age;
		String condition;
		Date admitted;
		public Patient(String name, int age, String condition, Date admitted) {
			this.name = name;
			this.age = age;
			this.condition = condition;
			this.admitted = admitted;
		}
		public String toString() {
			return this.name + " " + this.age + " " + 
					this.condition + " " + this.admitted;
		}
	}
	
	public PatientDatabase(int size) {
		this.size = size;
		this.patients = new Patient[size];
		this.index = 0;
	}
	public void add(Object o) {
		Patient pat = (Patient) o;
		if(index > size - 1) {
			Patient[] temp = new Patient[patients.length * 2];
			int i = 0;
			for(Patient p : patients) {
				temp[i] = p;
				i++;
			}
			this.patients = temp;
		}
		else {
			patients[index] = pat;
			index++;
		}
	}
	public Patient query(String name) {
		for(Patient p : patients) {
			if(p.name.equals(name)) return p;
		}
		System.out.println("Patient not found, returned null");
		return null;
	}
	public Patient query(int age) {
		for(Patient p : patients) {
			if(p.age == age) return p;
		}
		System.out.println("Patient not found, returned null");
		return null;
	}
	public Patient[] queryConditions(String condition) {
		Patient[] patientsWithCond = new Patient[this.size];
		int i = 0;
		for(Patient p : patients) {
			if(p != null && p.condition.equals(condition)) {
				patientsWithCond[i] = p;
				i++;
			}
		}
		return patientsWithCond;
	}
	public Patient getRandom() {
		return patients[(int) Math.floor(Math.random() * size)];
	}
	public Patient[] getAll() {
		return patients;
	}
	public static void addPrompt(PatientDatabase db) {
		Scanner sc = new Scanner(System.in);
		System.out.println("What is patient name?");
		String name = sc.next();
		System.out.println("What is patient age?");
		int age = sc.nextInt();
		System.out.println("Why is patient being admitted? (use hyphens for spaces)");
		String condition = sc.next();
		db.add(new Patient(name, age, condition, new Date()));
	}
	
	public static void print() {
		System.out.println("What operation would you like to perform?:"
				+ "\n0) quit"
				+ "\n1) add patient"
				+ "\n2) query by patient name"
				+ "\n3) query by patient age"
				+ "\n4) retrieve list of all patients with condition"
				+ "\n5) get random patient"
				+ "\n6) get all patients");
	}
	
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		System.out.println("How many patients would you like to keep?");
		int ans = sc.nextInt();
		PatientDatabase db = new PatientDatabase(ans);
		
		ans = DEFAULT;
		while(ans != 0) {
			print();
			ans = sc.nextInt();
			switch(ans) {
				case 0:
					break;
				case 1:
					addPrompt(db);
					break;
				case 2:
					System.out.println("What is the patient name?");
					String response = sc.next();
					System.out.println(db.query(response));
					break;
				case 3:
					System.out.println("What is the patient age?");
					int response2 = sc.nextInt();
					System.out.println(db.query(response2));
					break;
				case 4:
					System.out.println("What is the condition?");
					String response3 = sc.next();
					System.out.println(Arrays.toString(db.queryConditions(response3)));
					break;
				case 5:
					System.out.println(db.getRandom());
					break;
				case 6:
					System.out.println(Arrays.toString(db.getAll()));
			}
		}
	}

}
