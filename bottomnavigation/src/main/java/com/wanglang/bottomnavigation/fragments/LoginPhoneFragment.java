package com.wanglang.bottomnavigation.fragments;


import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.TextView;

import com.wanglang.bottomnavigation.R;

/**
 * A simple {@link Fragment} subclass.
 */
public class LoginPhoneFragment extends Fragment{


    private TextView mTvphoneNum,mTvPhoneClean,mTvPhoneGetAuthCode;
    private EditText mEdInputPhone;

    private TextView mTvAuthCode,mTvAuthCodeClean;
    private EditText mEdInputAuthCode;

    private TextView mMTvLogin,mMTvLoginIssue,mMTvLoginAgree,mMTvLoginPro;
    public LoginPhoneFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_login_phone, container, false);
        initView(view);
        return view;
    }

    private void initView(View view) {
        mTvphoneNum = (TextView) view.findViewById(R.id.tv_item_include_acc_input);
        mEdInputPhone = (EditText) view.findViewById(R.id.ed_item_include_acc_input);
        mTvPhoneClean = (TextView) view.findViewById(R.id.tv_item_include_acc_clean);
        mTvPhoneGetAuthCode = (TextView) view.findViewById(R.id.bt_item_include_authcode);

        mTvAuthCode = (TextView) view.findViewById(R.id.tv_item_include_psd_input);
        mEdInputAuthCode = (EditText) view.findViewById(R.id.ed_item_include_psd_input);
        mTvAuthCodeClean = (TextView) view.findViewById(R.id.tv_item_include_psd_clean);
        view.findViewById(R.id.tv_item_include_authcode).setVisibility(View.GONE);

        mMTvLogin = (TextView) view.findViewById(R.id.tv_item_includ_acc_phone_login);
        mMTvLoginIssue = (TextView) view.findViewById(R.id.tv_item_includ_login_issue);
        mMTvLoginAgree = (TextView) view.findViewById(R.id.tv_item_includ_login_agree);
        mMTvLoginPro = (TextView) view.findViewById(R.id.tv_item_includ_login_pro);

        mMTvLoginIssue.setVisibility(View.VISIBLE);
        mMTvLoginAgree.setVisibility(View.VISIBLE);
        mMTvLoginPro.setVisibility(View.VISIBLE);


        mMTvLogin.setOnClickListener(new MyOnClickListener());
        mMTvLoginIssue.setOnClickListener(new MyOnClickListener());

        mTvPhoneClean.setOnClickListener(new MyOnClickListener());
        mTvAuthCodeClean.setOnClickListener(new MyOnClickListener());
        mTvPhoneGetAuthCode.setOnClickListener(new MyOnClickListener());
        mMTvLoginPro.setOnClickListener(new MyOnClickListener());

        mTvphoneNum.setText("手机号");
        mTvphoneNum.setHint("仅支持中国大陆手机号");
        mTvPhoneGetAuthCode.setVisibility(View.VISIBLE);

        mTvAuthCode.setText("验证碼");
        mTvAuthCode.setHint("请输入验证碼");
        mMTvLoginAgree.setVisibility(View.VISIBLE);
        mMTvLoginPro.setVisibility(View.VISIBLE);



        mEdInputPhone.addTextChangedListener(new EditChangedListener());
        mEdInputAuthCode.addTextChangedListener(new EditChangedListener());

    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

    }


    public class EditChangedListener implements TextWatcher {

        @Override
        public void beforeTextChanged(CharSequence s, int start, int count, int after) {

        }

        @Override
        public void onTextChanged(CharSequence s, int start, int before, int count) {
            Log.i("onTextChanged","mEdInputPhone:"+mEdInputPhone.isFocused());
            if(mEdInputPhone.getText().length() > 0 && mEdInputPhone.isFocused()){
                mTvPhoneClean.setVisibility(View.VISIBLE);
            }else {
                mTvPhoneClean.setVisibility(View.INVISIBLE);
            }
            Log.i("onTextChanged","mEdInputAuthCode:"+mEdInputAuthCode.isFocused()+mEdInputAuthCode.isPressed());
            if (mEdInputAuthCode.getText().length() > 0 && mEdInputAuthCode.isFocused()){
                mTvAuthCodeClean.setVisibility(View.VISIBLE);
            }else {
                mTvAuthCodeClean.setVisibility(View.INVISIBLE);
            }
        }

        @Override
        public void afterTextChanged(Editable s) {

        }
    }


    public class MyOnClickListener implements View.OnClickListener{

        @Override
        public void onClick(View v) {
            Log.i("onFocusChange","v:"+v.getId());
            switch (v.getId()){
                case R.id.tv_item_includ_acc_phone_login://登录操作
                    break;

                case R.id.tv_item_includ_login_issue://登录遇到问题
                    break;

                case R.id.tv_item_include_acc_clean://输入的账号信息clean
                    mEdInputPhone.setText("");
                    break;

                case R.id.tv_item_include_psd_clean://输入的密码clean
                    mEdInputAuthCode.setText("");
                    break;
            }
        }
    }

}
