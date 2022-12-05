package ca.sheridancollege.prajakun.beans;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class User {

	private Long userid;
	private String userName;
	private String encryptedPassword;
	
}
