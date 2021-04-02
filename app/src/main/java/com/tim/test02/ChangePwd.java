package com.tim.test02;
import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class ChangePwd extends Activity{

    private EditText pwd;
    private EditText repwd;
    private TextView user_name;
    private Button button_ok;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        // TODO Auto-generated method stub
        super.onCreate(savedInstanceState);
        this.setContentView(R.layout.updata);
        findViews();
        setListeners();
        showMsg();
    }

    private void showMsg() {
        // TODO Auto-generated method stub
        Bundle bundle=this.getIntent().getExtras();
        user_name.setText(bundle.getString("KEY_NAME"));
    }

    private void setListeners() {
        // TODO Auto-generated method stub
        button_ok.setOnClickListener(new OnClickListener(){

            @Override
            public void onClick(View v) {
                // TODO Auto-generated method stub
                if(pwd.getText().toString().equals(repwd.getText().toString())){
                    DataBase db=new DataBase(ChangePwd.this);
                    db.open();
                    if(db.update(user_name.getText().toString(), pwd.getText().toString())){
                        new AlertDialog.Builder(ChangePwd.this).setMessage("修改密码成功！")
                                .setPositiveButton("确定", new DialogInterface.OnClickListener() {

                                    @Override
                                    public void onClick(DialogInterface dialog, int which) {
                                        // TODO Auto-generated method stub
                                        setResult(RESULT_OK);
                                        finish();
                                    }
                                }).show();
                    }
                }
                else{
                    new AlertDialog.Builder(ChangePwd.this).setMessage("确认密码不正确！")
                            .setPositiveButton("确定", new DialogInterface.OnClickListener() {

                                @Override
                                public void onClick(DialogInterface dialog, int which) {
                                    // TODO Auto-generated method stub

                                }
                            }).show();
                    pwd.setText("");
                    repwd.setText("");
                    pwd.requestFocus();
                }
            }

        });
    }

    private void findViews() {
        // TODO Auto-generated method stub
        pwd=(EditText)findViewById(R.id.pwd);
        repwd=(EditText)findViewById(R.id.repwd);
        user_name=(TextView)findViewById(R.id.user_name);
        button_ok=(Button)findViewById(R.id.button_ok);
    }

}
