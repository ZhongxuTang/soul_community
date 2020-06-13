package com.soul.soul_community.repository;

import com.soul.soul_community.entity.Admin;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;

public interface AdminRepository extends JpaRepository<Admin,Integer>, JpaSpecificationExecutor<Admin> {


    @Query(value = "select * from admin where admin_username=?1",nativeQuery = true)
    Admin getAdminByUserName(String userName);
}
