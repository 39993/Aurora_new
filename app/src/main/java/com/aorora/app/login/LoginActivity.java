package com.aorora.app.login;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.aorora.app.MainActivity;
import com.aorora.app.R;
import com.aorora.app.base.AppBaseActivity;
import com.aorora.app.bean.User;
import com.aorora.app.login.presenter.i.ILoginPresenter;
import com.aorora.app.login.presenter.iml.LoginPrezenter;
import com.aorora.app.login.view.ILoginView;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class LoginActivity extends AppBaseActivity implements ILoginView {

    @Bind(R.id.membername)
    EditText mMembername;
    @Bind(R.id.memberpassword)
    EditText mMemberpassword;
    @Bind(R.id.login)
    Button mLogin;
    @Bind(R.id.register)
    TextView mRegister;
    @Bind(R.id.rememberpassword)
    TextView mRememberpassword;

    private ILoginPresenter mLoginPresenter;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        ButterKnife.bind(this);

        initdata();

    }

    private void initdata() {

        mLoginPresenter = new LoginPrezenter(this);


    }


    @Override
    public void loginFailed(String message) {



    }

    @Override
    public void loginSuccess(User menber) {


        startActivity(new Intent(this, MainActivity.class));


    }

    @Override
    public void reson(String msg) {


        Log.e("reson",msg);
        showShortToast(msg);
    }

    @OnClick({R.id.membername, R.id.memberpassword, R.id.login, R.id.register, R.id.rememberpassword})
    public void onViewClicked(View view) {
        switch (view.getId()) {

            case R.id.memberpassword:    //忘记密码


                break;
            case R.id.login:  //登录按钮


                String name = mMembername.getEditableText().toString().trim();
                String pass = mMemberpassword.getEditableText().toString().trim();

                if(name.equals("")||pass.equals("")){

                    showShortToast("请输入账号和密码");
                    return;
                }
                mLoginPresenter.ILogin( name,pass);

                break;
            case R.id.register:   //注册


                break;

        }
    }
}
