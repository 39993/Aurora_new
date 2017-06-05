package com.aorora.app.login.view;

import com.aorora.app.bean.User;

/**
 * Created by Administrator on 2017/5/25.
 */
public interface ILoginView {

    void loginFailed(String message);


    void loginSuccess(User user);

    void reson(String msg);

}
