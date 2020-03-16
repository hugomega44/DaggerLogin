package com.example.daggerlogin;

import com.example.daggerlogin.login.LoginActivityMVP;
import com.example.daggerlogin.login.LoginActivityPresenter;
import com.example.daggerlogin.login.User;

import org.junit.Before;
import org.junit.Test;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.never;
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

        user = new User("Manolo","Escobar");




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


        //Comprobamos la interactuacion con el modelo de datos
        verify(mockedModel,times(1)).getUser();

        //Comprobamos la interactuacion con la vista
        verify(mockedView,times(1)).setFirstName("Manolo");
        verify(mockedView,times(1)).setLastName("Escobar");
        verify(mockedView,never()).showUserNotAvailable();
    }


    @Test
    public void isShowingAErrorIfUserIsNull(){
        when(mockedModel.getUser()).thenReturn(null);
        presenter.getCurrentUser();

        verify(mockedView,never()).setFirstName("Manolo");
        verify(mockedView,never()).setLastName("Escobar");
        verify(mockedView,times(1)).showUserNotAvailable();
    }


    @Test
    public void doesItCreateErrorMessageIfAnyFieldIsEmpty(){
        when(mockedView.getFirstName()).thenReturn("");
        presenter.loginButtonClicked();
        verify(mockedView,times(1)).getFirstName();
        verify(mockedView,never()).getLastName();
        verify(mockedView,times(1)).showInputError();

        when(mockedView.getFirstName()).thenReturn("Antonio");
        when(mockedView.getLastName()).thenReturn("");
        presenter.loginButtonClicked();
        verify(mockedView,times(2)).getFirstName();
        verify(mockedView,times(1)).getLastName();
        verify(mockedView,times(2)).showInputError();
    }


    @Test
    public void saveValidUser(){
        when(mockedView.getFirstName()).thenReturn("Esteban ");
        when(mockedView.getLastName()).thenReturn("Lopez ");
        presenter.loginButtonClicked();
        verify(mockedView,times(2)).getFirstName();
        verify(mockedView,times(2)).getLastName();
        verify(mockedModel,times(1)).createUser("Esteban","Lopez");
        verify(mockedView,times(1)).showUserSave();
    }
}
