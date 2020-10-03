package com.aritra.media.service;


import com.aritra.media.domain.Role;

import java.util.List;

public interface RoleService {

    Role findRoleById(long id);

    List<Role> getAllRoles();

}
