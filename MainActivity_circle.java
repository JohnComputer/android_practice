package com.example.project_03_02;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.View;

public class MainActivity extends AppCompatActivity {
    private MyView vw;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        vw = new MyView(this);  // MyView 객체 생성
        vw.setFocusable(true);  // 키 이벤트 포커스받기
        vw.setFocusableInTouchMode(true);   // 터치모드에서 키 입력 받기
        setContentView(vw);
    }
    protected class MyView extends View {
        // mX,mY 원의 현재 좌표 || mColor 원의 색상
        float mX,mY;
        int mColor;

        public MyView(Context context) {
            // 원의 초기화작업.
            super(context);
            mX = 100;
            mY = 100;
            mColor = Color.BLUE;
        }


        public void onDraw(Canvas canvas) {
            // Canvas 생성 그림을 그리기 위한 배경같은 존재.
            canvas.drawColor(Color.LTGRAY);
            Paint Pnt = new Paint(); // 그림그리기 위한 객체 생성.
            Pnt.setColor(mColor);
            Pnt.setAntiAlias(true);
            canvas.drawCircle(mX,mY,16,Pnt); // 원 그리기.
        }


        public boolean onKeyDown(int KeyCode, KeyEvent event) {
            // 키입력 받기. KeyCode 로 눌러진 키 확인. ASCII코드값
            super.onKeyDown(KeyCode, event);

            if (event.getAction() == KeyEvent.ACTION_DOWN) {
                switch (KeyCode) {  // 키 코드 값 비교 위아래좌우 모두 5만큼 움직이도록 작성
                    case KeyEvent.KEYCODE_DPAD_LEFT:
                        mX-=5;
                        invalidate();
                        return true;
                    case KeyEvent.KEYCODE_DPAD_RIGHT:
                        mX+=5;
                        invalidate();
                        return true;
                    case KeyEvent.KEYCODE_DPAD_UP:
                        mY-=5;
                        invalidate();
                        return true;
                    case KeyEvent.KEYCODE_DPAD_DOWN:
                        mY+=5;
                        invalidate();
                        return true;
                    case KeyEvent.KEYCODE_DPAD_CENTER:
                        // 공백버튼 클릭시에 원의 색을 파란색과 빨간색을 번갈아가면서 토글.
                        if (mColor == Color.BLUE) {
                            mColor = Color.RED;
                        } else {
                            mColor = Color.BLUE;
                        }
                        invalidate();
                        return true;
                }
            }
            return false;
        }
    }
}
