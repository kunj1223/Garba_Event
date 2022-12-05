package ca.sheridancollege.prajakun.controllers;

import java.security.Principal;
import java.text.DecimalFormat;
import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import ca.sheridancollege.prajakun.beans.Ticket;
import ca.sheridancollege.prajakun.beans.User;
import ca.sheridancollege.prajakun.repositories.TicketRepository;
import ca.sheridancollege.prajakun.repositories.UserRepository;
import lombok.AllArgsConstructor;


@AllArgsConstructor
@Controller
public class TicketController {

	private TicketRepository ticketrepo;
	
	@Autowired
	private UserRepository da;
	
	@GetMapping("/") 
    public String home() {
	return "home.html";
     }
	
	@GetMapping("/add") 
    public String home(Model model) {
    model.addAttribute("tickets", new Ticket());
    String[] UserList = da.getUserName();
	model.addAttribute("user", UserList);
	return "add.html";
     }
	
	@GetMapping("/process")
	 public String Process(@ModelAttribute Ticket tickets) {
	 ticketrepo.addTicket(tickets);
	 return "redirect:/add";
    }
	
	@GetMapping("/view")
	public String goView(Model model, Authentication authentication)
	{
		ArrayList<String> roles = new ArrayList<String>();
		for (GrantedAuthority ga: authentication.getAuthorities()) {
		roles.add(ga.getAuthority());
		}
		
		model.addAttribute("roleList", roles);

		if(roles.get(0).equals("ROLE_VENDOR"))
		{
			
			
			model.addAttribute("tickets", ticketrepo.getTickets());
		}
	
				if(roles.get(0).equals("ROLE_GUEST"))
				{
				
					  DecimalFormat formatter = new DecimalFormat("#0.00"); 
					String username = authentication.getName();
					double subtotal = ticketrepo.getSubTotal(username);
					
					String sub = formatter.format(subtotal);;
					String tax = formatter.format(0.13*subtotal);
					double Tax = Double.parseDouble(tax);
					
					Double Total2 = subtotal + Tax;
					String Total = formatter.format(Total2);
					
					model.addAttribute("tickets", ticketrepo.getGuestTicket(username));
					model.addAttribute("SubTotal",sub);
					model.addAttribute("Tax",tax);
					model.addAttribute("Total",Total);
				}
		 
				return "view.html";
			}
		

	 @GetMapping("/edit/{id}")
	 public String editDrink(@PathVariable int id, Model model) {
		 
		 Ticket ticket = ticketrepo.getTicketById(id);
		 model.addAttribute("tickets", ticket);
		 return "edit.html";
	 }

	 @PostMapping("/editTicket")
	 public String ProcessEdit(@ModelAttribute Ticket  ticket) {
		 Ticket t = ticket;
		 ticketrepo.editTicket(t);
		 return "redirect:/view";
	 }
	 
	 
	 @GetMapping("/delete/{id}")
	 public String deletePage(@PathVariable int id, Model model) {
		 ticketrepo.deleteTicketById(id);
		 model.addAttribute("myDrinks", ticketrepo.getTickets());
		 return "redirect:/view";
		 }

	 @GetMapping("/stats")
	  public String showStat(Model model) {
      model.addAttribute("kk",ticketrepo.maxTicketPrice());
      model.addAttribute("jj", ticketrepo.minTicketPrice());
      model.addAttribute("tt", ticketrepo.maxTicketOnSunday());
      model.addAttribute("aa", ticketrepo.maxTicketOnMonday());
      model.addAttribute("bb",ticketrepo.minTicketPriceSunday());
		 return "stats.html";
	 }
	 
	 
	 @GetMapping("/login") 
	    public String userLogin() {
		return "login.html";
		}
	 
	 @GetMapping("/register")
		public String goRegistration () {
		return "Registration.html";
		}
	 @PostMapping("/register")
		public String doRegistration(@RequestParam String username,
		                             @RequestParam String password) {
			da.addUser(username, password);
			long userId = da.findUserAccount(username).getUserid();
			da.addRole(userId, 2);
		return "redirect:/";		
		}
	 
}

