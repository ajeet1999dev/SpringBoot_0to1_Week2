package com.springboot0to1.Department_Week2.repository;

import com.springboot0to1.Department_Week2.enitity.DepartmentEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DepartmentRepository extends JpaRepository<DepartmentEntity,Long> {
}
