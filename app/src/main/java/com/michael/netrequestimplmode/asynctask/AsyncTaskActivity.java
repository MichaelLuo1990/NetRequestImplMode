package com.michael.netrequestimplmode.asynctask;

import android.app.Activity;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.michael.netrequestimplmode.R;
import com.michael.netrequestimplmode.utils.HttpUrlConnUtils;

/**
 * Desc 原生asynctask + httpUrlcon实现
 * Created by Michael on 2018/5/5.
 */

public class AsyncTaskActivity extends Activity {

    TextView textView;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_async);
        textView = (TextView) findViewById(R.id.tv_responed_msg);
    }

    /**
     * 发起网络请求事件
     * @param view
     */
    public void requestClick(View view) {
        new MyAsyncTask().execute();
    }

    /**
     * Asynctask异步处理
     */
    class MyAsyncTask extends AsyncTask<Void, Integer, Boolean> {
        String jsonStr;
        @Override
        protected void onPreExecute() {
            // TODO: 2018/5/5 UI thread preExecuted
            Toast.makeText(AsyncTaskActivity.this, "loading...", Toast.LENGTH_SHORT).show();
        }

        @Override
        protected Boolean doInBackground(Void... params) {
            // TODO: 2018/5/5 executed backgroud task
            jsonStr = HttpUrlConnUtils.get("http://caipu.yjghost.com/index.php/query/read?menu=土豆&rn=15&start=1");
            return true;
        }

        @Override
        protected void onProgressUpdate(Integer... values) {
            // TODO: 2018/5/5 update UI progress
            Toast.makeText(AsyncTaskActivity.this, "当前下载进度：" + values[0] + "%", Toast.LENGTH_SHORT).show();
        }

        @Override
        protected void onPostExecute(Boolean result) {
            // TODO: 2018/5/5 executed UI thread task
            textView.setText(jsonStr);
        }
    }
}
