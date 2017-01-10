package com.wanglang.bottomnavigation.activitys;

import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.util.TypedValue;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.wanglang.bottomnavigation.R;
import com.wanglang.bottomnavigation.adapters.JaikaoStidyAdapter;
import com.wanglang.bottomnavigation.entitys.DrivingTestEntity;
import com.wanglang.bottomnavigation.utils.TT;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class JiaKao_StudyActivity extends AppCompatActivity {

    @BindView(R.id.bt_layout_study_jk_previous)
    Button mJkPrevious;
    @BindView(R.id.bt_layout_study_jk_next)
    Button mJkNext;
    @BindView(R.id.tv_layout_study_jk_see)
    TextView mJkSee;
    private RecyclerView mRecyclerView;
    private static JaikaoStidyAdapter mJaikaoStidyAdapter;
    private LinearLayoutManager mLinearLayoutManager;
    private static int position = 0;
    private ArrayList<DrivingTestEntity> mDrivingTestEntities;


    public static final String mJiakaoUrl = "http://v.juhe.cn/jztk/query";

    private Handler mHandler = new Handler(){
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);

            try {
//            result =
                JSONObject object = (JSONObject) JSONObject.parse((String) msg.obj);
                Log.i("handleMessage",object.getInteger("error_code")+"");
                if(object.getInteger("error_code")==0){
                    Log.i("handleMessage",object.getString("result"));

                    JSONArray parse = (JSONArray) JSONArray.parse(object.getString("result"));
                    for (int i = 0; i < parse.size(); i++) {
                        JSONObject o = (JSONObject) parse.get(i);
                        DrivingTestEntity testEntity = new DrivingTestEntity();
                        testEntity.setAnswer(o.getString("answer"));
                        testEntity.setExplains(o.getString("explains"));
                        testEntity.setItem1(o.getString("item1"));
                        testEntity.setItem2(o.getString("item2"));
                        testEntity.setItem3(o.getString("item3"));
                        testEntity.setItem4(o.getString("item4"));
                        testEntity.setQuestion(o.getString("question"));
                        testEntity.setUrl(o.getString("url"));
                        mDrivingTestEntities.add(testEntity);
                    }
                    Log.i("handleMessage",mDrivingTestEntities.size()+"");
//                    mJaikaoStidyAdapter.setData(mDrivingTestEntities);

                    runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            mJaikaoStidyAdapter.notifyDataSetChanged();
                        }
                    });
                }else{
                    Log.i("getRequest1",object.getString("error_code")+":"+object.getString("reason"));
                }
            } catch (Exception e) {
                e.printStackTrace();
            }

        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_jia_kao_study);
        ButterKnife.bind(this);


        initView();
        initData();
    }

    private void initData() {
        getJiakaoData();

        mJaikaoStidyAdapter = new JaikaoStidyAdapter(mDrivingTestEntities);

        mLinearLayoutManager = new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false);
        mRecyclerView.setLayoutManager(mLinearLayoutManager);
        mRecyclerView.setAdapter(mJaikaoStidyAdapter);


        mLinearLayoutManager.setReverseLayout(false);//反向
        mLinearLayoutManager.setStackFromEnd(false);
        mRecyclerView.setItemAnimator(new DefaultItemAnimator());//可设置系统默认的动画或者自定义动画

        mRecyclerView.addOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrollStateChanged(RecyclerView recyclerView, int newState) {
                super.onScrollStateChanged(recyclerView, newState);
            }

            @Override
            public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
                super.onScrolled(recyclerView, dx, dy);
                position = mLinearLayoutManager.findFirstVisibleItemPosition();
                int width = getWindow().getWindowManager().getDefaultDisplay().getWidth();
                Log.i("onScrolled","dx:"+dx+"   "+dp2px(Math.abs(dx))+"   width:"+width);
                if (dp2px(Math.abs(dx)) > width/2){
                    if (dx > 0){
                        position--;
                    }else
                        position++;

                    mJaikaoStidyAdapter.notifyItemChanged(position);
                }
            }
        });
    }

    private int dp2px(float dp) {

        return (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, dp,getResources().getDisplayMetrics());
    }

    /**
     * 获取驾考题目，需要从网络上获取
     */
    private void getJiakaoData() {
        mDrivingTestEntities = new ArrayList<>();
        /*

        for (int i = 0; i < 50; i++) {
            DrivingTestEntity testEntity = new DrivingTestEntity();
            testEntity.setQuestion("" + i);
            testEntity.setAnswer("" + i);
            testEntity.setItem1("" + i);
            testEntity.setItem2("" +i);
            testEntity.setItem3("" +i);
            testEntity.setItem4("" +i);
            testEntity.setExplains("" +i);
            mDrivingTestEntities.add(testEntity);
        }

        */
        getRequest1("1", "c1", "rand");

    }


    /*
    初始化View
     */
    private void initView() {
        mRecyclerView = (RecyclerView) findViewById(R.id.rc_layout_study_jk_activity);

    }

    @OnClick(R.id.bt_layout_study_jk_next)
    public void OnClickNext(View v) {
        if (position < mJaikaoStidyAdapter.getItemCount()) {
            mLinearLayoutManager.scrollToPosition(++position);

        } else
            TT.showText("已经是最后一提了");

        mJaikaoStidyAdapter.notifyItemChanged(position);
    }


    @OnClick(R.id.bt_layout_study_jk_previous)
    public void OnClickPreVious(View v) {
        if (position > 0)
            mLinearLayoutManager.scrollToPosition(--position);
        else
            TT.showText("已经是第一题了");

        mJaikaoStidyAdapter.notifyItemChanged(position);
    }

    @OnClick(R.id.tv_layout_study_jk_see)
    public void OnClickDaAn(View v) {
        Log.i("OnClickDaAn","OnClickDaAn"+position);
        if (position < mJaikaoStidyAdapter.getItemCount() && !mDrivingTestEntities.get(position).isShowAnswer()){
            mDrivingTestEntities.get(position).setShowAnswer(true);
            mJaikaoStidyAdapter.notifyItemChanged(position);
        }
    }



    public static final String DEF_CHATSET = "UTF-8";
    public static final int DEF_CONN_TIMEOUT = 30000;
    public static final int DEF_READ_TIMEOUT = 30000;
    public static String userAgent =  "Mozilla/5.0 (Windows NT 6.1) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/29.0.1547.66 Safari/537.36";

    //配置您申请的KEY
    public static final String APPKEY ="bea741cbf0bad761105ed2ae0b9a3cd5";

    //1.题库接口
    public void getRequest1(String subject, String model, String testType){
        String result =null;
        final String url ="http://v.juhe.cn/jztk/query";//请求接口地址
        final Map params = new HashMap();//请求参数
        params.put("key",APPKEY);//您申请的appKey
        params.put("subject",subject);//选择考试科目类型，1：科目1；4：科目4
        params.put("model",model);//驾照类型，可选择参数为：c1,c2,a1,a2,b1,b2；当subject=4时可省略
        params.put("testType",testType);//测试类型，rand：随机测试（随机100个题目），order：顺序测试（所选科目全部题目）

        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    net(url, params, "GET");
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }).start();

    }

    //2.answer字段对应答案
    public static void getRequest2(){
        String result =null;
        String url ="http://api2.juheapi.com/jztk/answers";//请求接口地址
        Map params = new HashMap();//请求参数
        params.put("key",APPKEY);//您申请的appk

        try {
//            result =net(url, params, "GET");
            JSONObject object = JSONObject.parseObject(result);
            if(object.getInteger("error_code")==0){
                System.out.println(object.get("result"));
            }else{
                System.out.println(object.get("error_code")+":"+object.get("reason"));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    /**
     *
     * @param strUrl 请求地址
     * @param params 请求参数
     * @param method 请求方法
     * @return  网络请求字符串
     * @throws Exception
     */
    public void net(String strUrl, Map params,String method) throws Exception {
        HttpURLConnection conn = null;
        BufferedReader reader = null;
        String rs = null;
        try {
            StringBuffer sb = new StringBuffer();
            if(method==null || method.equals("GET")){
                strUrl = strUrl+"?"+urlencode(params);
            }
            Log.e("net","strUrl:"+strUrl);
            URL url = new URL(strUrl);
            conn = (HttpURLConnection) url.openConnection();

            /*

            if(method==null || method.equals("GET")){
                conn.setRequestMethod("GET");
            }else{
                conn.setRequestMethod("POST");
                conn.setDoOutput(true);
            }
            conn.setRequestProperty("User-agent", userAgent);
            conn.setUseCaches(false);
            conn.setConnectTimeout(DEF_CONN_TIMEOUT);
            conn.setReadTimeout(DEF_READ_TIMEOUT);
            conn.setInstanceFollowRedirects(false);
            conn.connect();
            */

            /*
            if (params!= null && method.equals("POST")) {
                try {
                    DataOutputStream out = new DataOutputStream(conn.getOutputStream());
                    out.writeBytes(urlencode(params));
                } catch (Exception e) {
                    // TODO: handle exception
                }
            }

            */
            InputStream is = conn.getInputStream();
            reader = new BufferedReader(new InputStreamReader(is, DEF_CHATSET));
            String strRead = null;
            while ((strRead = reader.readLine()) != null) {
                sb.append(strRead);
            }
            rs = sb.toString();

            Log.e("net","rs:"+rs);

            Message message = new Message();
            message.obj = rs;
            mHandler.handleMessage(message);
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (reader != null) {
                reader.close();
            }
            if (conn != null) {
                conn.disconnect();
            }
        }
//        return rs;
    }

    //将map型转为请求参数型
    public static String urlencode(Map<String,Object> data) {
        StringBuilder sb = new StringBuilder();
        for (Map.Entry i : data.entrySet()) {
            try {
                sb.append(i.getKey()).append("=").append(URLEncoder.encode(i.getValue()+"","UTF-8")).append("&");
            } catch (UnsupportedEncodingException e) {
                e.printStackTrace();
            }
        }
        return sb.toString().substring(0,sb.toString().length()-1);
    }

}
