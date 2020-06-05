package com.kang.bingdari;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.text.InputFilter;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import java.util.Random;

public class PlayActivity extends AppCompatActivity {

    private int num;
    private LinearLayout ll;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_play);

        TextView tv1 = (TextView)findViewById(R.id.tv_play_num);
        Button btnPlay = (Button)findViewById(R.id.btn_play_go);
        ll = (LinearLayout)findViewById(R.id.ll_et);

        Intent intent = getIntent();
        String data = intent.getStringExtra("num");
        tv1.setText("갯수 : "+data);

        num = Integer.parseInt(data.toString());

        for (int i=0;i<num;i++) {

            EditText et = new EditText(getApplicationContext());
            LinearLayout.LayoutParams p = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);
            p.setMargins(0,15,0,0);
            et.setLayoutParams(p);
            et.setGravity(1);
            et.setHint("editText" + i + "번");
            et.setFilters(new InputFilter[] { new InputFilter.LengthFilter(10) });

            et.setId(num);
            ll.addView(et);
        }
        btnPlay.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AlertDialog.Builder ad = new AlertDialog.Builder(PlayActivity.this);

                ad.setTitle("결과");
                // TextView 삽입하기
                Random rnd = new Random();
                int rndNum = rnd.nextInt(num);
                EditText et = (EditText) ll.getChildAt(rndNum);

                final TextView tv = new TextView(PlayActivity.this);

                tv.setText(et.getText());
                ad.setView(tv);

                // 확인 버튼 설정
                ad.setPositiveButton("홈으로 가기", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        // Text 값 받아서 로그 남기기
                        Intent intent = new Intent(PlayActivity.this,MainActivity.class);
                        dialog.dismiss();
                        startActivity(intent);
                    }
                });
                // 취소 버튼 설정
                ad.setNegativeButton("다시하기", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.dismiss();     //닫기
                        // Event
                    }
                });
                ad.show();

            }
        });
    }
}
