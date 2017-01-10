package com.wanglang.bottomnavigation.activitys;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.wanglang.bottomnavigation.R;
import com.wanglang.bottomnavigation.adapters.MyFragmentPagerAdapter;
import com.wanglang.bottomnavigation.fragments.LoginAccountFragment;
import com.wanglang.bottomnavigation.fragments.LoginPhoneFragment;

import java.util.ArrayList;

public class LoginRegisterActivity extends AppCompatActivity implements View.OnClickListener{

    private ViewPager mVpLoginRegister;//账号、密码输入
    private TextView mTvLogin;//登录按钮
    private TextView mTvRegister;//注册按钮
    private TextView mTvAccountLogin;//账号登录显示
    private TextView mTvPhoneLogin;//手机号登录显示
    private View mViewAccount;//分割线显示1
    private View mViewPhone;//分割线显示2
    private ImageView mIvHideQitaLogin;//显示或隐藏其他登录
    private TextView mTvQitaLoginShow;
    private LinearLayout mLlLoginShow;
    private TextView mTvLoginQQ;//qq登录
    private TextView mTvLoginWeixin;//微信登录
    private TextView mTvLoginWeibo;//微博登录


    private ArrayList<Fragment> mFragments;
    private View mVHideQitaLogin;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login_register);
        initView();
        initData();
    }

    private void initData() {
        mFragments = new ArrayList<>();
        mFragments.add(new LoginAccountFragment());
        mFragments.add(new LoginPhoneFragment());
        mVpLoginRegister.setAdapter(new MyFragmentPagerAdapter(getSupportFragmentManager(),mFragments));
    }

    private void initView() {
        mTvLogin = (TextView) findViewById(R.id.tv_item_includ_login);
        mTvRegister = (TextView) findViewById(R.id.tv_item_includ_register);
        mTvAccountLogin = (TextView) findViewById(R.id.tv_layout_account_login);
        mTvPhoneLogin = (TextView) findViewById(R.id.tv_layout_phone_login);
        mViewAccount = findViewById(R.id.view_layout_fist);
        mViewPhone = findViewById(R.id.view_layout_second);
        mVpLoginRegister = (ViewPager) findViewById(R.id.vp_layout_login);
        mVHideQitaLogin = findViewById(R.id.view_login_layout_qita_login);
        mIvHideQitaLogin = (ImageView) findViewById(R.id.iv_login_layout_qita_login);
        mTvQitaLoginShow = (TextView)findViewById(R.id.tv_layout_qita_login);
        mLlLoginShow = (LinearLayout) findViewById(R.id.ll_layout_login_share);
        mTvLoginQQ = (TextView) findViewById(R.id.tv_layout_login_qq);
        mTvLoginWeixin = (TextView) findViewById(R.id.tv_layout_login_weixin);
        mTvLoginWeibo = (TextView) findViewById(R.id.tv_layout_login_weibo);
//        mTvLogin.setOnClickListener(this);
        mTvRegister.setOnClickListener(this);
        mTvAccountLogin.setOnClickListener(this);
        mTvPhoneLogin.setOnClickListener(this);
        mIvHideQitaLogin.setOnClickListener(this);
        mTvLoginQQ.setOnClickListener(this);
        mTvLoginWeixin.setOnClickListener(this);
        mTvLoginWeibo.setOnClickListener(this);

        mIvHideQitaLogin.setTag("UP");
        mVpLoginRegister.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {


            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {
                initViewShow();
                if (position == 0)
                    accountLogin();
                else
                    phoneLogin();
            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });
    }

    @Override
    public void onClick(View v) {
        initViewShow();
        switch (v.getId()){
            case R.id.tv_item_includ_login:

                break;

            case R.id.tv_item_includ_register:
                startActivity(new Intent(this, RehisterActivity.class));
                break;

            case R.id.tv_layout_account_login:
                accountLogin();
                break;

            case R.id.tv_layout_phone_login:
                phoneLogin();
                break;

            case R.id.iv_login_layout_qita_login:
                qitaLogin();
                break;

            case R.id.tv_layout_login_qq:

                break;

            case R.id.tv_layout_login_weixin:
                break;

            case R.id.tv_layout_login_weibo:
                break;
        }
    }

    private void initViewShow() {
        mTvAccountLogin.setTextColor(getResources().getColor(R.color.mineItemTextColor));
        mTvPhoneLogin.setTextColor(getResources().getColor(R.color.mineItemTextColor));
        mViewAccount.setBackgroundResource(R.color.gray);
        mViewPhone.setBackgroundResource(R.color.gray);
    }

    private void qitaLogin() {
        if (!mIvHideQitaLogin.getTag().equals("Down")){
            mLlLoginShow.setVisibility(View.GONE);
            mIvHideQitaLogin.setTag("Down");
            mVHideQitaLogin.setVisibility(View.GONE);
        }else{
            mLlLoginShow.setVisibility(View.VISIBLE);
            mIvHideQitaLogin.setTag("UP");
            mVHideQitaLogin.setVisibility(View.VISIBLE);
        }
        if (mVpLoginRegister.getCurrentItem() == 0){
            accountLogin();
        }else
            phoneLogin();
    }

    private void phoneLogin() {
        mVpLoginRegister.setCurrentItem(1);
        mTvPhoneLogin.setTextColor(getResources().getColor(R.color.mineItemHraderColor));
        mViewPhone.setBackgroundResource(R.color.mineItemHraderColor);
        mLlLoginShow.setVisibility(View.GONE);
        mIvHideQitaLogin.setVisibility(View.GONE);
        mTvQitaLoginShow.setVisibility(View.GONE);
    }

    private void accountLogin() {
        mVpLoginRegister.setCurrentItem(0);
        mTvAccountLogin.setTextColor(getResources().getColor(R.color.mineItemHraderColor));
        mViewAccount.setBackgroundResource(R.color.mineItemHraderColor);
        mIvHideQitaLogin.setVisibility(View.VISIBLE);
        mTvQitaLoginShow.setVisibility(View.VISIBLE);
        if (!mIvHideQitaLogin.getTag().equals("Down")){
            mLlLoginShow.setVisibility(View.VISIBLE);
        }
    }
}
