package hr.atos.praksa.DijanaIvezic.zadatak14;

public class Ctan extends TrigonometricFunction {
	public Ctan(float A, float B, float T1, float T2) {
		setParams(A, B, T1, T2);
	}

	@Override
	protected float f(float x) {
		return (float)(A*(1/Math.tan(x))+B);
	}

}
