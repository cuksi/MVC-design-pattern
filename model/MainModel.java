package com.codegym.task.task36.task3608.model;

import com.codegym.task.task36.task3608.bean.User;
import com.codegym.task.task36.task3608.model.service.UserService;
import com.codegym.task.task36.task3608.model.service.UserServiceImpl;

import java.util.ArrayList;
import java.util.List;

public class MainModel implements Model{


    private DataModel dataModel = new DataModel();
    private UserService userService = new UserServiceImpl();


    @Override
    public DataModel getDataModel() {
        return dataModel;
    }

    @Override
    public void loadUsers() {

        dataModel.setDisplayDeletedUserList(false);

        List<User> user = getAllUsers();

        dataModel.setUsers(user);



    }

    @Override
    public void loadDeletedUsers() {

        dataModel.setDisplayDeletedUserList(true);

        List<User> deletedUsers = userService.getAllDeletedUsers();

        dataModel.setUsers(deletedUsers);

    }

    @Override
    public void loadUserById(long userId) {

        User user = userService.getUsersById(userId);

        dataModel.setActiveUser(user);

    }

    @Override
    public void deleteUserById(long id) {

        userService.deleteUser(id);

        List<User> users = getAllUsers();

        dataModel.setUsers(users);
    }

    @Override
    public void changeUserData(String name, long id, int level) {


        userService.createOrUpdateUser(name, id, level);

        List<User> users = getAllUsers();

        dataModel.setUsers(users);

    }

    private List<User> getAllUsers() {

        List<User> allUsers = userService.getUsersBetweenLevels(1, 100);

        allUsers = userService.filterOnlyActiveUsers(allUsers);

        return allUsers;

    }


}
