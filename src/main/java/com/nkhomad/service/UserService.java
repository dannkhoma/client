package com.nkhomad.service;

import com.nkhomad.model.UserDTO;

public interface UserService {

    UserDTO fetchUserByIdentifiers(final Long id, final String username);

}
