package com.tim.test02;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;

import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {
    private Button button_load;
    private Button button_regist;
    private EditText user_name;
    private EditText user_un;
    private EditText user_age;
    private EditText user_num;
    private EditText user_pwd;
    private Button button_exit;
    private DataBase db;
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        db=new DataBase(this);
        db.open();
        findViews();
        setListeners();
    }

    private void findViews() {
        // TODO Auto-generated method stub
        button_load=(Button)findViewById(R.id.ld);
        button_regist=(Button)findViewById(R.id.reg);
        button_exit=(Button)findViewById(R.id.exit);
        user_name=((EditText)findViewById(R.id.name));
        user_pwd=((EditText)findViewById(R.id.password));
        user_un=((EditText)findViewById(R.id.un));
        user_age=((EditText)findViewById(R.id.age));
        user_num=((EditText)findViewById(R.id.num));
    }

    private void setListeners() {
        // TODO Auto-generated method stub
        button_exit.setOnClickListener(new OnClickListener(){
            public void onClick(View v){
                new AlertDialog.Builder(MainActivity.this).setMessage("是否要退出程序？")
                        .setPositiveButton("yes", new DialogInterface.OnClickListener() {

                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                // TODO Auto-generated method stub
                                finish();
                            }
                        })
                        .setNegativeButton("no", new DialogInterface.OnClickListener() {

                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                // TODO Auto-generated method stub

                            }
                        }).show();
            }
        });
        button_load.setOnClickListener(new OnClickListener(){
            public void onClick(View v){
                //登录验证
                if(db.checkUser(user_name.getText().toString(),user_pwd.getText().toString()).moveToNext()){
                    Intent intent=new Intent();
                    Bundle bundle=new Bundle();
                    bundle.putString("KEY_NAME",user_name.getText().toString());
                    bundle.putString("KEY_PWD",user_pwd.getText().toString());
                    bundle.putString("KEY_UN",user_un.getText().toString());
                    bundle.putString("KEY_AGE",user_age.getText().toString());
                    bundle.putString("KEY_NUM",user_num.getText().toString());
                    intent.setClass(MainActivity.this, Login.class);
                    intent.putExtras(bundle);
                    startActivity(intent);
                }
                else{
                    new AlertDialog.Builder(MainActivity.this).setMessage("登录失败")
                            .setPositiveButton("确定", new DialogInterface.OnClickListener() {

                                @Override
                                public void onClick(DialogInterface dialog, int which) {
                                    // TODO Auto-generated method stub
                                    user_name.setText("");
                                    user_pwd.setText("");
                                    user_name.requestFocus();
                                }
                            }).show();
                }
            }
        });

        button_regist.setOnClickListener(new OnClickListener(){
            public void onClick(View v){
                if(db.adduser(user_name.getText().toString(), user_pwd.getText().toString(),user_un.
                        getText().toString(),user_age.getText().toString(),user_num.getText().toString())!=-1)
                {
                    new AlertDialog.Builder(MainActivity.this).setMessage("注册成功")
                            .setPositiveButton("确定", new DialogInterface.OnClickListener() {

                                @Override
                                public void onClick(DialogInterface dialog, int which) {
                                    // TODO Auto-generated method stub
                                    Intent intent=new Intent();
                                    Bundle bundle=new Bundle();
                                    bundle.putString("KEY_NAME",user_name.getText().toString());
                                    intent.setClass(MainActivity.this, Login.class);
                                    intent.putExtras(bundle);
                                    //startActivity(intent);
                                }
                            }).show();
                }
                else{
                    new AlertDialog.Builder(MainActivity.this).setMessage("用户名已占用")
                            .setPositiveButton("确定", new DialogInterface.OnClickListener() {

                                @Override
                                public void onClick(DialogInterface dialog, int which) {
                                    // TODO Auto-generated method stub
                                    user_name.setText("");
                                    user_pwd.setText("");
                                    user_un.setText("");
                                    user_age.setText("");
                                    user_num.setText("");
                                    user_name.requestFocus();
                                }
                            }).show();
                }

            }
        });
    }

}