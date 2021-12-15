package com.vir.springboot;

import com.vir.springboot.model.Employee;
import com.vir.springboot.repository.EmployeeRepository;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

import java.util.List;
import java.util.Optional;

@SpringBootTest
class SpringbootThymeleafCrudWebAppApplicationTests {

	@Autowired
	EmployeeRepository employeeRepository;

	@Test
	void testCreateEmployee()
	{
		Employee employee=new Employee();
		employee.setFirstName("Kartik");
		employee.setLastName("Saxena");
		employee.setEmail("kartik@gmail.com");
		employeeRepository.save(employee);
		assertNotNull(employeeRepository.findByFirstName("Kartik").get(0));
	}

	@Test
	void readAllEmployee()
	{
		List<Employee> employeeList=employeeRepository.findAll();
		assertThat(employeeList.size()).isPositive();
	}

	@Test
	void testSingleEmployee()
	{
		Optional<Employee> employee=employeeRepository.findById(Long.valueOf(4));
		Employee employee1=null;
		if(employee.isPresent())
		{
			employee1=employee.get();
			assertThat(employee1.getFirstName()).isEqualTo("Pulkit");
		}
	}

	@Test
	void testUpdateEmployee()
	{
		Employee employee=employeeRepository.findByFirstName("Mahima").get(0);
		employee.setLastName("Goel");
		employeeRepository.save(employee);
		assertThat(employeeRepository.findByFirstName("Mahima").get(0).getLastName()).isEqualTo("Goel");
	}
	@Test
	void testDeleteEmployee()
		{
			employeeRepository.deleteById(8L);
			assertThat(employeeRepository.existsById(8L)).isFalse();
		}
	}
