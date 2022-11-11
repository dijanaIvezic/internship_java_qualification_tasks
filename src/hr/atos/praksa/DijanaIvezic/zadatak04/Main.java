package hr.atos.praksa.DijanaIvezic.zadatak04;

import java.util.Arrays;
import java.util.List;
import java.util.function.Consumer;

public class Main {
	
	public static void main(String[] args) {
		
		Consumer<Integer> checkNumber = n->{
			String oddEven = (n % 2 == 0)?  " je paran broj.": " je neparan broj.";
			System.out.println(n.toString() + oddEven);
			
			int divisor = (n%3==0)? 3:
						 ((n%5==0)? 5:
						 ((n%11==0)?11:
							        0));
			
			if(divisor > 0) {
			System.out.println(n.toString() + " je višekratnik od " + Integer.toString(divisor) + ".");
			}
			
			System.out.println();
		};
		
		List<Integer> numbers = Arrays.asList(5,10,11,45,99);
		numbers.stream().forEach(checkNumber);

	}

}
