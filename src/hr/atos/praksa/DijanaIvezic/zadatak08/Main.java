package hr.atos.praksa.DijanaIvezic.zadatak08;

import java.time.YearMonth;
import java.util.Scanner;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class Main {

	public static void main(String[] args) {
		String monthInput;
		
		try (Scanner scan = new Scanner(System.in)) {
			System.out.println("Enter the month:");
			monthInput = scan.nextLine();
		}
		
		try {			
			int month = Integer.parseInt(monthInput);
			
			if(month < 1 || month > 12) {
				throw new Exception();
			}
			
			YearMonth yearMonth = YearMonth.of(2020, month); //2020 was a leap year
			int nDays = yearMonth.lengthOfMonth();
			
			IntStream range = IntStream.range(1, nDays+1);
			String callendar = range.mapToObj(n->(n%7==0)? String.format("%3d%n", n):
										  ((n%7==1)? String.format("%2d", n):
											         String.format("%3d", n))).collect(Collectors.joining(""));
			
			String header = " P  U  S  Č  P  S  N";
			System.out.println(header);
			System.out.println(callendar);
			
			
		}catch(Exception e) {
			System.out.println("ERROR. Make sure the input is a natural number between 1 and 12 inclusive!");
		}

	}

}
