package hr.atos.praksa.DijanaIvezic.zadatak05;

import java.util.function.IntUnaryOperator;
import java.util.stream.IntStream;

public class Main {
	

	public static void main(String[] args) {
		try {
			int lowerBound = 4;
			int upperBound = 120;
			int counter = 0;
			
			if(lowerBound >= 10 || upperBound <= 100) {
				throw new Exception();
			}
			
			IntUnaryOperator operation = n -> {
				return (n<=18)?4:
					   (n%20==0 || n>=75)?0:-1;
				};
			
			//upper bound doesn't really make much of a difference since we stop at 75 either way
			//I assume the task meant a simple for loop, for-each java loop would have been sufficient
			upperBound = 75;
			IntStream range = IntStream.range(lowerBound, upperBound);
			range = range.map(operation);
			
			counter = range.sum();
			System.out.println("Brojač = " + counter);
			
		}catch(Exception e){
			System.out.println("Donja granica mora biti manja od 10, a gornja granica mora biti veća od 100.");
		}	
	}
}
