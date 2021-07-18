package com.nkhomad.repository;

import com.nkhomad.model.User;

import java.util.List;

public interface UserRepository {

    List<User> fetchUsers(final String userUrl);
}
