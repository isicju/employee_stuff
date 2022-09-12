package com.example.demo.services.user;

import java.util.List;

public interface UserApi {
    public List<UserApiImpl.UserStatsData> getUserStatsData();

    public List<UserApiImpl.User> getUsers();
}
