package com.youtube.ecommerce.dao;

import com.youtube.ecommerce.entity.Role;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RoleDao extends CrudRepository<Role, Integer> {
    //public Role findByRoleDescription(String roleDescription);


    public Role findByRoleName(String roleName);
}
