package com.example.daggerlogin;

import com.example.daggerlogin.login.LoginActivityMVP;
import com.example.daggerlogin.login.LoginActivityPresenter;
import com.example.daggerlogin.login.User;

import org.junit.Before;
import org.junit.Test;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.verifyZeroInteractions;
import static org.mockito.Mockito.when;

public class PresenterUnitTest {

    LoginActivityPresenter presenter;
    User user;

    LoginActivityMVP.Model mockedModel;
    LoginActivityMVP.View mockedView;

    @Before
    public void initialization(){
        mockedModel = mock(LoginActivityMVP.Model.class);
        mockedView = mock(LoginActivityMVP.View.class);

        user = new User("Antonio","Banderas");

        when(mockedView.getFirstName()).thenReturn("Antonio");
        when(mockedView.getLastName()).thenReturn("Banderas");


        presenter = new LoginActivityPresenter(mockedModel);
        presenter.setView(mockedView);
    }


    @Test
    public void doesItExistsInteractionWithView(){
        presenter.getCurrentUser();
        verify(mockedView,times(1)).showUserNotAvailable();
    }

    @Test
    public void loadUserFromTheRepositoryWhenValidUserIsPresent(){
        when(mockedModel.getUser()).thenReturn(user);

        presenter.getCurrentUser();
    }
}
