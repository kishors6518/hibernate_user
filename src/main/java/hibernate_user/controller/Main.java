package hibernate_user.controller;

import java.util.Scanner;

import hibernate_user.dao.Userdao;
import hibernate_user.dto.User;

public class Main {

	public static void main(String[] args) {
		Scanner scanner=new Scanner(System.in);
		User user=new User();
		Userdao dao=new Userdao();
		
		System.out.println("Press option \n1.Singup \n2.Login");
		try {
			switch (scanner.nextInt()) {
			case 1:{
				System.out.println("Enter Id");
				user.setId(scanner.nextInt());
				System.out.println("Enter your name");
				user.setName(scanner.next());
				System.out.println("Enter your phone number");
				user.setPhone(scanner.nextLong());
				System.out.println("Enter your email");
				user.setEmail(scanner.next());
				System.out.println("Enter password");
				user.setPassword(scanner.next());
				int result=dao.saveUser(user);
				if(result!=0)
				{
					System.out.println("Signup successful");
				}
				else
				{
					System.out.println("Please retry");
				}
			}	break;

			case 2:{
				System.out.println("Enter email");
				String email=scanner.next();
				System.out.println("Enter password");
				String password=scanner.next();
				
				User dbUser=dao.getUser(email);
				if (dbUser!=null) {	
					if(password.equals(dbUser.getPassword()))
					{
						System.out.println("Login Successful");
						System.out.println("Enter the choice \n1.Display Passwords \n2.Update Password");
						switch (scanner.nextInt()) {
						case 1:{
							System.out.println("Password of Facebook is: "+dbUser.getFacebook());
							System.out.println("Password of Instagram is: "+dbUser.getInstagram());
							System.out.println("Password of Twitter is: "+dbUser.getTwitter());
							System.out.println("Password of Snapchat is: "+dbUser.getSnapchat());
							
						}break;
						case 2:{
							System.out.println("1.Facebook \n2.Instagram \n3.Twitter \n4.Snapchat");
							switch (scanner.nextInt()) {
							case 1:{
								System.out.println("Give the new Password");
								int result=dao.setFacebookPass(scanner.next(),email);
								if(result==1)
								{
									System.out.println("Facebook Password Update");
								}
								else
								{
									System.out.println("Please try again");
								}
							}break;

							default:
								break;
							}
							
						}
							break;
						}
					}
					else
					{
						System.out.println("Login Failed");
					}		
				}
				else
				{
					System.out.println("User not found");
				}
			}
				break;
			}
			
		} catch (Exception e) {
			// TODO: handle exception
			System.out.println("Please enter valid input");
		}

	}

}
