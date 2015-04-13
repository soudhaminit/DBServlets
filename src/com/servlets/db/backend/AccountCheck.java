package com.servlets.db.backend;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class AccountCheck {
	private Connection con;

	public Connection getCon() {
		return con;
	}

	public void setCon(Connection con) {
		this.con = con;
	}
	
	public AccountCheck(){
	}
	
	public AccountCheck(Connection con){
		this.con=con;
	}
	
	public boolean authenticate(String email,String password) throws SQLException{
		String sql="select count(*) as count from users1 where email=? and password=?";
		PreparedStatement pstmt=con.prepareStatement(sql);
		pstmt.setString(1, email);
		pstmt.setString(2, password);
		ResultSet rs=pstmt.executeQuery();
		int count=0;
		while(rs.next()){
			count=rs.getInt("count");
		}
		if(count>0) return true;
		else return false;
	}

	public boolean emailExists(String email) throws SQLException{
		String sql="select count(*) as count from users1 where email=? ";
		PreparedStatement pstmt=con.prepareStatement(sql);
		pstmt.setString(1, email);
		
		ResultSet rs=pstmt.executeQuery();
		int count=0;
		while(rs.next()){
			count=rs.getInt("count");
		}
		pstmt.close();
		if(count>0) return true;
		else return false;
	}

	public void createLogin(String email, String password) throws SQLException{
		String sql="insert into users1(email,password) values(?,?) ";
		PreparedStatement pstmt=con.prepareStatement(sql);
		pstmt.setString(1, email);
		pstmt.setString(2, password);
		
		pstmt.executeUpdate();
		pstmt.close();
		
	}

}
