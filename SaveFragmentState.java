package com.example.myapplication;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

public class SaveFragmentState extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_save_fragment_state);
        //activity_save_fragment_state 레이아웃 배치
    }
    // Fragment 를 상속받는 CounterFramgent 클래스 생성.
    public static class CounterFragment extends Fragment {
        @Nullable
        @Override
        public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        // 화면 생성후 호출부분
            View root = inflater.inflate(R.layout.counterfragment, container,false);
            // inflate 메서드를 이용하여 counterfragment로 만들어진 fragment가져와서 새로운 뷰 생성.
            Button btnincrease = (Button) root.findViewById(R.id.btnincrease);
            // root이름의 view에서 btnincrease 가져오기.

            final TextView textCounter = (TextView) root.findViewById(R.id.txtcounter);
            // root이름의 view에서 txtcounter 가져오기.

            if (savedInstanceState != null) {
                textCounter.setText(Integer.toString(savedInstanceState.getInt("counter")));
                // textcounter에 값이 있는지 확인.
            }

            btnincrease.setOnClickListener(new Button.OnClickListener() {
            // 버튼 이벤트
                @Override
                public void onClick(View v) {
                // 버튼 클릭시 숫자 증가.
                    int count = Integer.parseInt(textCounter.getText().toString());
                    textCounter.setText(Integer.toString(count + 1));
                }
            });
            return root;
            // root view 리턴
        }

        @Override
        public void onSaveInstanceState(@NonNull Bundle outState) {
            // 화면전환을 위한 콜백 메소드 생성.
            // textcounter 값 가져와서 counter키에 저장
            super.onSaveInstanceState(outState);
            TextView textCounter=(TextView)getView().findViewById(R.id.txtcounter);
            int a = Integer.parseInt(textCounter.getText().toString());
            outState.putInt("counter",a);
        }
    }
}
