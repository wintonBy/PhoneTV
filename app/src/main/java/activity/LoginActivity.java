package activity;

import android.content.Intent;
import android.support.design.widget.TextInputEditText;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.Button;

import com.wasu.winton.phonetv.R;

import utils.StringUtil;
import utils.ToastUtil;

/**
 * Created by Administrator on 2016/3/13.
 */
public class LoginActivity extends WstvBaseActivity implements View.OnClickListener{
    private TextInputEditText mETUsername = null;
    private TextInputEditText mETPasswd = null;
    private Button mBTLogin = null;


    @Override
    public void initView() {
        setContentView(R.layout.activity_login);
        mETUsername = (TextInputEditText)findViewById(R.id.login_et_username);
        mETPasswd = (TextInputEditText)findViewById(R.id.login_et_password);
        mBTLogin = (Button)findViewById(R.id.login_ib_login);

    }

    @Override
    public void initListener() {
        mBTLogin.setOnClickListener(this);
        mETUsername.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

                mETPasswd.setText(StringUtil.EMPTY_STRING);
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });
        mETPasswd.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                if(s.length() > 0){
                    mBTLogin.setEnabled(true);
                }else {
                    mBTLogin.setEnabled(false);
                }
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });
    }

    @Override
    public void initData() {
        mBTLogin.setEnabled(false);
    }

    @Override
    public void onClick(View v) {

        if(v == mBTLogin){
            String username = mETUsername.getText().toString().trim();
            String password = mETPasswd.getText().toString().trim();
            boolean isOk = checkUserInfo(username,password);
            if(isOk){
                ToastUtil.showSuccessMsg(R.string.login_success);
                Intent intent = new Intent(this,IndexActivity.class);
                startActivity(intent);
                this.finish();
            }else {
                ToastUtil.showErrorMsg(R.string.username_or_passwd_error);
            }

        }
    }

    private boolean checkUserInfo(String username, String passwd){
        if(username.trim().length() > 0 && passwd.trim().length() >0){

            if(username.equals("zww") && passwd.equals("123456")){
                return true;
            }else {
                return false;
            }

        }
        return false;
    }



}
