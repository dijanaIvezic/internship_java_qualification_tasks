package hr.atos.praksa.DijanaIvezic.zadatak15;

import java.sql.ResultSet;
import java.sql.Statement;
import java.util.Scanner;

public class User extends Employee {
	public User(String name, String lastname) {
		this.name = name;
		this.lastname = lastname;
	}

	@Override
	void createTask(Statement stmt, Scanner scan) throws Exception {
		throw new Exception("ERROR: authority error.");
		
	}

	@Override
	void createEmployee(Statement stmt, Scanner scan) throws Exception{
		throw new Exception("ERROR: authority error.");
		
	}

	@Override
	void listTasks(Statement stmt) throws Exception {
		String sql = "SELECT * from Tasks";
		String toPrint;
		ResultSet result = stmt.executeQuery(sql);
		System.out.println("\nTasks list:");
		System.out.println("-".repeat(100));
		while(result.next()) {
			toPrint = "id = %d,\r\n"
	        		+ "name = %s,\r\n"
	        		+ "description = %s,\r\n"
	        		+ "type = %s,\r\n"
	        		+ "status = %s,\r\n"
	        		+ "complexity = %d,\r\n"
	        		+ "time_spent = %d,\r\n"
	        		+ "date_start = %s,\r\n"
	        		+ "date_end = %s";
			
			System.out.println(String.format(toPrint, 
					result.getInt("id"),
					result.getString("name"),
					result.getString("description"),
					result.getString("type"),
					result.getString("status"),
					result.getInt("complexity"),
					result.getInt("time_spent"),
					result.getString("date_start"),
					result.getString("date_end")));
			
			System.out.println("-".repeat(100));
		}
	}

	@Override
	void listEmployees(Statement stmt) throws Exception{
		String sql = "SELECT * from Employees";
		String toPrint;
		ResultSet result = stmt.executeQuery(sql);
		System.out.println("\nEmployees list:");
		System.out.println("-".repeat(100));
		while(result.next()) {
			toPrint = "name = %s,\r\n"
	        		+ "last_name = %s,\r\n"
	        		+ "work_place = %s";
			System.out.println(String.format(toPrint,
					result.getString("name"),
					result.getString("last_name"),
					result.getString("work_place")));
			
			System.out.println("-".repeat(100));
		}
		
	}

	@Override
	void modifyEmployee(Statement stmt, Scanner scan) throws Exception{
		throw new Exception("ERROR: authority error.");
		
	}

	@Override
	void modifyTask(Statement stmt, Scanner scan) throws Exception{
		throw new Exception("ERROR: authority error.");
		
	}

	@Override
	void createReport(Statement stmt, Scanner scan) throws Exception{
		throw new Exception("ERROR: authority error.");
		
	}

	@Override
	void deleteEmployee(Statement stmt, Scanner scan) throws Exception {
		throw new Exception("ERROR: authority error.");
		
	}

	@Override
	void deleteTask(Statement stmt, Scanner scan) throws Exception {
		throw new Exception("ERROR: authority error.");
		
	}

}
