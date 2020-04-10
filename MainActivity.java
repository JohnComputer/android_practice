package com.example.multipane;

import androidx.annotation.Nullable;

import android.app.Activity;
import android.app.Fragment;
import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.app.ListFragment;
import android.content.Intent;
import android.content.res.Configuration;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;

public class MainActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.multipaneorient); // multipaneorient 화면 배치
    }

    //  listFragment 상속 PlanetListFragment 클래스 생성
    public static class PlanetListFragment extends ListFragment {
        boolean mMultiPane; // 가로/세로 확인하기 위한 Boolean 생성. true 가로 / false 세로
        int mLastIndex = 0; // 선택한 내용 위치유지를 위한 Index 생성.

        @Override
        public void onActivityCreated(@Nullable Bundle savedInstanceState) {
            super.onActivityCreated(savedInstanceState);
            // PalnetInfo.java 원본 파일을 가져와서 하나만 선택할 수 있는 ListAdapter 생성
            setListAdapter(new ArrayAdapter<String>(getActivity(),android.R.layout.simple_list_item_activated_1, PlanetInfo.PLANET));
            getListView().setChoiceMode(ListView.CHOICE_MODE_SINGLE);   // 하나만 선택할수 있는 모드 설정


            if (savedInstanceState != null){    // 선택한것 기억하는 부분 가로세로모드 바뀔때 선택되어진 것이 있는지 확인.
                mLastIndex = savedInstanceState.getInt("lastindex");    // 저장된값 mLastIndex에 저장.
            }

            View descPanel = getActivity().findViewById(R.id.descpanel);    // View 객체 가로모드 multi 레이아웃 관리

            if(descPanel != null && descPanel.getVisibility() == View.VISIBLE){ // 만들어져있고 눈에 보인다면 Boolean값 가로지정.
                mMultiPane = true;
                onListItemClick(getListView(),null,mLastIndex,0);
            }
        }

        @Override
        public void onSaveInstanceState(Bundle outState) {
            // 화면 회전시에 라이프 사이클 / Fragment 종료시 onPause() -> onSaveInstanceState()순서로 호출 기존데이터 유지
            super.onSaveInstanceState(outState);
            outState.putInt("lastindex",mLastIndex);
        }

        @Override
        public void onListItemClick(ListView l, View v, int position, long id) {
            mLastIndex = position;
            getListView().setItemChecked(position, true);   // 선택된 위치 색상 변경

            if(mMultiPane){ // 가로모드이면
                FragmentManager fm = getFragmentManager();  // FragmentManger 관리자 객체 생성
                DescFragment df = (DescFragment)fm.findFragmentById(R.id.descpanel);    //DescFragment 객체 df 생성
                if (df == null || df.mNowIndex != position){    // 만들어 지지 않았거나 현재선택된값과 선택할값이 다르면
                    df = DescFragment.newInstance(position);    // position 값으로 새로 만든다.
                    FragmentTransaction tr = fm.beginTransaction(); // FragmentTransaction(Activity 내의 Fragment관리) 객체 생성
                    tr.replace(R.id.descpanel, df); // descpanel를 재배치
                    tr.commit();    // commit!
                }
            }else{
                Intent intent = new Intent(getActivity(), DescActivity.class);  // DescActivity로 이동
                intent.putExtra("index",position);  // position 정보값 전달
                startActivity(intent);  // 실행
            }
        }
    }
//   설명을 보여주는 프래그먼트
    public static class DescFragment extends Fragment {
        int mNowIndex;  // 현재 선택되어있는 인덱스

        public static DescFragment newInstance (int index){ // 매개변수 받아온다.
            DescFragment df = new DescFragment();
            df.mNowIndex = index;   // 현재 선택된 값의 인덱스 가져오기.
            return  df;
        }
        @Override
        public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
            View root = inflater.inflate(R.layout.descfragment, container, false); // ScrollView View그룹에 붙이기
            TextView text = (TextView)root.findViewById(R.id.txtdescription);   // TextView 가져오기
            text.setText(PlanetInfo.DESC[mNowIndex]);   // 현재선택한 Index값 가져오기.
            return root;
        }
    }

    // 설명을 보여주는 액티비티
    public static class DescActivity extends Activity{
        @Override
        protected void onCreate(@Nullable Bundle savedInstanceState) {
            super.onCreate(savedInstanceState);

            //세로 모드에서 가로로 전환하면 즉시 종료.
            if(getResources().getConfiguration().orientation == Configuration.ORIENTATION_LANDSCAPE){
                finish();
                return;
            }

            // 인텐트로 전달된 첨자의 단어를 보여준다.
            int index = getIntent().getExtras().getInt("index");    // 인덱스값 가져오기
            DescFragment details = DescFragment.newInstance(index); // 인덱스값 새로지정.
            getFragmentManager().beginTransaction().add(android.R.id.content, details).commit();    // content 공간에 추가하고 commit.
        }
    }

}
