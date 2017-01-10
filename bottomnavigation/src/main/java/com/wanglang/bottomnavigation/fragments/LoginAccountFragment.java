package com.wanglang.bottomnavigation.fragments;


import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.EditorInfo;
import android.widget.EditText;
import android.widget.TextView;

import com.wanglang.bottomnavigation.R;

/**
 * A simple {@link Fragment} subclass.
 */
public class LoginAccountFragment extends Fragment {


    private EditText mEdInputAccount, mEdInputPsd;
    private TextView mTvAcClean, mTvPsdClean, mTvPsdLook;
    private TextView mMTvLogin, mMTvLoginIssue;

    public LoginAccountFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_login_account, container, false);
        initView(view);
        return view;
    }

    private void initView(View view) {
        mEdInputAccount = (EditText) view.findViewById(R.id.ed_item_include_acc_input);
        mTvAcClean = (TextView) view.findViewById(R.id.tv_item_include_acc_clean);
        view.findViewById(R.id.bt_item_include_authcode).setVisibility(View.GONE);

        mEdInputPsd = (EditText) view.findViewById(R.id.ed_item_include_psd_input);
        mTvPsdClean = (TextView) view.findViewById(R.id.tv_item_include_psd_clean);
        mTvPsdLook = (TextView) view.findViewById(R.id.tv_item_include_authcode);

        mMTvLogin = (TextView) view.findViewById(R.id.tv_item_includ_acc_phone_login);
        mMTvLoginIssue = (TextView) view.findViewById(R.id.tv_item_includ_login_issue);

        mMTvLoginIssue.setVisibility(View.VISIBLE);

        mMTvLogin.setOnClickListener(new MyOnClickListener());
        mMTvLoginIssue.setOnClickListener(new MyOnClickListener());
        mTvPsdLook.setOnClickListener(new MyOnClickListener());
        mTvAcClean.setOnClickListener(new MyOnClickListener());
        mTvPsdClean.setOnClickListener(new MyOnClickListener());

        mTvPsdLook.setVisibility(View.VISIBLE);
        mMTvLoginIssue.setVisibility(View.VISIBLE);


        mEdInputAccount.addTextChangedListener(new EditChangedListener());
        mEdInputPsd.addTextChangedListener(new EditChangedListener());
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
            if (mEdInputAccount.getText().length() > 0) {
                mTvAcClean.setVisibility(View.VISIBLE);
            } else {
                mTvAcClean.setVisibility(View.INVISIBLE);
            }
            if (mEdInputPsd.getText().length() > 0) {
                mTvPsdClean.setVisibility(View.VISIBLE);
            } else {
                mTvPsdClean.setVisibility(View.INVISIBLE);
            }
        }

        @Override
        public void afterTextChanged(Editable s) {

        }
    }

    public class MyOnClickListener implements View.OnClickListener {

        @Override
        public void onClick(View v) {
            switch (v.getId()) {
                case R.id.tv_item_includ_acc_phone_login://登录操作

                    break;

                case R.id.tv_item_includ_login_issue://登录遇到问题
                    break;

                case R.id.tv_item_include_authcode://查看或隐藏密码
                    if (mEdInputPsd.getInputType() == EditorInfo.TYPE_TEXT_VARIATION_PASSWORD) {
                        mEdInputPsd.setInputType(EditorInfo.TYPE_CLASS_TEXT);
                        mTvPsdLook.setBackgroundResource(R.mipmap.passport_ic_show_password);
                    } else {
                        mEdInputPsd.setInputType(EditorInfo.TYPE_TEXT_VARIATION_PASSWORD);
                        mTvPsdLook.setBackgroundResource(R.mipmap.passport_ic_hide_password);
                    }
                    break;

                case R.id.tv_item_include_acc_clean://输入的账号信息clean
                    mEdInputAccount.setText("");
                    break;

                case R.id.tv_item_include_psd_clean://输入的密码clean
                    mEdInputPsd.setText("");
                    break;
            }
        }
    }
}
