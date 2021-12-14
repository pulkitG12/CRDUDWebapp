package com.vir.springboot.controller;

import com.vir.springboot.model.Assets;
import com.vir.springboot.service.AssetsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import com.vir.springboot.model.Employee;
import com.vir.springboot.service.EmployeeService;

import java.util.List;

//@Controller
@RestController
@CrossOrigin(origins="http://localhost:4200")
@RequestMapping(value="/api")
public class EmployeeController {

	@Autowired
	private EmployeeService employeeService;
	@Autowired
	private AssetsService assetsService;

	private static final String EMPLOYEE="employee";
	// display list of employees
	@GetMapping("/")
	public String viewHomePage(Model model) {
		model.addAttribute("listEmployees", employeeService.getAllEmployees());
		return "index";
	}

	@GetMapping("/employee")
	public List<Employee> getAll()
	{
		return employeeService.getAllEmployees();
	}


	@GetMapping("/search")
	public String searchEmployee(String keyword,Model model)
	{
		model.addAttribute("listEmployees", employeeService.searchEmployee(keyword));
		return "index";
	}
	@GetMapping("/showNewEmployeeForm")
	public String showNewEmployeeForm(Model model)
	{
		// create model attribute to bind form data
		Employee employee = new Employee();
		model.addAttribute(EMPLOYEE, employee);
		return "new_employee";
	}
	@PostMapping("/saveEmployee")
	public String saveEmployee(@ModelAttribute("employee") Employee employee) {
		// save employee to database
		employeeService.saveEmployee(employee);
		return "redirect:/";
	}
	@GetMapping("/viewDetails/{id}")
	public String viewDetails(@PathVariable( value = "id") long id, Model model) {

		Employee employee = employeeService.getEmployeeById(id);
		model.addAttribute(EMPLOYEE,employee);
		model.addAttribute("assets",assetsService.getAssetsByEmployee(employee));
		return "employeeAssets";
	}




	@GetMapping("/employee/{id}")
	public Employee viewEmployee(@PathVariable(value = "id")long id)
	{
		return employeeService.getEmployeeById(id);
	}

	@GetMapping("/employee/asset/{id}")
	public List<Assets> viewEmployeeAsset(@PathVariable( value = "id") long id)
	{
		Employee employee = employeeService.getEmployeeById(id);
		return assetsService.getAssetsByEmployee(employee);
	}







		@GetMapping("/showFormForUpdate/{id}")
	public String showFormForUpdate(@PathVariable( value = "id") long id, Model model) {
		
		// get employee from the service
		Employee employee = employeeService.getEmployeeById(id);
		
		// set employee as a model attribute to pre-populate the form
		model.addAttribute(EMPLOYEE, employee);
		return "update_employee";
	}
	
	@GetMapping("/deleteEmployee/{id}")
	public String deleteEmployee(@PathVariable (value = "id") long id) {
		
		// call delete employee method 
		this.employeeService.deleteEmployeeById(id);
		return "redirect:/";
	}
}
