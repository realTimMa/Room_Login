package com.tim.test02;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class Login extends AppCompatActivity {
    private TextView user_name;
    private TextView user_pwd;
    private TextView user_un;
    private TextView user_age;
    private TextView user_num;

    protected static final int MENU_DELETE=Menu.FIRST;
    protected static final int MENU_CHANGEPWD=Menu.FIRST+1;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        // TODO Auto-generated method stub
        super.onCreate(savedInstanceState);
        this.setContentView(R.layout.display);
        findviews();
        showMsg();

    }

    private void showMsg() {
        // TODO Auto-generated method stub
        Bundle bundle=this.getIntent().getExtras();
        System.out.println("用户名："+bundle.getString("KEY_NAME"));
        System.out.println("密码"+bundle.getString("KEY_PWD"));
        System.out.println("姓名"+bundle.getString("KEY_UN"));
        System.out.println("年龄"+bundle.getString("KEY_AGE"));
        System.out.println("电话"+bundle.getString("KEY_NUM"));
        user_name.setText(bundle.getString("KEY_NAME"));
        user_pwd.setText(bundle.getString("KEY_PWD"));
        user_un.setText(bundle.getString("KEY_UN"));
        user_age.setText(bundle.getString("KEY_AGE"));
        user_num.setText(bundle.getString("KEY_NUM"));

    }

    private void findviews() {
        // TODO Auto-generated method stub
        user_name=(TextView)findViewById(R.id.user);
        user_pwd=(TextView)findViewById(R.id.password);
        user_un=(TextView)findViewById(R.id.uname);
        user_age=(TextView)findViewById(R.id.age);
        user_num=(TextView)findViewById(R.id.num);

    }



//    @Override
//    public boolean onCreateOptionsMenu(Menu menu) {
//        // TODO Auto-generated method stub
////        super.onCreateOptionsMenu(menu);
////        menu.add(0,MENU_DELETE,1,"删除");
////        menu.add(0,MENU_CHANGEPWD,2,"修改密码");
////        menu.add(0,MENU_CHANGEPWD,3,"修改年龄");
////        menu.add(0,MENU_CHANGEPWD,4,"修改手机号码");
//        getMenuInflater().inflate(R.menu.options_menu, menu);
//        return true;
//    }
@Override
public boolean onCreateOptionsMenu(Menu menu) {
    //加载菜单资源
    getMenuInflater().inflate(R.menu.options_menu,menu);
    return super.onCreateOptionsMenu(menu);
}
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // TODO Auto-generated method stub

        if(item.getItemId() == R.id.MENU_DELETE){
            DataBase db=new DataBase(this);
            db.open();
            if(db.delete(user_name.getText().toString())){
                setResult(RESULT_OK);
                finish();
            }
        }
        if(item.getItemId() == R.id.MENU_CHANGEPWD){
            Intent intent=new Intent();
            Bundle bundle=new Bundle();
            bundle.putString("KEY_NAME",user_name.getText().toString());
            intent.setClass(Login.this, ChangePwd.class);
            intent.putExtras(bundle);
            startActivity(intent);
        }
//        switch(item.getItemId()){
//            case MENU_DELETE:
//                DataBase db=new DataBase(this);
//                db.open();
//                if(db.delete(user_name.getText().toString())){
//                    setResult(RESULT_OK);
//                    finish();
//                }
//                break;
//            case MENU_CHANGEPWD:
//                Intent intent=new Intent();
//                Bundle bundle=new Bundle();
//                bundle.putString("KEY_NAME",user_name.getText().toString());
//                intent.setClass(Login.this, ChangePwd.class);
//                intent.putExtras(bundle);
//                startActivity(intent);
//                break;
//
//        }
//
        return super.onOptionsItemSelected(item);
    }


}
