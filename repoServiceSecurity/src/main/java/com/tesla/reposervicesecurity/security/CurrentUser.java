package com.tesla.reposervicesecurity.security;

import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.userdetails.User;

public class CurrentUser extends User {
   private final com.tesla.model.User user;

    public CurrentUser(com.tesla.model.User user1) {
        super(user1.getEmail(), user1.getPassword(), AuthorityUtils.createAuthorityList(user1.getUserType().name()));
        this.user = user1;
    }
    public com.tesla.model.User getUser() {
        return this.user;
    }
}
