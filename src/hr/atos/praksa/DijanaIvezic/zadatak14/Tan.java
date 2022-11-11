package hr.atos.praksa.DijanaIvezic.zadatak14;

public class Tan extends TrigonometricFunction {
	public Tan(float A, float B, float T1, float T2) {
		setParams(A, B, T1, T2);
	}

	@Override
	protected float f(float x) {
		return (float)(A*Math.tan(x)+B);
	}

}
