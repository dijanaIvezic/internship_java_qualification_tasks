package hr.atos.praksa.DijanaIvezic.zadatak15;

import java.sql.ResultSet;
import java.sql.Statement;
import java.util.Scanner;

public abstract class Employee {
	String name, lastname;
	
	public static Employee make(Statement stmt, int OIB) throws Exception {
		String sql = String.format("SELECT * FROM Employees WHERE OIB=%d;", OIB);
    	ResultSet result = stmt.executeQuery(sql);
    	
    	if(!result.isBeforeFirst()) {
    		throw new Exception("Your OIB is not in the database!");
    	}
    	
    	result.next();
    	String firstName = result.getString("name");
    	String lastName = result.getString("last_name");
    	String workplace = result.getString("work_place");
    	
		switch(workplace) {
		case"admin":
			return new Admin(firstName, lastName);
		case"superuser":
			return new Superuser(firstName, lastName);
		}
		return new User(firstName, lastName);
	}
	
	protected void greet() {
		System.out.println(String.format("Hello %s %s!", name, lastname));
	}
	
	protected void makeChoice(Statement stmt, Scanner scan) throws Exception {
		System.out.println("What do you want to do?");
    	System.out.println("1) List employees\n"+
    					"2) List tasks\n"+
    					"3) Create an employee\n"+
    					"4) Create a task\n"+
    					"5) Modify an employee\n"+
    					"6) Modify a task\n"+
    					"7) Delete an employee\n"+
    					"8) Delete a task\n"+
    					"9) Create a report");
    	String input = scan.nextLine();
    	int choice;
    	try {
    		choice = Integer.parseInt(input);
    		if(choice < 1 || choice > 9) {
    			throw new Exception();
    		}
    		
    	}catch(Exception e) {
    		throw new Exception("Please pick a number between 1 and 9.");
    	}
    	
		switch(choice) {
    	case 1:
    		listEmployees(stmt);
    		break;
    	case 2:
    		listTasks(stmt);
    		break;
    	case 3:
    		createEmployee(stmt, scan);
    		break;
    	case 4:
    		createTask(stmt, scan);
    		break;
    	case 5:
    		modifyEmployee(stmt, scan);
    		break;
    	case 6:
    		modifyTask(stmt, scan);
    		break;
    	case 7:
    		deleteEmployee(stmt, scan);
    		break;
    	case 8:
    		deleteTask(stmt, scan);
    		break;
    	case 9:
    		createReport(stmt, scan);
    		break;
    	}
	}
	
	abstract void createTask(Statement stmt, Scanner scan) throws Exception;
	abstract void createEmployee(Statement stmt, Scanner scan) throws Exception;
	abstract void listTasks(Statement stmt) throws Exception;
	abstract void listEmployees(Statement stmt) throws Exception;
	abstract void modifyEmployee(Statement stmt, Scanner scan) throws Exception;
	abstract void modifyTask(Statement stmt, Scanner scan) throws Exception;
	abstract void deleteEmployee(Statement stmt, Scanner scan) throws Exception;
	abstract void deleteTask(Statement stmt, Scanner scan) throws Exception;
	abstract void createReport(Statement stmt, Scanner scan) throws Exception;
}
