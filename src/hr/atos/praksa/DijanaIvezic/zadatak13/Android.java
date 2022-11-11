package hr.atos.praksa.DijanaIvezic.zadatak13;

public class Android implements Phone {
	private int androidVersion;
	
	public Android(int androidVersion) {
		this.androidVersion = androidVersion;
	}

	@Override
	public void ring() {
		System.out.println("RIIIIIIIING");

	}

	@Override
	public int getVersion() {
		return androidVersion;
	}

}
