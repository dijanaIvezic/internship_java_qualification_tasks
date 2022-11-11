package hr.atos.praksa.DijanaIvezic.zadatak14;

public class Sin extends TrigonometricFunction {
	public Sin(float A, float B, float T1, float T2) {
		setParams(A, B, T1, T2);
	}

	@Override
	protected float f(float x) {
		return (float)(A*Math.sin(x)+B);
	}

}
