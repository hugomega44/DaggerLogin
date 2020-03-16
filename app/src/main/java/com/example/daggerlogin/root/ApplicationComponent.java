package com.example.daggerlogin.root;

import com.example.daggerlogin.login.LoginActivity;
import com.example.daggerlogin.login.LoginModule;

import dagger.Component;

@Component(modules = {ApplicationModule.class, LoginModule.class})
public interface ApplicationComponent {

    void inject(LoginActivity target);

}
