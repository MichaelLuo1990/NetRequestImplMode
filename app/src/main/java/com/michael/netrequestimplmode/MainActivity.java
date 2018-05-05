package com.michael.netrequestimplmode;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import com.michael.netrequestimplmode.asynctask.AsyncTaskActivity;
import com.michael.netrequestimplmode.handlerthread.HTActivity;

/**
 * Desc 网络请求方式解析(实现至java实现，原生Android与第三方框架请求解析待测试添加)
 * Created by Michael on 2018/4/10.
 */
public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ListView listView = (ListView) findViewById(R.id.lv_main);
        String[] array = {"httpClient&httpGet&httpPost(apache包)-java实现",
                "httpUrlConnection(java.net包)-java实现",
                "原生handle（message）+thread（runnable）+HttpURLConnection实现",
                "原生AsyncTask+HttpURLConnection实现"};
        ArrayAdapter<String> arrayAdapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, array);
        listView.setAdapter(arrayAdapter);
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                Intent intent;
                switch (i) {
                    case 0:
                        Toast.makeText(MainActivity.this, "javaLibs模块中测试/实现", Toast.LENGTH_SHORT).show();
                        break;
                    case 1:
                        Toast.makeText(MainActivity.this, "javaLibs模块中测试/实现", Toast.LENGTH_SHORT).show();
                        break;
                    case 2:
                        intent = new Intent(MainActivity.this, HTActivity.class);
                        startActivity(intent);
                        break;
                    case 3:
                        intent = new Intent(MainActivity.this, AsyncTaskActivity.class);
                        startActivity(intent);
                        break;
                    default:
                        break;
                }
            }
        });
    }
}
