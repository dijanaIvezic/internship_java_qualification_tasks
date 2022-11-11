package hr.atos.praksa.DijanaIvezic.zadatak14;

abstract class TrigonometricFunction {
	protected float A,B,T1,T2;
	
	abstract protected float f(float x);
	
	public float integrate(int n) {
		float sum = 0;
		for(int k = 1; k<n; k++) {
			sum += f(T1+k*((T2-T1)/(n)));
		}
		float result = ((T2-T1)/(n)) * (f(T1)/2 + sum + f(T2)/2);
		return result;
	}
	
	protected void setParams(float A, float B, float T1, float T2) {
		this.A = A;
		this.B = B;
		if(T1>T2) {
			this.T2 = T1;
			this.T1 = T2;
		}else {
			this.T1 = T1;
			this.T2 = T2;
		}
	}

}
