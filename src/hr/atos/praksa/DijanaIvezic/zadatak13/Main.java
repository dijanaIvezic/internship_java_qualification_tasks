package hr.atos.praksa.DijanaIvezic.zadatak13;

public class Main {

	public static void main(String[] args) {
		Android android = new Android(12);
		android.ring();
		int version = android.getVersion();
		System.out.println(String.format("Android version = %d", version));

	}

}
