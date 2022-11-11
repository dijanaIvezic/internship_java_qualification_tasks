package hr.atos.praksa.DijanaIvezic.zadatak14;

import java.util.Arrays;
import java.util.List;
import java.util.Scanner;
import java.util.stream.Collectors;

public class Main {
	
	public static List<Float> getCoefficients(Scanner scan) throws Exception {
		String input;
		List<Float> coeffs;
		System.out.println("Please enter: A,B,T1,T2");
		input = scan.nextLine();
		
		try {
		coeffs = Arrays.stream(input.split(",")).
				map(s->Float.valueOf(s)).
				collect(Collectors.toList());
		if(coeffs.size() != 4) {
			throw new Exception();
		}			
		}catch(Exception e) {
			throw new Exception("Input must be four real values separated by commas.");
		}
		return coeffs;
	}
	
	public static String getFunction(Scanner scan) throws Exception {
		String input;
		System.out.println("Please input s (sin), c (cos), t (tan) or k (ctan):");
		input = scan.nextLine().toLowerCase();
		if(!Arrays.asList("s", "c", "t", "k").contains(input)) {
			throw new Exception("Please, input only s, c, t or k.");
		}
		return input;
	}
	
	public static int getSubintervals(Scanner scan) throws Exception{
		String input;
		System.out.println("Since we are approximating integration, we will split the interval between T1 and T2 to n subintervals.");
		System.out.println("Please enter n:");
		input = scan.nextLine();
		int n = 0;
		try {
			n = Integer.parseInt(input);
		}catch(Exception e) {
			throw new Exception("n must be integer!");
		}
		return n;
	}

	public static void main(String[] args) {
		try {
			float A,B,T1,T2;
			String function;
			List<Float> coeffs;
			int n = 0;
			
			try(Scanner scan = new Scanner(System.in)){
				coeffs = getCoefficients(scan);
				function = getFunction(scan);
				n = getSubintervals(scan);
			}
			A=coeffs.get(0); B=coeffs.get(1); T1=coeffs.get(2); T2=coeffs.get(3);
			
			TrigonometricFunction fun = null;
			
			switch(function) {
				case"s":
					fun = new Sin(A,B,T1,T2);
					break;
				case"c":
					fun = new Cos(A,B,T1,T2);
					break;
				case"t":
					fun = new Tan(A,B,T1,T2);
					break;
				case"k":
					fun = new Ctan(A,B,T1,T2);
					break;
			}
			
			float result = fun.integrate(n);
			System.out.println(String.format("Result = %f", result));
			
		}catch(Exception e) {
			System.out.println(e.getMessage());
		}

	}

}
