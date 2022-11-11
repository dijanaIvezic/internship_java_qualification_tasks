package hr.atos.praksa.DijanaIvezic.zadatak07;

import java.util.Scanner;

public class Main {

	public static void main(String[] args) {
		String firstInput;
		String secondInput;
		try (Scanner scan = new Scanner(System.in)) {
			System.out.println("Enter the first number:");
			firstInput = scan.nextLine();
			System.out.println("Enter the second number:");
			secondInput = scan.nextLine();
		}
		
		try {
			int firstNumber = Integer.parseInt(firstInput);
			int secondNumber = Integer.parseInt(secondInput);
			
			if(firstNumber <= 0 || secondNumber <= 0) {
				throw new Exception();
			}
			
			int temp = firstNumber;
			
			if(firstNumber < secondNumber) {
				firstNumber = secondNumber;
				secondNumber = temp;
			}
			
			int n = firstNumber/6 - (secondNumber-1)/6;
			
			System.out.println(String.format("There are %d numbers divisible by 6 between %d and %d.", n, secondNumber, firstNumber));
			
		}catch(Exception e) {
			System.out.println("ERROR. Make sure the inputs are natural numbers!");
		}

	}

}
