package com.example.CinemaEBooking.model.admin;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.ui.Model;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

@Controller
public class AdminController {
	@Autowired
	private AdminRepository adminRepository;


	/* 
		Show Pages
	*/
	// Admin Home
	@GetMapping("/admin/home")
	public String viewAdminHomePage() {
		return "AdminHomePage";
	}


	// Movie manage page
	@GetMapping("/admin/movies")
	public String viewManageMoviesPage() {
		return "manage_movie";
	}


	// User Manage Page
	@GetMapping("/admin/users")
	public String viewManageUsersPage() {
		return "manage_user";
	}

	// Promotion Manage Page
	@GetMapping("/admin/promotions")
	public String viewManagePromotionsPage() {
		return "manage_promotion";
	}

	// Admin Manage Page
	@GetMapping("/admin/admins")
	public String viewManageAdminsPage() {
		return "manage_admin";
	}


	// Manage Ticket Price 
	@GetMapping("/admin/ticketprice")
	public String viewManageTicketPricePage() {
		return "ticket_price";
	}



	/*
		Add Pages
	*/

	// Add Movie Page
	@GetMapping("/admin/addMovie")
	public String showAddMovieForm() {

		return "add_movie";
	}

	// Add Promotion Page
	@GetMapping("/admin/addPromotion")
	public String showAddPromotionForm() {

		return "add_promotion";
	}

	// Add Admin Page
	@GetMapping("/admin/addAdmin")
	public String showAddAdminForm() {

		return "add_admin";
	}

	// Add Time/Date Page
	// have to grab "this" movie id first to add new date to only "this" movie
	@GetMapping("/admin/addDate")
	public String showAddDateForm() {

		return "add_date";
	}

	 

	/* 
		Edit pages
	*/
	// edit movie
	// have to grab "this" movie id first to apply changes to only "this" movie
	@GetMapping("/admin/EditMovie")
	public String showEditMovie() {

		return "edit_movie";
	}

	// edit admin
	// have to grab "this" admin id first to apply changes to only "this" admin
	@GetMapping("/admin/EditAdmin")
	public String showEditAdmin() {

		return "edit_admin";
	}



	/*
	* other CRUD methods
	*/
	@PostMapping(path="/addAdmin")
	public @ResponseBody String addNewAdmin(
		@RequestParam String firstName,
		@RequestParam String lastName, @RequestParam String email,
		@RequestParam String encryptedPassword) {
	
		Admin admin = new Admin();
		admin.setFirstName(firstName);
		admin.setLastName(lastName);
		admin.setEmail(email);
		admin.setEncryptedPassword(encryptedPassword);
		adminRepository.save(admin);
		return "Saved";
	}

	@GetMapping(path="/viewAdmins")
	public @ResponseBody Iterable<Admin> getAllAdmins() {
		return adminRepository.findAll();
	}
}
