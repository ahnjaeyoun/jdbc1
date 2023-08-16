package edu.kh.jdbc1;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import edu.kh.jdbc1.model.vo.Employee;

public class JDBCExample4 {
	
	public static void main(String[] args) {
		
		Scanner sc = new Scanner(System.in);
		
		Connection conn = null;
		Statement stmt = null;
		ResultSet rs = null;
		
		try {
			System.out.print("직급명 입력 : ");
			String input1 = sc.next();
			
			System.out.print("급여 입력 : ");
			int input2 = sc.nextInt();
			
			Class.forName("orcle.jdbc.driver.OracleDriver");
			
			String type = "jdbc:oracle:thin:@";
			String ip = "localhost";
			String port = ":1521";
			String sid = ":EX";
			String user = "kh";
			String pw = "kh1234";
			
			conn = DriverManager.getConnection(type + ip + port + sid, user, pw);
			
			String sql = "SELECT EMP_NAME, JOB_NAME, SALARY, (12 * SALARY) AS annualIncome"
					+ " FROM EMPLOYEE"
					+ " JOIN JOB USING(JOB_CODE)"
					+ " WHERE JOB_NAME = 'input1' AND SALARY > 'input2'";
			
			stmt = conn.createStatement();
			rs = stmt.executeQuery(sql);
			
			List<Employee> list = new ArrayList<Employee>();
			
			while(rs.next()) {
				String empName = rs.getString("EMP_NAME");
				String jobName = rs.getString("JOB_NAME");
				int salary = rs.getInt("SALARY");
				int annualIncome = rs.getInt("annualIncome");
				
				//list.add(new Employee(empName, jobName, salary, annualIncome));
			}
			
			if(list.isEmpty()) {
				System.out.println("조회 결과 없음.");
			}else {
				for(Employee emp : list) {
					System.out.println(emp);
				}
			}
			
			
			
			
		}catch(Exception e) {
			e.printStackTrace();
		}finally {
			
		}
	}

}
