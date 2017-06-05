package com.aorora.app.login.presenter.listener;

import com.aorora.app.bean.User;

/**
 * Created by Administrator on 2017/5/25.
 */
public interface onLoginListener {


    void loginFailed(String message);


    void loginSuccess(User user);


    void reason(String msg);

}
