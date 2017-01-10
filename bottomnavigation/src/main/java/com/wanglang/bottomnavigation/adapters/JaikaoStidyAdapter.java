package com.wanglang.bottomnavigation.adapters;

import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.wanglang.bottomnavigation.R;
import com.wanglang.bottomnavigation.entitys.DrivingTestEntity;

import java.util.ArrayList;

/**
 * Created by wangl on 2017/1/6 0006.
 */

public class JaikaoStidyAdapter extends RcBaseAdapter<DrivingTestEntity> implements View.OnClickListener{


    private ArrayList<DrivingTestEntity> drivingTestEntities;
    private TextView mTvA;
    private TextView mTvB;
    private TextView mTvC;
    private TextView mTvD;
    private TextView mTvAnswer;
    private TextView mTvExplains;

    public JaikaoStidyAdapter(ArrayList<DrivingTestEntity> drivingTestEntities) {
        super(drivingTestEntities);
        this.drivingTestEntities = drivingTestEntities;
    }

    @Override
    protected int setLayoutid(int viewType) {
        return R.layout.item_rc_jiakao_layout_;
    }

    @Override
    protected void handleViewData(RcBaseHodler holder, int position) {
        holder.setText(R.id.tv_item_rc_jiakao_title,"问题"+(position+1)+"："+drivingTestEntities.get(position).getQuestion());
        holder.setText(R.id.tv_item_rc_jiakao_a,"A. "+drivingTestEntities.get(position).getItem1());
        holder.setText(R.id.tv_item_rc_jiakao_b,"B. "+drivingTestEntities.get(position).getItem2());

        mTvA = holder.getView(R.id.tv_item_rc_jiakao_a);
        mTvB = holder.getView(R.id.tv_item_rc_jiakao_b);
        mTvC = holder.getView(R.id.tv_item_rc_jiakao_c);
        mTvD = holder.getView(R.id.tv_item_rc_jiakao_d);

        mTvA.setText("A. "+drivingTestEntities.get(position).getItem1());
        mTvB.setText("B. "+drivingTestEntities.get(position).getItem2());

        Log.i("handleViewData","jiakoa:"+drivingTestEntities.get(position).getItem3()+"    "+drivingTestEntities.get(position).getItem4());
        if (drivingTestEntities.get(position).getItem3().equals("")){
            mTvC.setVisibility(View.GONE);
        }else{
            mTvC.setText("C. "+drivingTestEntities.get(position).getItem3());
            mTvC.setVisibility(View.VISIBLE);
        }

        if (drivingTestEntities.get(position).getItem4().equals("")){
            mTvD.setVisibility(View.GONE);
        }else{
            mTvD.setText("D. "+drivingTestEntities.get(position).getItem4());
            mTvD.setVisibility(View.VISIBLE);
        }

        mTvA.setOnClickListener(new SingleSeletorOnClickListener(drivingTestEntities.get(position),position));
        mTvB.setOnClickListener(new SingleSeletorOnClickListener(drivingTestEntities.get(position),position));
        mTvC.setOnClickListener(new SingleSeletorOnClickListener(drivingTestEntities.get(position),position));
        mTvD.setOnClickListener(new SingleSeletorOnClickListener(drivingTestEntities.get(position),position));


//        tvC.setText(?tvC.setVisibility(View.GONE):"C. "+drivingTestEntities.get(position).getItem3());
//        holder.setText(R.id.tv_item_rc_jiakao_d,drivingTestEntities.get(position).getItem4().equals("")?"":"D. "+drivingTestEntities.get(position).getItem4());
        mTvAnswer = holder.getView(R.id.tv_item_rc_jiakao_answer);
        mTvExplains = holder.getView(R.id.tv_item_rc_jiakao_explains);
        if (drivingTestEntities.get(position).isShowAnswer()){
            mTvAnswer.setVisibility(View.VISIBLE);
            mTvExplains.setVisibility(View.VISIBLE);
            String answer = drivingTestEntities.get(position).getAnswer();
            if (answer.equals("1")){
                answer = "A";
            }else if (answer.equals("2")){
                answer = "B";
            }else if (answer.equals("3")){
                answer = "C";
            }else
                answer = "D";
            mTvAnswer.setText("答案："+answer);
        }else{
            mTvAnswer.setVisibility(View.GONE);
            mTvExplains.setVisibility(View.GONE);
        }


        holder.setText(R.id.tv_item_rc_jiakao_explains,"解释说明："+drivingTestEntities.get(position).getExplains());
        ImageView view = holder.getView(R.id.iv_item_rc_jiakao_img);
        Glide.with(holder.getContext()).load(drivingTestEntities.get(position).getUrl()).into(view);
    }

    @Override
    public void onClick(View v) {
        initSeletor();
        switch (v.getId()){
            case R.id.tv_item_rc_jiakao_a:
                mTvA.setSelected(true);
                break;
            case R.id.tv_item_rc_jiakao_b:
                mTvB.setSelected(true);
                break;
            case R.id.tv_item_rc_jiakao_c:
                mTvC.setSelected(true);
                break;
            case R.id.tv_item_rc_jiakao_d:
                mTvD.setSelected(true);
                break;
        }
        mTvAnswer.setVisibility(View.VISIBLE);
        mTvExplains.setVisibility(View.VISIBLE);
        String string = mTvAnswer.getText().toString();
        if (((TextView)v).getText().toString().charAt(0) == string.charAt(0)){
            mTvAnswer.setText("回答正确");
        }else
            mTvAnswer.setText("回答错误，正确答案是："+ string);
    }

    private void initSeletor() {
        mTvA.setSelected(false);
        mTvB.setSelected(false);
        mTvC.setSelected(false);
        mTvD.setSelected(false);
    }

    private class SingleSeletorOnClickListener implements View.OnClickListener {
        private DrivingTestEntity drivingTestEntity;
        private int pos;
        public SingleSeletorOnClickListener(DrivingTestEntity drivingTestEntity, int position) {
            this.drivingTestEntity = drivingTestEntity;
            this.pos = position;
        }

        @Override
        public void onClick(View v) {
            initSeletor();
            drivingTestEntity.setShowAnswer(true);
            switch (v.getId()){
                case R.id.tv_item_rc_jiakao_a:
                    mTvA.setSelected(true);
                    break;
                case R.id.tv_item_rc_jiakao_b:
                    mTvB.setSelected(true);
                    break;
                case R.id.tv_item_rc_jiakao_c:
                    mTvC.setSelected(true);
                    break;
                case R.id.tv_item_rc_jiakao_d:
                    mTvD.setSelected(true);
                    break;
            }
            notifyItemChanged(pos);
        }
    }
}
