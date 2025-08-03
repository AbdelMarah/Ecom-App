package com.youtube.ecommerce.service;

import com.youtube.ecommerce.dao.RoleDao;
import com.youtube.ecommerce.dao.UserDao;
import com.youtube.ecommerce.entity.Role;
import com.youtube.ecommerce.entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import javax.swing.*;
import java.util.HashSet;
import java.util.Set;

@Service
public class UserService {

    @Autowired
    private UserDao userDao;

    @Autowired
    private RoleDao roleDao;

    @Autowired
    private PasswordEncoder passwordEncoder;

    public User registerNewUser(User user) {
        Role role = roleDao.findByRoleName("User");
        Set<Role> userRoles = new HashSet<>();
        userRoles.add(role);
        user.setRole(userRoles);
        user.setUserPassword(getEncodedPassword(user.getUserPassword()));

        return userDao.save(user);
    }

    public Iterable<User> getUsers() {
        return userDao.findAll();
    }

    public String getEncodedPassword(String password) {
        return passwordEncoder.encode(password);
    }

    public void initRoleAndUser() {
        Role superAdminRole = new Role();
        superAdminRole.setRoleId(1);
        superAdminRole.setRoleName("Super Admin");
        superAdminRole.setRoleDescription("SuperAdmin role");
        roleDao.save(superAdminRole);

        Role adminRole = new Role();
        adminRole.setRoleId(2);
        adminRole.setRoleName("Admin");
        adminRole.setRoleDescription("Admin role");
        roleDao.save(adminRole);

        Role managerRole = new Role();
        managerRole.setRoleId(3);
        managerRole.setRoleName("Manager");
        managerRole.setRoleDescription("Manager role");
        roleDao.save(managerRole);

        Role userRole = new Role();
        userRole.setRoleId(4);
        userRole.setRoleName("User");
        userRole.setRoleDescription("Default role for newly created record");
        roleDao.save(userRole);


        User superAdminUser = new User();
        superAdminUser.setUserId(1);
        superAdminUser.setUserName("superAdmin1");
        superAdminUser.setUserPassword(getEncodedPassword("superadmin1234"));
        superAdminUser.setUserFirstName("super admin");
        superAdminUser.setUserLastName("super admin");
        Set<Role> superAdminRoles = new HashSet<>();
        superAdminRoles.add(superAdminRole);
        superAdminRoles.add(adminRole);
        superAdminRoles.add(managerRole);
        superAdminRoles.add(userRole);
        superAdminUser.setRole(superAdminRoles);
        userDao.save(superAdminUser);

        User adminUser = new User();
        adminUser.setUserId(2);
        adminUser.setUserName("admin1");
        adminUser.setUserPassword(getEncodedPassword("admin1234"));
        adminUser.setUserFirstName("admin");
        adminUser.setUserLastName("admin");
        Set<Role> adminRoles = new HashSet<>();
        adminRoles.add(adminRole);
        adminRoles.add(managerRole);
        adminRoles.add(userRole);
        adminUser.setRole(adminRoles);
        userDao.save(adminUser);

        User user1 = new User();
        user1.setUserId(3);
        user1.setUserName("user1");
        user1.setUserPassword(getEncodedPassword("user1234"));
        user1.setUserFirstName("user1");
        user1.setUserLastName("user1");
        Set<Role> user1Roles = new HashSet<>();
        user1Roles.add(userRole);
        user1.setRole(user1Roles);
        userDao.save(user1);

        User user2 = new User();
        user2.setUserId(4);
        user2.setUserName("user2");
        user2.setUserPassword(getEncodedPassword("user1234"));
        user2.setUserFirstName("user2");
        user2.setUserLastName("user2");
        Set<Role> user2Roles = new HashSet<>();
        user2Roles.add(userRole);
        user2.setRole(user2Roles);
        userDao.save(user2);

        User user3 = new User();
        user3.setUserId(5);
        user3.setUserName("user3");
        user3.setUserPassword(getEncodedPassword("user1234"));
        user3.setUserFirstName("user3");
        user3.setUserLastName("user3");
        Set<Role> user3Roles = new HashSet<>();
        user3Roles.add(userRole);
        user3.setRole(user3Roles);
        userDao.save(user3);

        User user4 = new User();
        user4.setUserId(6);
        user4.setUserName("user4");
        user4.setUserPassword(getEncodedPassword("user1234"));
        user4.setUserFirstName("user4");
        user4.setUserLastName("user4");
        Set<Role> user4Roles = new HashSet<>();
        user4Roles.add(userRole);
        user4.setRole(user4Roles);
        userDao.save(user4);

        User user6 = new User();
        user6.setUserId(7);
        user6.setUserName("user6");
        user6.setUserPassword(getEncodedPassword("user1234"));
        user6.setUserFirstName("user6");
        user6.setUserLastName("user6");
        Set<Role> user6Roles = new HashSet<>();
        user6Roles.add(userRole);
        user6.setRole(user6Roles);
        userDao.save(user6);

    }

    public User getProfile(String userName) {
        return userDao.findByUserName(userName);
    }

    public String getName(String userName) {
        return userDao.findByUserName(userName).getUserFirstName()+" "+userDao.findByUserName(userName).getUserLastName();
    }

}
