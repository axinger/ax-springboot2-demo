package com.axing.demo.service;

import com.axing.demo.model.dto.AccountUserLoginDTO;

public interface UserManager {
    String login(AccountUserLoginDTO req);
}
