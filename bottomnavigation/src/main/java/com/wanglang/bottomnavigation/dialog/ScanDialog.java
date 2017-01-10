package com.wanglang.bottomnavigation.dialog;

import android.app.AlertDialog;
import android.app.Dialog;
import android.os.Bundle;
import android.support.v4.app.DialogFragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.Window;
import android.widget.TextView;

import com.wanglang.bottomnavigation.R;
import com.wanglang.bottomnavigation.utils.TT;

/**
 * Created by wangl on 2016/12/15 0015.
 */

public class ScanDialog extends DialogFragment implements View.OnClickListener {

    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {

        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        View inflate = LayoutInflater.from(getActivity()).inflate(R.layout.dialog_home_scan, null);
        inflate.findViewById(R.id.tv_dialog_scan_item_scan).setOnClickListener(this);
        inflate.findViewById(R.id.tv_dialog_scan_item_pay_code).setOnClickListener(this);
        builder.setView(inflate);
//        AlertDialog dialog = builder.create();
//        Window window = dialog.getWindow();

//        window.setGravity(Gravity.TOP); // 此处可以设置dialog显示的位置
//        window.setWindowAnimations(R.style.myAnimationstyle); // 添加动画
        return builder.create();
    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        Window window = getDialog().getWindow();
//        WindowManager.LayoutParams attributes = window.getAttributes();
//        window.setBackgroundDrawable(new ColorDrawable(Color.GRAY));
//        WindowManager.LayoutParams mWindowAttributes = window.getAttributes();
//        mWindowAttributes.width = 60;//这个属性需要配合透明背景颜色,才会真正的 MATCH_PARENT
//        mWindowAttributes.height = WindowManager.LayoutParams.WRAP_CONTENT;
//        mWindowAttributes.gravity = Gravity.RIGHT;
//        window.setAttributes(mWindowAttributes);
        window.setLayout(300,200);
    }

    @Override
    public void onClick(View v) {
        TT.showText(((TextView)v).getText().toString());
    }

    @Override
    public void onStart() {
        super.onStart();
    }
}
