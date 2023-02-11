package com.jspcrud.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.jspcrud.model.User;

public class UserDao {//url="jdbc:mysql://localhost:3306/dbname?characterEncoding=utf8"
//burl = "jdbc:mysql://localhost:3306/testdb"
	private String dburl="jdbc:mysql://localhost:3306/testdb?characterEncoding=utf8";
	private String dbUname = "root";
	private String dbPassword = "123456789";
	private String dbDriver = "com.mysql.jdbc.Driver";

	public void loadDriver(String dbDriver) {
		try {
			Class.forName(dbDriver);
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
	}

	public Connection getCon() {
		Connection con = null;
		try {
			loadDriver(dbDriver);
			con = DriverManager.getConnection(dburl, dbUname, dbPassword);
		} catch (SQLException e) {
			System.out.println(e);;
		}
		return con;
	}

	public int save(User u) {
		int status = 0;
		try {
			Connection con = getCon();
			PreparedStatement ps = con
					.prepareStatement("insert into userdetails(username,pass,email,sex,country) values(?,?,?,?,?)");
			ps.setString(1, u.getName());
			ps.setString(2, u.getPassword());
			ps.setString(3, u.getEmail());
			ps.setString(4, u.getSex());
			ps.setString(5, u.getCountry());
			status = ps.executeUpdate();

		} catch (Exception e) {
			e.printStackTrace();
		}
		return status;
	}

	public int update(User u) {
		int status = 0;
		try {
			Connection con = getCon();
			PreparedStatement ps = con.prepareStatement(
					"update userdetails set username=?,pass=?," + "email=?,sex=?,country=? where id=?");
			ps.setString(1, u.getName());
			ps.setString(2, u.getPassword());
			ps.setString(3, u.getEmail());
			ps.setString(4, u.getSex());
			ps.setString(5, u.getCountry());
			ps.setInt(6, u.getId());
			status = ps.executeUpdate();

		} catch (Exception e) {
			e.printStackTrace();
		}
		return status;
	}

	public int delete(User u) {
		int status = 0;
		try {
			Connection con = getCon();
			PreparedStatement ps = con.prepareStatement("delete from userdetails where id=?");
			ps.setInt(1, u.getId());
			status = ps.executeUpdate();
		} catch (Exception e) {
			System.out.println(e);
		}

		return status;
	}

	public List<User> getAllRecords() {
		List<User> list = new ArrayList<User>();

		try {
			Connection con = getCon();
			PreparedStatement ps = con.prepareStatement("select * from userdetails");
			ResultSet rs = ps.executeQuery();
			while (rs.next()) {
				User u = new User();
				u.setId(rs.getInt("id"));
				u.setName(rs.getString("username"));
				u.setPassword(rs.getString("pass"));
				u.setEmail(rs.getString("email"));
				u.setSex(rs.getString("sex"));
				u.setCountry(rs.getString("country"));
				list.add(u);
			}
		} catch (Exception e) {
			System.out.println(e);
		}
		return list;
	}

	public User getRecordById(int id) {
		User u = null;
		try {
			Connection con = getCon();
			PreparedStatement ps = con.prepareStatement("select * from userdetails where id=?");
			ps.setInt(1, id);
			ResultSet rs = ps.executeQuery();
			while (rs.next()) {
				u = new User();
				u.setId(rs.getInt("id"));
				u.setName(rs.getString("username"));
				u.setPassword(rs.getString("pass"));
				u.setEmail(rs.getString("email"));
				u.setSex(rs.getString("sex"));
				u.setCountry(rs.getString("country"));
			}
		} catch (Exception e) {
			System.out.println(e);
		}
		return u;
	}
}
