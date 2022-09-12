package com.example.demo.services.user;

import java.util.List;

public class UserApiMock implements UserApi {

    List<UserApiImpl.UserStatsData> userStatsData;
    List<UserApiImpl.User> users;

    public UserApiMock(List<UserApiImpl.UserStatsData> userStatsData, List<UserApiImpl.User> users) {
        this.users = users;
        this.userStatsData = userStatsData;
    }

    @Override
    public List<UserApiImpl.UserStatsData> getUserStatsData() {
        return null;
    }

    @Override
    public List<UserApiImpl.User> getUsers() {
        return null;
    }
}
