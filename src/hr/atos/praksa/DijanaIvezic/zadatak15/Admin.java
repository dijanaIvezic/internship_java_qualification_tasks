package hr.atos.praksa.DijanaIvezic.zadatak15;

import java.sql.Statement;
import java.util.Scanner;


public class Admin extends Superuser {
	public Admin(String name, String lastname) {
		super(name, lastname);
		this.name = name;
		this.lastname = lastname;
	}
	
	@Override
	void modifyEmployee(Statement stmt, Scanner scan) throws Exception{
		Boolean unique = false; //we need the employee to exist
		EmployeeInput employeeInput = getEmployeeInput(stmt, scan, unique);
		int oib = employeeInput.oib;
		String name = employeeInput.name;
		String lastname = employeeInput.lastname;
		String workplace = employeeInput.workplace;
		
		String sql = String.format("UPDATE Employees\r\n"
				+ "SET name=\"%s\",last_name=\"%s\",work_place=\"%s\"\r\n"
				+ "where oib=%d",
				name, lastname, workplace, oib);
		
		stmt.execute(sql);
		System.out.println("Employee modified!");
		
	}

	@Override
	void modifyTask(Statement stmt, Scanner scan) throws Exception{
		Boolean unique = false; //we need task ID to exist
		TaskInput taskInput = getTaskInput(stmt, scan, unique);
		
		int id = taskInput.id;
		String name = taskInput.name;
		String desc = taskInput.description;
		String type = taskInput.type;
		String status = taskInput.status;
		int complexity = taskInput.complexity;
		int timeSpent = taskInput.timeSpent;
		String startDate = taskInput.dateStart;
		String endDate = taskInput.dateEnd;
		
		
		String sql = String.format("UPDATE Tasks\r\n"
				+ "SET name=\"%s\", description=\"%s\", type=\"%s\", status=\"%s\", complexity=%d,\r\n"
				+ "time_spent=%d, date_start=\"%s\", date_end=\"%s\"\r\n"
				+ "WHERE id=%d;",
				name, desc, type, status, complexity, timeSpent, startDate, endDate, id);
		
		stmt.execute(sql);
		System.out.println("Task updated!");
	}
	
	@Override
	void deleteEmployee(Statement stmt, Scanner scan) throws Exception {
		Boolean unique = false; //we need the employee to exist
		
		int oib = getOib(stmt, scan, unique);
		
		String sql = String.format("DELETE FROM Employees\r\n"
				+ "where oib=%d", oib);
		
		stmt.execute(sql);
		System.out.println("Employee deleted!");
		
	}

	@Override
	void deleteTask(Statement stmt, Scanner scan) throws Exception {
		Boolean unique = false; //we need task ID to exist
		
		int id = getId(stmt, scan, unique);
		
		String sql = String.format("DELETE FROM Tasks\r\n"
				+ "WHERE id=%d;", id);
		
		stmt.execute(sql);
		System.out.println("Task deleted!");
		
	}

}
