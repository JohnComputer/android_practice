package com.example.actionbar;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.View;
import android.widget.Button;

public class showhideactionbar extends AppCompatActivity {

    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_showhideactionbar);
    }

    public boolean onCreateOptionsMenu(Menu menu) {
        super.onCreateOptionsMenu(menu);
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.actionbarmenu, menu);

        return true;
    }

    public void mOnClick(View v) {
        switch (v.getId()) {
            case R.id.btntoggle:
                //  v7 appcomapt 지원 라이브러리의 ACtionBar 클래스 메서드 엑서스시 getSupportActionbar()메서드 호출 필요.
                ActionBar ab = getSupportActionBar();
                if (ab.isShowing()) {
                    ab.hide();
                    ((Button) v).setText("Show Action Bar");
                } else {
                    ab.show();
                    ((Button) v).setText("Hide Action Bar");
                }
                break;
        }
    }
}
