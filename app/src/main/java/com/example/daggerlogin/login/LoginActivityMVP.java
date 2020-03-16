package com.example.daggerlogin.login;

public class LoginActivityMVP {

    public interface Model{
        void createUser(String firstName,String lastName);
        User getUser();
    }

    public interface View{
        String getFirstName();
        String getLastName();

        void showUserNotAvailable();
        void showInputError();
        void showUserSave();

        void setFirstName(String firstName);
        void setLastName(String lastName);
    }

    public interface Presenter{
        void setView(LoginActivityMVP.View view);
        void loginButtonClicked();
        void getCurrentUser();
    }
}
