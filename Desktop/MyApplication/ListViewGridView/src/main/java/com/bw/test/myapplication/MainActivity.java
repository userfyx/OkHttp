package com.bw.test.myapplication;

import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.GridView;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.ScrollView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private ListView lv;
    private GridView gv;
    private List<ImageView> imglist;
    private List<String> list;
    private ScrollView scrollview;
    private GridView gv2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        list = new ArrayList<>();
        for (int i=0;i<30;i++){
            list.add("aaaa"+i);
        }
        lv = (ListView) findViewById(R.id.lv);
        lv.setAdapter(new BaseAdapter() {
            @Override
            public int getCount() {
                return list.size();
            }

            @Override
            public Object getItem(int i) {
                return list.get(i);
            }

            @Override
            public long getItemId(int i) {
                return i;
            }

            @Override
            public View getView(int i, View view, ViewGroup viewGroup) {
                view=View.inflate(MainActivity.this,android.R.layout.simple_list_item_1,null);
                TextView tv= (TextView) view.findViewById(android.R.id.text1);
                tv.setText(list.get(i));
                return view;
            }
        });
        scrollview = (ScrollView) findViewById(R.id.ss);

        gv = (GridView) findViewById(R.id.gv);
        gv.setBackgroundColor(Color.GRAY);
        gv2 = (GridView) findViewById(R.id.gv2);
        gv2.setBackgroundColor(Color.GRAY);
        initData();
//        lv.setOnTouchListener(new View.OnTouchListener() {
//            @Override
//            public boolean onTouch(View view , MotionEvent event) {
//                if(event.getAction() == MotionEvent.ACTION_UP){
//                    scrollview.requestDisallowInterceptTouchEvent(false);
//                }else if(event.getAction()==MotionEvent.ACTION_MOVE){
//                    scrollview.requestDisallowInterceptTouchEvent(true);
//                    return false;
//                }
//            });

//        setListViewHeightBasedOnChildren(lv);
    }
   /* private void setListViewHeightBasedOnChildren(ListView listView) {

        ListAdapter listAdapter = listView.getAdapter();
        if (listAdapter == null) {
            return;
        }

        int totalHeight = 0;
        for (int i = 0; i < listAdapter.getCount(); i++) {
            View listItem = listAdapter.getView(i, null, listView);
            listItem.measure(0, 0);
            totalHeight += listItem.getMeasuredHeight();
        }

        ViewGroup.LayoutParams params = listView.getLayoutParams();

        params.height = totalHeight
                + (listView.getDividerHeight() * (listAdapter.getCount() - 1));

        // 得到当前屏幕的高
        int h = getWindowManager().getDefaultDisplay().getHeight();
        if (params.height > h / 2) {// 如果listview的高大于屏幕的二分之一，设置listview为屏幕的二分之一
            params.height = h / 2;
        }
        // listView.getDividerHeight()获取子项间分隔符占用的高度

        // params.height最后得到整个ListView完整显示需要的高度

        listView.setLayoutParams(params);
    }*/

    private void initData() {
        imglist = new ArrayList<ImageView>();
        ImageView Image1=new ImageView(R.drawable.gv1);
        ImageView Image2=new ImageView(R.drawable.gv2);
        ImageView Image3=new ImageView(R.drawable.gv3);
        ImageView Image4=new ImageView(R.drawable.gv4);
        ImageView Image5=new ImageView(R.drawable.gv5);
        ImageView Image6=new ImageView(R.drawable.gv6);
        ImageView Image7=new ImageView(R.drawable.gv7);
        ImageView Image8=new ImageView(R.drawable.gv8);
        ImageView Image9=new ImageView(R.drawable.gv9);
        ImageView Image10=new ImageView(R.drawable.gv10);
        ImageView Image11=new ImageView(R.drawable.gv11);
        ImageView Image12=new ImageView(R.drawable.gv12);
        ImageView Image13=new ImageView(R.drawable.gv13);
        ImageView Image14=new ImageView(R.drawable.gv14);
       imglist.add(Image1);
        imglist.add(Image2);
        imglist.add(Image3);
        imglist.add(Image4);
        imglist.add(Image5);
        imglist.add(Image6);
        imglist.add(Image7);
        imglist.add(Image8);
        imglist.add(Image9);
        imglist.add(Image10);
        imglist.add(Image11);
        imglist.add(Image12);
        imglist.add(Image13);
        imglist.add(Image14);
        GridViewAdapter adapter=new GridViewAdapter(MainActivity.this,imglist);
        gv.setAdapter(adapter);
        gv2.setAdapter(new MyAdapter(MainActivity.this,imglist));
    }

}
