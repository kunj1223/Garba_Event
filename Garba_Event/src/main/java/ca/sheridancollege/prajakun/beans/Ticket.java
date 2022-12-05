package ca.sheridancollege.prajakun.beans;


import org.springframework.beans.factory.annotation.Autowired;


import ca.sheridancollege.prajakun.repositories.UserRepository;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class Ticket {
	


	private int id;
	private String name;
	private String dayOfWeek;
	private String[] days= {"Saturday", "Sunday", "Monday"};
	private double price;
	private String email;
    private double nOfPeople;
	
}
