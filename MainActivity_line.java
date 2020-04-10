package com.example.project_03_01;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.os.Bundle;
import android.view.MotionEvent;
import android.view.View;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    private MyView vw;
    // 정점 하나에 대한 정보를 가지는 클래스
    public class Vertex {
        Vertex(float ax, float ay, boolean ad) {
            // X,Y는 점의 좌표 || ad(Draw) 갑이 True 이면 계속 연결 False 이면 떨어진 다른곡선 시작.
            x = ax;
            y = ay;
            Draw = ad;
        }
        float x;
        float y;
        boolean Draw;
    }
    ArrayList<Vertex> arVertex;
    // 중간 삽입 및 삭제할 일 없으므로 ArrayList 타입의 가변 컬랙션 배열 이용.

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        vw = new MyView(this); // MyView 생성
        setContentView(vw); // 생성된 Myview 적용
        arVertex = new ArrayList<Vertex>(); // ArrayList 생성

    }
    protected class MyView extends View {
        Paint mPaint;
        public MyView(Context context) {
            super(context);
            mPaint = new Paint();   // Paint 객체 미리 초기화 mPaint를 이용한 멤버 선언
            mPaint.setColor(Color.BLACK);   // Color = Black
            mPaint.setStrokeWidth(3);   // 획선의 굵기 3
            mPaint.setAntiAlias(true);  // 부드러운 모션을 위한 추가.

        }


        public void onDraw(Canvas canvas) {
            // 그리기 객체 선언
            canvas.drawColor(Color.LTGRAY);
            // 정점을 순회하면서 선분으로 잇는다.
            for (int i=0;i<arVertex.size();i++) {
                // Draw 멤버가 True인점에서 이전좌표와 현재 좌표를 연결하며 그림.
                if (arVertex.get(i).Draw) {
                    canvas.drawLine(arVertex.get(i-1).x,
                            arVertex.get(i-1).y,
                            arVertex.get(i).x,
                            arVertex.get(i).y, mPaint);
                }

            }

        }


        // 터치 이동 시마다 정점을 추가한다.
        public boolean onTouchEvent(MotionEvent event) {
            // 누른 좌표에 대한 Vertex 객체를 컬렉션에 추가
            if (event.getAction() == MotionEvent.ACTION_DOWN) {
                arVertex.add(new Vertex(event.getX(),
                        event.getY(), false));  // 누르는 과정은 새로운 곡선의 시작 False
                return true;
            }
            if (event.getAction() == MotionEvent.ACTION_MOVE) {
                arVertex.add(new Vertex(event.getX(),
                        event.getY(), true));   // MOVE 이동과정은 True로 연결
                invalidate();   // 새로 추가된선 출력 호출
                return true;
            }
            return false;
        }

    }

}

