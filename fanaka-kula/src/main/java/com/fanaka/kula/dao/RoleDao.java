package com.fanaka.kula.dao;

import com.fanaka.kula.models.Role;

public interface RoleDao {
    Role getRoleByName(String roleName);
}
