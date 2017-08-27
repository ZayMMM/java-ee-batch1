package com.jdc.student;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class StudentRepo {
	
	private Connection getConnection() throws SQLException {
		return DriverManager.getConnection("jdbc:mysql://localhost:3306/StudentRDB", "root", "admin");
	}

	public void create(List<Student> students) {
		String sql = "insert into student (name, phone) values (?, ?)";
		try(Connection conn = getConnection();
				PreparedStatement stmt = conn.prepareStatement(sql)) {
			for (Student student : students) {
				stmt.setString(1, student.getName());
				stmt.setString(2, student.getPhone());
				stmt.addBatch();
			}
			
			stmt.executeBatch();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public List<Student> getAll() {
		List<Student> list = new ArrayList<>();
		String sql = "select * from student";
		try(Connection conn = getConnection();
				PreparedStatement stmt = conn.prepareStatement(sql)) {
			
			ResultSet rs = stmt.executeQuery();
			
			while(rs.next()) {
				Student s = new Student(
						rs.getInt("id"), 
						rs.getString("name"), 
						rs.getString("phone"));
				list.add(s);
			}
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return list;
	}
}
