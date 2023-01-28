package com.example.mynote.utils;

import com.example.mynote.payload.Role;

public class RoleUtils {
    public static Role getRoleByInt(int roleId){
        switch (roleId){
            case 1: {
                return Role.EMPLOYEE;
            }
            case 2:{
                return Role.CUSTOMER;
            }
            default:
                throw new RuntimeException("Role not existed!");
        }
    }

    public static Integer getRoleIdByRole(Role role){
        switch (role){
            case EMPLOYEE: {
                return 1;
            }
            case CUSTOMER:{
                return 2;
            }
            default:
                throw new RuntimeException("role not existed!");
        }
    }
}
