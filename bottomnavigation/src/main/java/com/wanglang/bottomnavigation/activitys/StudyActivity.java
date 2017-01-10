package com.wanglang.bottomnavigation.activitys;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.TextView;

import com.wanglang.bottomnavigation.R;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * 培训学习界面-----驾考培训为例
 */

public class StudyActivity extends AppCompatActivity {

    @BindView(R.id.textView5)
    TextView mTextView5;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_study);
        ButterKnife.bind(this);
    }

    @OnClick(R.id.textView5)
    public void OnClickJiakao(View v){
        startActivity(new Intent(this,JiaKao_StudyActivity.class));
    }
}
