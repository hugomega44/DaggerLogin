package com.example.daggerlogin.login;

public class LoginActivityModel implements LoginActivityMVP.Model {

    private LoginRepository repository;

    public LoginActivityModel(LoginRepository repository){
        this.repository = repository;
    }

    @Override
    public void createUser(String firstName, String lastName) {
        //Logic of Business
        repository .saveUser(new User(firstName,lastName));
    }

    @Override
    public User getUser() {
        return null;
    }
}
