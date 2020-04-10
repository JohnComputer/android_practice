package com.example.project_03_03;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

public class ActEdit extends AppCompatActivity {
    EditText mEdit; // EditText 불러오기위해 선언

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_act_edit);
        mEdit = (EditText) findViewById(R.id.stredit);  // EditText id : stredit 가져오기.

        Intent intent = getIntent();    // intent 객체 생성 가져오기.
        String text = intent.getStringExtra("TextIn");
        if (text != null) {
            mEdit.setText(text);    // mEdit에 가져온 텍스트 셋.
        }
    }

    public void mOnClick(View v) {
        // mOnclick을 호출매소드로 가지고있는 버튼 클릭시 호출. getId로 id 가져오기.
        switch (v.getId()) {

            case R.id.btnok:
                // OK버튼일 경우 TextOut이름에 Value 저장 후 Result_OK값 반환.
                Intent intent = new Intent();
                intent.putExtra("TextOut", mEdit.getText().toString());
                setResult(RESULT_OK, intent);
                finish();
                break;

            case R.id.btncancel:
                // 취소 버튼의 경우 Result_Cancled 값 반환.
                setResult(RESULT_CANCELED);
                finish();

                break;

        }

    }
}

