package com.example.actionbar;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.Window;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
//        requestWindowFeature(Window.FEATURE_ACTION_BAR);
        TextView text = new TextView(this);
        text.setText("액션바를 테스트합니다.");
        setContentView(text);
    }
    // 옵션메뉴를 생성합니다. Menu객체의 menu 가져오기
    public boolean onCreateOptionsMenu(Menu menu) {
        super.onCreateOptionsMenu(menu);
        MenuInflater inflater = getMenuInflater(); //menuInflater 객체화할수있는 메뉴 전개자 생성.
        inflater.inflate(R.menu.actionbarmenu, menu); // R.menu.actionbarmenu 가져와서 actionbarmenu menu에 붙여넣기.

        return true;
    }
}
