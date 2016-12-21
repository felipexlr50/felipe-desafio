package controller;

import java.io.UnsupportedEncodingException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.sql.SQLException;
import java.util.ArrayList;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;

import dao.UserDAO;
import model.User;

@ManagedBean
@ViewScoped
public class SurveyController {
	
	private User user = new User();

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}
	
	public void saveUserAwnser(String id){
		UserDAO dao;
				
		try{
			dao = new UserDAO();
			testEmail(dao.selectUserEmail(),id);
			System.out.println(user.getQuestion1()+user.getQuestion2()+user.getQuestion3());
			dao.updateUser(user);
			
		} catch (ClassNotFoundException | InstantiationException | SQLException
				| IllegalAccessException e) {
			
			e.printStackTrace();
		}
	}
	
	private String toMD5(String text){
		MessageDigest algorithm;
		try {
			algorithm = MessageDigest.getInstance("MD5");
			byte messageDigest[] = algorithm.digest("senha".getBytes("UTF-8"));
			StringBuilder hexString = new StringBuilder();
			for (byte b : messageDigest) {
			  hexString.append(String.format("%02X", 0xFF & b));
			}
			return hexString.toString();
		} catch (NoSuchAlgorithmException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (UnsupportedEncodingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}
	
	private void testEmail(ArrayList<String> emails, String id){
		for(String s:emails){
			if(id.equals(toMD5(s))){
				user.setEmail(s);
				break;
			}
		}
	}

}
