package com.servlets.db.beans;

public class User {
private String email;
private String password;
private String message="";

public String getMessage() {
	return message;
}
public String getPassword() {
	return password;
}
public void setPassword(String password) {
	this.password = password;
}

public User(){}

public User(String email,String password){
	this.email=email;
	this.password=password;
}

public boolean  validate(){
	if(!email.matches("\\w+@\\w+\\.\\w+")){
		message="Invalid email id";
		return false;
	}
	if(password.length()<8){
		message="Password cannot be less than 8 characters";
		return false;
	}
	else if(password.matches("\\w*\\s+\\w*")){
		message="Password cannot conatian spaces";
		return false;
	}
	return true;
}

}
