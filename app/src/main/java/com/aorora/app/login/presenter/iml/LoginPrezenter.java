package com.aorora.app.login.presenter.iml;

import com.aorora.app.bean.User;
import com.aorora.app.login.model.LoginModel;
import com.aorora.app.login.presenter.i.ILoginPresenter;
import com.aorora.app.login.presenter.listener.onLoginListener;
import com.aorora.app.login.view.ILoginView;

/**
 * Created by Administrator on 2017/5/25.
 */
public class LoginPrezenter implements onLoginListener,ILoginPresenter {

    private ILoginView iLoginview;
    private LoginModel mLoginModel;


    public LoginPrezenter(ILoginView iLoginview) {
        this.iLoginview = iLoginview;
        mLoginModel = new LoginModel(this);

    }

    @Override
    public void loginFailed(String message) {

        iLoginview.loginFailed(message);

    }


    @Override
    public void loginSuccess(User user) {

        iLoginview.loginSuccess(user);

    }

    @Override
    public void reason(String msg) {

        iLoginview.reson(msg);

    }

    //此处调用登录
    @Override
    public void ILogin(String userName,String password) {

        mLoginModel.login(userName,password);

    }


}
