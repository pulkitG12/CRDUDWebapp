package com.vir.springboot.repository;


import com.vir.springboot.model.Assets;
import com.vir.springboot.model.Employee;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public interface AssetsRepository extends JpaRepository<Assets,Long> {

    public List<Assets> findByEmployee(Employee employee);

    @Query(value="SELECT a from Assets a where a.assetCode LIKE '%' || :keyword || '%' ")
    public List<Assets> search(@Param("keyword") String keyword);
}
