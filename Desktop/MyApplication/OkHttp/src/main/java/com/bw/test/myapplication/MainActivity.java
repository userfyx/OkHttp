package com.bw.test.myapplication;

import android.graphics.Color;
import android.os.Handler;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.view.ActionMode;
import android.text.SpannableString;
import android.text.Spanned;
import android.text.style.ForegroundColorSpan;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;
import com.google.gson.Gson;
import java.io.IOException;
import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.FormBody;
import okhttp3.MediaType;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;


public class MainActivity extends AppCompatActivity implements View.OnClickListener {
    //设置标题头格式
    public static final MediaType JSON = MediaType.parse("application/json; charset=utf-8");
    private Button btnget;
    private TextView tv;
    private Button btnpost;
    private Button btngetsame;
    private Button btnpostsame;
    final OkHttpClient client = new OkHttpClient();
    private String url="http://www.tngou.net/api/cook/list";

    /**
     * 异步post  参数为对象
     * @param url
     * @param obj
     * @param callback
     */
    public static void post(String url,Object obj,ActionMode.Callback callback){
        post(url,new Gson().toJson(obj),callback);
    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        btnget = (Button) findViewById(R.id.btn_get);
        btnget.setOnClickListener(this);

        btnpost = (Button) findViewById(R.id.btn_post);
        btnpost.setOnClickListener(this);

        btngetsame = (Button) findViewById(R.id.btn_get_same);
        btngetsame.setOnClickListener(this);

        btnpostsame = (Button) findViewById(R.id.btn_post_same);
        btnpostsame.setOnClickListener(this);

        tv = (TextView) findViewById(R.id.text);

    }
    @Override
    public void onClick(View view) {
        switch(view.getId()){
            case R.id.btn_get:
                Toast.makeText(MainActivity.this,"异步OkHttp_GET请求数据",Toast.LENGTH_SHORT).show();
                getRequest();
                break;
            case R.id.btn_post:
                Toast.makeText(MainActivity.this,"异步OkHttp_POST请求数据",Toast.LENGTH_SHORT).show();
                postRequest();
                break;
            case R.id.btn_get_same:
                Toast.makeText(MainActivity.this,"同步OkHttp_GET请求数据",Toast.LENGTH_SHORT).show();
                samegetRequest();
                break;
            case R.id.btn_post_same:
                Toast.makeText(MainActivity.this,"同步OkHttp_POST请求数据",Toast.LENGTH_SHORT).show();
                samepostRequest();
                break;
        }
    }
    //同步OkHttp_GET请求数据
    private void samegetRequest() {
        Request request =new Request.Builder()
                .get()
                .url(url)
                .build();
        try {
            Call call = new OkHttpClient().newCall(request);
            Response response = call.execute();
            if(response.isSuccessful()){

                Log.v("sameOkHttpGET",response.body().string());
                // String str=response.body().string();
               // tv.setText(str);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    //同步OkHttp_POST请求数据
    private void samepostRequest() {
    }

    private void getRequest() {

        final Request request=new Request.Builder()
                .get()
                .tag(this)
                .url("http://www.tngou.net/api/cook/list")
                .build();

         Call call= client.newCall(request);

        call.enqueue(new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {
            }
            @Override
            public void onResponse(Call call, Response response) throws IOException {
                final String str1=response.body().string();
                Log.e("121231",str1);
              runOnUiThread(new Runnable() {
                  @Override
                  public void run() {
                      SpannableString ss=new SpannableString(str1);
                      ss.setSpan(new ForegroundColorSpan(Color.RED),0,str1.length(), Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
                     tv.setText(ss);
                  }
              });
            }
        });

    }
    private void postRequest() {

        FormBody formBody=new FormBody.Builder().add("","").build();
        final Request request=new Request.Builder()
                .post(formBody)
                .tag(this)
                .url("http://www.tngou.net/api/cook/list")
                .build();
        Call call1= new OkHttpClient().newCall(request);
        call1.enqueue(new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {

            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                final String str2=response.body().string();
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        SpannableString ss=new SpannableString(str2);
                        ss.setSpan(new ForegroundColorSpan(Color.BLUE),0,str2.length(), Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
                        tv.setText(ss);


                    }
                });
            }
        });

    }
}
