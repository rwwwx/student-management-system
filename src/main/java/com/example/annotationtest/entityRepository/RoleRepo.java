package com.example.annotationtest.entityRepository;

import com.example.annotationtest.entity.Role;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RoleRepo extends JpaRepository<Role, Long> {

}
