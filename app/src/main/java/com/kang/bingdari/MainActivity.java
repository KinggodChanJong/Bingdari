package com.kang.bingdari;

import androidx.appcompat.app.AppCompatActivity;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.text.InputType;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    private Button mBtnStart, mBtnCredit;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        init();
    }

    void init(){
        mBtnStart = (Button)findViewById(R.id.btn_start);
        mBtnCredit = (Button)findViewById(R.id.btn_credit);

       View.OnClickListener listener = new View.OnClickListener(){
           @Override
           public void onClick(View v){
               switch (v.getId()){
                   case R.id.btn_start:
                       AlertDialog.Builder ad = new AlertDialog.Builder(MainActivity.this);
                       ad.setTitle("갯수를 입력하시오!");
                       // EditText 삽입하기
                       final EditText et = new EditText(MainActivity.this);
                       et.setHint("1이상 10이하로 써주세요.");
                       et.setInputType(InputType.TYPE_CLASS_NUMBER);
                       ad.setView(et);

                        // 확인 버튼 설정
                       ad.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                           @Override
                           public void onClick(DialogInterface dialog, int which) {
                               // Text 값 받아서 로그 남기기
                               String value = et.getText().toString();
                               int intValue = Integer.parseInt(value);
                               if(intValue>=1&&intValue<=10){
                                   Intent intent = new Intent(MainActivity.this,PlayActivity.class);
                                   intent.putExtra("num",value);
                                   dialog.dismiss();
                                   startActivity(intent);
                               }
                               else{
                                   Toast.makeText(MainActivity.this,"1에서 10사이로 입력해주세요",Toast.LENGTH_SHORT).show();
                               }
                           }
                       });
                        // 취소 버튼 설정
                       ad.setNegativeButton("No", new DialogInterface.OnClickListener() {
                           @Override
                           public void onClick(DialogInterface dialog, int which) {
                               dialog.dismiss();     //닫기
                               // Event
                           }
                       });
                       ad.show();
                       break;
                   case R.id.btn_credit:
                       Intent intent = new Intent(MainActivity.this,CreditActivity.class);
                       startActivity(intent);
                       break;
               }

           }
       };
       mBtnStart.setOnClickListener(listener);
       mBtnCredit.setOnClickListener(listener);
    }
}
