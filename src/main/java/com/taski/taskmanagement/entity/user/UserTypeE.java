package com.taski.taskmanagement.entity.user;

import java.util.Arrays;

public enum UserTypeE {

    ADMIN("Admin"), TEAM_MEMBER("Team Member"), TEAM_LEADER("Team Leader"), NON_ASSIGNED("Not Assigned");

    private final String roleName;

    UserTypeE(String roleName){
        this.roleName = roleName;
    }

    public String getRoleName() {
        return roleName;
    }

    public static UserTypeE findByName(String roleName){
        return Arrays.stream(values()).filter(role -> role.getRoleName().equals(roleName)).findFirst()
                .orElseThrow(() -> new IllegalStateException("Enum type not found"));
    }

}
