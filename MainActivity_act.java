package com.example.project_03_03;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {
    TextView mText;     // TextView 받아오기 위해 생성
    final static int ACT_EDIT = 0;  // 클릭 리스너에서 호출 위해 생성.

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mText = (TextView)findViewById(R.id.text);  // TextView 타입의 id text를 mText 에 저장.
    }
    public void mOnClick(View v) {
        // 버튼이 눌려졌을때 view의 아이디를 가져옴(getId)
        switch (v.getId()) {

            case R.id.btnedit:
                // intent 객체 생성 현재의 페이지에서 ActEdit 클래스로 넘겨줌. 보내줄 값은 TextIn이름으로 mText의 텍스트전달
                Intent intent = new Intent(this, ActEdit.class);
                intent.putExtra("TextIn", mText.getText().toString());
                startActivityForResult(intent,ACT_EDIT);    // 호출한 값 돌려 받기.
                break;
        }

    }


    protected void onActivityResult (int requestCode, int resultCode, Intent data) {
        // 호출 후 돌려받은 값이 RESULT_OK인 경우 텍스트 수정.
        switch (requestCode) {
            case ACT_EDIT:
                if (resultCode == RESULT_OK) {
                    mText.setText(data.getStringExtra("TextOut"));  // mText에 TextOut이름으로 가져온 값 세팅.
                }
                break;

        }

    }

}
