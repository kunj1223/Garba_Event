package ca.sheridancollege.prajakun.repositories;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Repository;


import ca.sheridancollege.prajakun.beans.User;
import lombok.AllArgsConstructor;

@Repository
@AllArgsConstructor

public class UserRepository {
	
	
	@Autowired
	private NamedParameterJdbcTemplate jdbc;
	
	@Bean
	public BCryptPasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder();
	}
	
	public User findUserAccount(String userName) {
		
		String query = "SELECT * FROM SEC_USER WHERE USERNAME=:name";
		
		MapSqlParameterSource parameter = new MapSqlParameterSource();
		parameter.addValue("name", userName);
		ArrayList<User> users = (ArrayList<User>) jdbc.query(query,parameter, new BeanPropertyRowMapper<User>(User.class));
		
		return (users.size()>0)?users.get(0):null;
	}
	
	public String[]  getUserName() {
		MapSqlParameterSource parameter = new MapSqlParameterSource();
		String query = "SELECT USERNAME FROM SEC_USER WHERE NOT USERNAME = 'Jon' ORDER BY USERNAME;";
				ArrayList<User> UserList = (ArrayList<User>) jdbc.query(query, parameter, new BeanPropertyRowMapper<User>(User.class));
				int size = UserList.size();
				String[] Users = new String[size];
				for(int i =0; i < size; i++)
				{
					Users[i] = UserList.get(i).getUserName();
				}
	           
	            return Users;
	}
	
	
	public List<String> getRolesById(long userId){
		ArrayList<String> roles  = new ArrayList<String>();
		MapSqlParameterSource parameter = new MapSqlParameterSource();
		
		String query = "SELECT USER_ROLE.USERID, SEC_ROLE.ROLENAME FROM USER_ROLE , SEC_ROLE \r\n"
				+ "WHERE USER_ROLE.ROLEID = SEC_ROLE.ROLEID \r\n"
				+ "AND USERID=:meow;";
		parameter.addValue("meow", userId);
		List<Map<String,Object>> rows = jdbc.queryForList(query, parameter);
		
		for(Map<String,Object> row : rows ) {
		roles.add((String)row.get("roleName"));	
		}
		return roles;
	}
	
	public void addUser(String userName, String password) {
		MapSqlParameterSource parameters = new MapSqlParameterSource();
		String query = "insert into SEC_User "
		+ "(userName, encryptedPassword, ENABLED)"
		+ " values (:userName, :encryptedPassword, 1)";
		parameters.addValue("userName", userName);
		parameters.addValue("encryptedPassword",passwordEncoder().encode(password));
		jdbc.update(query, parameters);
		}
	
	public void addRole(long userId, long roleId) {
		MapSqlParameterSource parameters = new MapSqlParameterSource();
		String query = "insert into user_role (userId, roleId)"
		+ "values (:userId, :roleId);";
		parameters.addValue("userId", userId);
		parameters.addValue("roleId", roleId);
		jdbc.update(query, parameters);
		}
	
}
