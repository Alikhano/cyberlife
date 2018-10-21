package ru.alikhano.cyberlife.controller;

import javax.servlet.http.HttpServletRequest;
import javax.validation.ConstraintViolationException;
import javax.validation.Valid;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import ru.alikhano.cyberlife.DTO.AddressDTO;
import ru.alikhano.cyberlife.DTO.CustomLogicException;
import ru.alikhano.cyberlife.DTO.CustomerDTO;
import ru.alikhano.cyberlife.DTO.UserDTO;
import ru.alikhano.cyberlife.service.AddressService;
import ru.alikhano.cyberlife.service.CustomerService;
import ru.alikhano.cyberlife.service.UserService;

@Controller
public class CustomerController {
	
	private static final Logger logger = LogManager.getLogger(CustomerController.class);
	
	@Autowired
	UserService userService;
	
	@Autowired
	CustomerService customerService;
	
	@Autowired
	AddressService addressService;
	
	@Autowired
	BCryptPasswordEncoder encoder;
	
	private static final String REDIRECT = "redirect:/myAccount";  
	
	@RequestMapping("/myAccount")
	public String viewAccount(Model model, Authentication authentication) {
		String username = authentication.getName();
		UserDTO user = userService.getByUsernameDTO(username);
		CustomerDTO customerDTO = customerService.getByUserId(user.getUserId());
		model.addAttribute("customer", customerDTO);
		
		return "customerAccount";
	}
	
	@GetMapping("/myAccount/updateAccount/")
	public String updateAccount(Model model, Authentication authentication) throws CustomLogicException {
		String username = authentication.getName();
		UserDTO user = userService.getByUsernameDTO(username);
		CustomerDTO customer = customerService.getByUserId(user.getUserId());
		
		if (customer == null) {
			logger.error("Customer profile has not been created before");
			customer = new CustomerDTO();
			customer.setUser(user);
			customerService.create(customer);
			model.addAttribute("customer", customer);
			model.addAttribute("error","You seem to not have a profile. We generated it for you");
			return "updateAccount";
		}
		
		return "updateAccount";
			
		}
	
	
	@GetMapping("/myAccount/updateAccount/{customerId}")
	public String updateAccount(@PathVariable("customerId") int customerId, Model model, Authentication authentication) throws CustomLogicException {
		CustomerDTO customerDTO = customerService.getById(customerId);
		String username = authentication.getName();
		UserDTO user = userService.getByUsernameDTO(username);
		CustomerDTO customer = customerService.getByUserId(user.getUserId());
		
		if (customerDTO == null) {
			logger.error("Customer profile has not been created before");
			customer = new CustomerDTO();
			customer.setUser(user);
			customerService.create(customerDTO);
			model.addAttribute("customer", customer);
			model.addAttribute("error","You seem to not have a profile. We generated it for you");
			return "updateAccount";
			
		}
		
		if (customer.getCustomerId() != customerId) {
			logger.error("Oops. You should not try to access someone else's profile!");
			model.addAttribute("customer", customer);
			model.addAttribute("error","You cannot access someone else's profile");
			return "updateAccount";
			
		}

		model.addAttribute("customer", customerDTO);
		

		return "updateAccount";
	}
	
	@RequestMapping(value = "/myAccount/updateAccount", method = RequestMethod.POST)
	public String updateAccountPost(@Valid @ModelAttribute("customer") CustomerDTO customerDTO, BindingResult result,
			HttpServletRequest request, Authentication authentication, Model model) throws CustomLogicException {
		
		try {
			customerService.update(customerDTO);
			logger.info(customerDTO.getLastName() + " has updated his/her account");
		}
		catch (DataIntegrityViolationException ex) {
			model.addAttribute("error","Your have used the email address which is already taken on this website. Please try again.");
			logger.error("Your have used the email address which is already taken on this website. Please try again.");
			return "updateAccount";
		}
		catch (ConstraintViolationException ex) {
			model.addAttribute("error","Please check your input, some fields are filled in incorrectly");
			logger.error("Errors in user input");
			return "updateAccount";
		}
		

		return REDIRECT;
	}
	
	@RequestMapping("/myAccount/changeAddress")
	public String changeAddress(Authentication authentication, Model model) {
		String username = authentication.getName();
		UserDTO user = userService.getByUsernameDTO(username);
		CustomerDTO customerDTO = customerService.getByUserId(user.getUserId());

		model.addAttribute("address", customerDTO.getAddress());

		return "changeAddress";
	}
	
	@RequestMapping(value = "/myAccount/changeAddress", method = RequestMethod.POST)
	public String changeAddressPost(@Valid @ModelAttribute("address") AddressDTO addressDTO, BindingResult result,
			HttpServletRequest request, Authentication authentication, Model model) {
		
		try {
			addressService.update(addressDTO);
			logger.info(authentication.getName() + " has updated his/her account");
		}
		catch (ConstraintViolationException ex) {
			model.addAttribute("error","Your have mistyped values for some of the fields. Please verify provided information (no negative values, correct zip code format)");
			logger.error(ex.getMessage() + "WRONG values");
			return "changeAddress";
		}
		
		
		
		return REDIRECT;
	}
	
	@RequestMapping("/myAccount/changePassword")
	public String changePassword(Authentication authentication, Model model) {
	

		return "changePassword";
	}
	
	@RequestMapping(value = "/myAccount/changePassword", method = RequestMethod.POST)
	public String changePasswordPost(@RequestParam("oldPassword") String oldPassword, @RequestParam("newPassword") String newPassword,
			HttpServletRequest request, Authentication authentication, Model model) {
		String username = authentication.getName();
		UserDTO user = userService.getByUsernameDTO(username);
		if (!userService.verifyPassword(oldPassword, user.getUserId())) {
			model.addAttribute("mismatchMsg", "Oops, entered password does not match the stored value");
			logger.error("Oops, entered password does not match the stored value");
			return "changePassword";
		}
		userService.changePassword(newPassword, user);
		logger.info(username + "has changed his/her password");
		return REDIRECT;
	}



}
