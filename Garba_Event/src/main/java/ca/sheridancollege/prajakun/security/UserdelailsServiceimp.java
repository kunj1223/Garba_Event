package ca.sheridancollege.prajakun.security;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import ca.sheridancollege.prajakun.repositories.UserRepository;

@Service
public class UserdelailsServiceimp implements UserDetailsService {

	@Autowired
	private UserRepository secRepo;
	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		// TODO Auto-generated method stub
	ca.sheridancollege.prajakun.beans.User user = secRepo.findUserAccount(username);
	
	
		if(user==null) {
			System.out.println("Username Not Found");
			throw new UsernameNotFoundException("User not Found");
		}
		
		List<String> roles = secRepo.getRolesById(user.getUserid());
		
		List<GrantedAuthority> grantList = new ArrayList<GrantedAuthority>();
		
		for (String role : roles) {
			grantList.add(new  SimpleGrantedAuthority(role));
		}
		
		User springUser = new User(user.getUserName(), user.getEncryptedPassword(),grantList);
		
		return (UserDetails)springUser;
	}

}
