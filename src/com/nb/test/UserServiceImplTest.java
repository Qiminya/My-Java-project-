package com.nb.test;

import com.nb.sevice.UserService;
import com.nb.sevice.impl.UserServiceImpl;
import org.junit.Test;

import static org.junit.Assert.*;

public class UserServiceImplTest {
private UserService userService=new UserServiceImpl();
    @Test
    public void registUser() {
    }

    @Test
    public void login() {
    }

    @Test
    public void adminlogin() {
    }

    @Test
    public void existsUsername() {
    }

    @Test
    public void findPassword() {
    }

    @Test
    public void page() {
        System.out.println(userService.page(1,2,""));
    }
}