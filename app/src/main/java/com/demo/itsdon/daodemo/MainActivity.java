package com.demo.itsdon.daodemo;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ListView;

import com.greendao.model.DBUser;
import com.greendao.model.User;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {


    private EditText name_et;
    private EditText age_et;
    private ListView listView;
    private List<String> datas = new ArrayList<>();
    private User user;
    private ArrayAdapter<String> adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        initView();

    }

    public void initView(){
        name_et = (EditText) findViewById(R.id.et_name);
        age_et = (EditText) findViewById(R.id.et_age);
        listView = (ListView) findViewById(R.id.listview);
        List<User> list = DBUser.getInstance(this).queryAllUser();
        for(int i = 0; i < list.size(); i++){
            datas.add(list.get(i).getName()+"--"+list.get(i).getAge());
        }
        adapter = new ArrayAdapter<String>(this,android.R.layout.simple_list_item_1,datas);
        listView.setAdapter(adapter);

        findViewById(R.id.btn_insert).setOnClickListener(this);
        findViewById(R.id.btn_delete).setOnClickListener(this);
        findViewById(R.id.btn_update).setOnClickListener(this);
        findViewById(R.id.btn_clear).setOnClickListener(this);


    }


    @Override
    public void onClick(View view) {

        String name = name_et.getText().toString().trim();
        String age = age_et.getText().toString().trim();
        user = new User(name,age);
        switch (view.getId()){
            case R.id.btn_insert:
                DBUser.getInstance(this).insertUser(user);
                break;
            case R.id.btn_delete:
                DBUser.getInstance(this).deleteByName(name_et.getText().toString().trim());
                break;
            case R.id.btn_update:
                DBUser.getInstance(this).update(user);
                break;
            case R.id.btn_clear:
              DBUser.getInstance(this).clear();
                break;
        }
        update();

    }


    public void update(){
        List<User> list = DBUser.getInstance(this).queryAllUser();
        datas.clear();
        for(int i = 0; i < list.size(); i++){
            datas.add(list.get(i).getName()+"--"+list.get(i).getAge());
        }
        adapter.notifyDataSetChanged();
    }

}
