package hibernate_user.dto;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.Data;

@Entity
@Table(name = "user_info")
@Data
public class User {
	
	@Id
	@Column(name = "user_id")
	private int id;
	private String name;
	@Column(unique = true)
	private long phone;
	@Column(unique = true)
	private String email;
	private String password;
	private String facebook;
	private String instagram;
	private String twitter;
	private String snapchat;
	

}
