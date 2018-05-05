package com.michael.netrequestimplmode.handlerthread;

import android.app.Activity;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.annotation.Nullable;
import android.view.View;
import android.widget.TextView;

import com.michael.netrequestimplmode.R;
import com.michael.netrequestimplmode.utils.HttpUrlConnUtils;

/**
 * Desc handle（message）+thread（runnable）实现网络请求
 * Created by Michael on 2018/5/3.
 */

public class HTActivity extends Activity {

    private static String httpsUrl = "http://www.sojson.com/open/api/weather/json.shtml";
    private static String httpsParam = "city=福州";
    private Handler handler;
    private static int GET_RESULT_SUCCESS = 200;
    private static int GET_RESULT_FAIL = 500;
    private TextView textView;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ht);
        textView = (TextView) findViewById(R.id.tv_responed_msg);
        initHandler();
    }

    /**
     * 初始化handler
     */
    private void initHandler() {
        handler = new Handler(){
            @Override
            public void handleMessage(Message msg) {
                super.handleMessage(msg);
                if(msg.what == GET_RESULT_SUCCESS) {
                    Bundle bundle = msg.getData();
                    String result = bundle.getString("result");
                    textView.setText(result);
                }
            }
        };
    }

    /**
     * 网络请求测试
     * @param view
     */
    public void requestClick(View view) {
        new Thread(new Runnable() {
            @Override
            public void run() {
                String jsonStr = HttpUrlConnUtils.get("http://caipu.yjghost.com/index.php/query/read?menu=土豆&rn=15&start=1");
                Bundle bundle = new Bundle();
                Message message = new Message();
                bundle.putString("result", jsonStr);
                message.setData(bundle);
                message.what = GET_RESULT_SUCCESS;
                handler.sendMessage(message);
            }
        }).start();
    }
}
