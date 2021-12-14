package com.vir.springboot.controller;

import com.vir.springboot.model.Employee;
import org.junit.Test;
import org.junit.runner.RunWith;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.test.context.ActiveProfiles;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import org.springframework.test.context.junit4.SpringRunner;
import static org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase.Replace.NONE;
import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

@ActiveProfiles("test")
@RunWith(SpringRunner.class)
@DataJpaTest
@AutoConfigureTestDatabase(replace = NONE)
public class EmployeeControllerTest {

    @Autowired
    private TestEntityManager entityManager;

    @Autowired
    EmployeeController employeeController;

    @Test
    public void viewEmployee() {

        Employee employee=new Employee();
        employee.setFirstName("Pulkit");
        entityManager.persist(employee);
        entityManager.flush();

        Employee testEmployee= employeeController.viewEmployee(employee.getId());

        assertThat(employee.getFirstName()).isEqualTo(testEmployee.getFirstName());
    }

    @Test
    public void createEmployee()
    {
        Employee employee=new Employee();
        employee.setFirstName("Neha");
        employee.setLastName("Mahajan");
        employee.setEmail("neha@gmail.com");

        employeeController.saveEmployee(employee);

        assertNotNull(employeeController.viewEmployee(employee.getId()));
    }
}