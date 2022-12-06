package com.firstDiary.mydiary;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;

//메인 화면
public class MainActivity extends AppCompatActivity {

    RecyclerView mRvDiary;              // 리사이클러 뷰 (리스트 뷰)
    DiaryListAdapter mAdapter;          // 리사이클러 뷰와 연동할 어댑터
    ArrayList<DiaryModel> mLstDiary;    // 리스트에 표현할 다이어리 데이터 (배열)
    DatabaseHelper mDatabaseHelper;     // 데이터베이스 헬퍼 클래스

    @Override
    protected void onCreate(Bundle savedInstanceState) { // 액티비티가 최초 1회만 호출
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // 데이터 베이스 객체의 초기화
        mDatabaseHelper = new DatabaseHelper(this);

        mLstDiary = new ArrayList<>();

        mRvDiary = findViewById(R.id.rv_diary);

        mAdapter = new DiaryListAdapter(); // 리사이클러 뷰 어댑터 인스턴스 생성

//        // 다이어리 샘플 아이템 1개 생성
//        DiaryModel item = new DiaryModel();
//        item.setId(0);
//        item.setTitle("나는 오늘도 피곤했다.");
//        item.setContent("내용입니다.");
//        item.setUserDate("2022/10/21 Fri");
//        item.setWriteDate("2022/10/21 Fri");
//        item.setWeatherType(0);
//        mLstDiary.add(item);
//
//        DiaryModel item2 = new DiaryModel();
//        item2.setId(0);
//        item2.setTitle("나는 오늘 힘이났다.");
//        item2.setContent("내용입니다.");
//        item2.setUserDate("2022/10/22 Sat");
//        item2.setWriteDate("2022/10/22 Sat");
//        item2.setWeatherType(3);
//        mLstDiary.add(item2);

        mRvDiary.setAdapter(mAdapter); // 어댑터와 연결

        // 액티비티 (화면)이 실행될 때 가장 먼저 호출되는 곳
        FloatingActionButton floatingActionButton = findViewById(R.id.btn_write); //R.id. -> res 폴더 밑의 id 중에 btn_write
        floatingActionButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) { // 작성하기 버튼을 누를 때 호출되는 곳

                // MainActivity 에서 DiaryDetailActivity 페이지로 이동 ( 작성하기 화면으로 이동 )
                Intent intent = new Intent(MainActivity.this, DiaryDetailActivity.class);
                startActivity(intent);
            }
        });


    }

    @Override
    protected void onResume() { // onCreate는 최초 1회만 호출되기 때문에 글 작성 후 리스트를 불러와야 함
        super.onResume();
        // 액티비티의 재개

        // get load list
        setLoadRecentList();
    }

    private void setLoadRecentList() {
        // 최근 데이터베이스 정보를 가지고와서 리사이클러뷰에 갱신해준다.
        
        // 이전에 배열 리스트에 저장된 데이터가 있으면 비워버림.
        if (!mLstDiary.isEmpty()) {
            mLstDiary.clear();
        }
        
        mLstDiary = mDatabaseHelper.getDiaryListFromDB(); // 데이터베이스로부터 저장되어 있는 DB를 확인하여 가지고옴
        mAdapter.setListInit(mLstDiary);
    }
}