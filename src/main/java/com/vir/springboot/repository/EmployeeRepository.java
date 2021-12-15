package com.vir.springboot.repository;

import com.vir.springboot.model.Employee;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface EmployeeRepository extends JpaRepository<Employee, Long>{

    @Query(value="SELECT e from Employee e where e.firstName LIKE '%' || :keyword || '%' ")
    public List<Employee> search(@Param("keyword") String keyword);

    public List<Employee> findByFirstName(String firstName);
}