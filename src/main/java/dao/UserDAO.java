package dao;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import model.User;

public class UserDAO {
	
	private Connection connection;

	public UserDAO() throws ClassNotFoundException, InstantiationException, IllegalAccessException, SQLException {
		super();
		this.connection = new DataBaseConnection().getConnection();
	}
	
	public void addUser(User user){
		
		String sql = "insert into user " +
				"(email)" +
				" values (?)";
		
		try{
			PreparedStatement stmt = connection.prepareStatement(sql);
			
			stmt.setString(1, user.getEmail());
			
			stmt.execute();
			stmt.close();
			
		} catch(SQLException e) {
			throw new RuntimeException(e);
		}
	}
	
	public void updateUser(User user){
		String sql = "update user set question1 = ?, question2 = ?, question3 = ? where email = ?";
		
		try{
			PreparedStatement stmt = connection.prepareStatement(sql);
			
			stmt.setString(1, user.getQuestion1());
			stmt.setString(2, user.getQuestion2());
			stmt.setString(3, user.getQuestion3());
			stmt.setString(4, user.getEmail());
			
			stmt.execute();
			stmt.close();
			
		} catch(SQLException e) {
			throw new RuntimeException(e);
		} 
	}
	
	public ArrayList<String> selectUserEmail(){
		PreparedStatement stmt;
		try {
			stmt = this.connection.prepareStatement("select email from user");
			ResultSet rs = stmt.executeQuery();
			ArrayList<String> emails = new ArrayList<>();
			while (rs.next()) {
				emails.add(rs.getString("email"));
			}
			rs.close();
			stmt.close();
			return emails;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}
	
	 
	

}
