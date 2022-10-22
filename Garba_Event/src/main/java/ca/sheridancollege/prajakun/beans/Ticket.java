package ca.sheridancollege.prajakun.beans;

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
