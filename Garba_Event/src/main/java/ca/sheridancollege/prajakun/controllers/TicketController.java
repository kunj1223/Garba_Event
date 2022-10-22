package ca.sheridancollege.prajakun.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import ca.sheridancollege.prajakun.beans.Ticket;
import ca.sheridancollege.prajakun.repositories.TicketRepository;

import lombok.AllArgsConstructor;


@AllArgsConstructor
@Controller
public class TicketController {

	private TicketRepository ticketrepo;
	
	@GetMapping("/") 
    public String home() {
	return "home.html";
     }
	
	@GetMapping("/add") 
    public String home(Model model) {
    model.addAttribute("tickets", new Ticket());
	return "add.html";
     }
	
	@GetMapping("/process")
	 public String Process(@ModelAttribute Ticket tickets) {
	 ticketrepo.addTicket(tickets);
	 return "redirect:/add";
    }
	
	 @GetMapping("/view") 
	 public String loadview(Model model) {
		 model.addAttribute("tickets",ticketrepo.getTickets());
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
	 
}

