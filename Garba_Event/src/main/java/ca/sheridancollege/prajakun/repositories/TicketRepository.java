package ca.sheridancollege.prajakun.repositories;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;


import ca.sheridancollege.prajakun.beans.Ticket;
import lombok.AllArgsConstructor;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

@AllArgsConstructor
@Repository

public class TicketRepository {

	
	@Autowired
	private NamedParameterJdbcTemplate jdbc;
	
	public void addTicket(Ticket ticket) {
		MapSqlParameterSource parameter = new MapSqlParameterSource();
		
		String query = "INSERT INTO tickets (name , dayOfWeek , price , email , nOfPeople) VALUES " + "(:name, :d ,:p , :e, :n)";
		
		parameter.addValue("name", ticket.getName());
		parameter.addValue("d", ticket.getDayOfWeek());
		parameter.addValue("p", ticket.getPrice());
		parameter.addValue("e", ticket.getEmail());
		parameter.addValue("n", ticket.getNOfPeople());

		
		jdbc.update(query,parameter);
	
		
	}
	
public ArrayList<Ticket> getTickets(){
		
		ArrayList<Ticket> tickets  = new ArrayList<Ticket>();
		MapSqlParameterSource parameter = new MapSqlParameterSource();
		
		String query = "SELECT * FROM TICKETS";
		List<Map<String,Object>> rows = jdbc.queryForList(query, parameter);
		
		for(Map<String,Object> row : rows ) {
			Ticket t =new Ticket();
			t.setId((int)row.get("id"));
			t.setName((String)row.get("name"));
			t.setDayOfWeek((String)row.get("dayOfWeek"));
			t.setPrice((double)row.get("price"));
			t.setEmail((String)row.get("email"));
			t.setNOfPeople((double)row.get("nOfPeople"));

			tickets.add(t);
		}
		return tickets;
    }

public Ticket getTicketById(int id){
	
	ArrayList<Ticket> tickets  = new ArrayList<Ticket>();
	MapSqlParameterSource parameter = new MapSqlParameterSource();
	
	String query = "SELECT * FROM TICKETS WHERE id = :t";
	parameter.addValue("t", id);
	List<Map<String,Object>> rows = jdbc.queryForList(query, parameter);
	
	for(Map<String,Object> row : rows ) {
		Ticket t =new Ticket();
		t.setId((int)row.get("id"));
		t.setName((String)row.get("name"));
		t.setDayOfWeek((String)row.get("dayOfWeek"));
		t.setPrice((double)row.get("price"));
		t.setEmail((String)row.get("email"));
		t.setNOfPeople((double)row.get("nOfPeople"));
		
		tickets.add(t);
	}
	
	if (tickets.size()>0) {
		return tickets.get(0);
	} else {
		return null;
	}
	
   }
public void editTicket(Ticket ticket) {
	MapSqlParameterSource parameter = new MapSqlParameterSource();
	
	String query = "UPDATE TICKETS SET name=:name , dayOfWeek=:d ,price=:p ,email=:e, nOfPeople=:n WHERE ID=:id ";
	
	parameter.addValue("id", ticket.getId());
	parameter.addValue("name", ticket.getName());
	parameter.addValue("d", ticket.getDayOfWeek());
	parameter.addValue("p", ticket.getPrice());
	parameter.addValue("e", ticket.getEmail());
	parameter.addValue("n", ticket.getNOfPeople());

	jdbc.update(query,parameter);

	
  }
public void deleteTicketById(int id) {
	
    MapSqlParameterSource parameter = new MapSqlParameterSource();
	
	String query = "DELETE FROM TICKETS WHERE ID=:id";
	parameter.addValue("id", id);
	
	jdbc.update(query,parameter);
}

public double maxTicketPrice() {
	
	ArrayList<Double> d  = new ArrayList<Double>();
	MapSqlParameterSource parameter = new MapSqlParameterSource();
	

	String query = "SELECT MAX(Price) FROM TICKETS";
	List<Map<String, Object>> rows = jdbc.queryForList(query, parameter);
	
	for(Map<String,Object> row : rows ) {
		
		d.add((Double) (row.get("MAX(Price)")));

    }
	if (d.size()>0) {
		return d.get(0);
	} else {
		return 0.0;
	}
}

public double minTicketPrice() {
	
	ArrayList<Double> d  = new ArrayList<Double>();
	MapSqlParameterSource parameter = new MapSqlParameterSource();
	

	String query = "SELECT MIN(PRICE) FROM TICKETS";
	List<Map<String, Object>> rows = jdbc.queryForList(query, parameter);
	
	for(Map<String,Object> row : rows ) {
		
		d.add((double)(row.get("MIN(PRICE)")));
    }
	if (d.size()>0) {
		return d.get(0);
	} else {
		return 0.0;
	}
  }

public double maxTicketOnSunday() {
	
	ArrayList<Double> d  = new ArrayList<Double>();
	MapSqlParameterSource parameter = new MapSqlParameterSource();

	String query = "SELECT MAX(Price) FROM TICKETS WHERE DAYOFWEEK='Sunday'";
	List<Map<String, Object>> rows = jdbc.queryForList(query, parameter);
	
	for(Map<String,Object> row : rows ) {
		
		d.add((double)(row.get("MAX(Price)")));	
   }
	if (d.size()>0) {
		
		return d.get(0);
	} else {
		
		return 0;
	}
  }

public double maxTicketOnMonday() {
	
	ArrayList<Double> d  = new ArrayList<Double>();
	MapSqlParameterSource parameter = new MapSqlParameterSource();

	String query = "SELECT MAX(Price) FROM TICKETS WHERE DAYOFWEEK='Monday'";
	List<Map<String, Object>> rows = jdbc.queryForList(query, parameter);
	
	for(Map<String,Object> row : rows ) {
		
		d.add((double)(row.get("MAX(Price)")));
    }
	if (d.size()>0) {
		
		return d.get(0);
	} else {
		return 0;
	}
  }

public double minTicketPriceSunday() {
	
	ArrayList<Double> d  = new ArrayList<Double>();
	MapSqlParameterSource parameter = new MapSqlParameterSource();
	

	String query = "SELECT MIN(PRICE) FROM TICKETS WHERE DAYOFWEEK='Sunday'";
	List<Map<String, Object>> rows = jdbc.queryForList(query, parameter);
	
	for(Map<String,Object> row : rows ) {
		
		d.add((double)(row.get("MIN(PRICE)")));
    }
	if (d.size()>0) {
		return d.get(0);
	} else {
		return 0.0;
	}
  }

}


