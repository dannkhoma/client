package com.nkhomad;

import com.nkhomad.model.Address;
import com.nkhomad.model.Company;
import com.nkhomad.model.Geo;
import com.nkhomad.model.User;
import com.nkhomad.model.UserDTO;

import java.util.ArrayList;
import java.util.List;

public class BaseTest {

    protected static final String EXTERNAL_SERVICE_URL="https://jsonplaceholder.typicode.com/users";

    public User[] getUsers() {
        List<User> objects = new ArrayList<>();
        objects.add(getMockUser());
        return objects.toArray(User[]::new);
    }

    public User getMockUser() {
        return User.builder()
                .address(Address.builder()
                        .city("Gwenborough")
                        .street("Kulas Light")
                        .suite("Apt. 556")
                        .zipcode("92998-3874")
                        .geo(Geo.builder()
                                .lat("-37.3159")
                                .lng("81.1496")
                                .build())
                        .build())
                .id(1L)
                .company(Company.builder()
                        .name("Romaguera-Crona")
                        .catchPhrase("Multi-layered client-server neural-net")
                        .bs("harness real-time e-markets")
                        .build())
                .email("Sincere@april.biz")
                .username("Bret")
                .name("Leanne Graham")
                .phone("1-770-736-8031 x56442")
                .website("hildegard.org")
                .build();
    }


    public UserDTO map(User user) {
        return UserDTO.builder()
                .id(user.getId())
                .email(user.getEmail())
                .phone(user.getPhone())
                .build();
    }
}
