package com.example.mp_project;

import android.os.AsyncTask;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;
import android.util.Log;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.mp_project.GroundAdapter;
import com.example.mp_project.Ground;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
public class GroundActivity extends AppCompatActivity {

    private static final String API_URL = "https://openapi.gg.go.kr/PublicTrainingFacilitySoccer";
    private static final String API_KEY = "aa1f91f57a9d4e17924313526d274752";

    private Button btnGetData;
    private TextView tvResult;
    private EditText editText;
    private ProgressBar progressBar;
    private RecyclerView recyclerView;
    private GroundAdapter groundAdapter;
    private List<Ground> groundList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ground);

        // UI 구성 요소 초기화
        btnGetData = findViewById(R.id.btnGetData);
        tvResult = findViewById(R.id.tvResult);
        progressBar = findViewById(R.id.progressBar);
        editText = findViewById((R.id.editText));

        // 버튼에 클릭 리스너 설정
        btnGetData.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // AsyncTask를 사용하여 API 호출 수행
                new GroundActivity.GetDataTask().execute();
            }
        });

        recyclerView = findViewById(R.id.recyclerView);
        groundList = new ArrayList<>();
        groundAdapter = new GroundAdapter(groundList);

        // RecyclerView 설정
        if (recyclerView != null) {
            Log.d("MainActivity", "RecyclerView is not null");
            if (groundAdapter != null) {
                Log.d("MainActivity", "GroundAdapter is not null");
                recyclerView.setLayoutManager(new LinearLayoutManager(this));
                recyclerView.setAdapter(groundAdapter);
            } else {
                Log.e("MainActivity", "GroundAdapter is null");
            }
        } else {
            Log.e("MainActivity", "RecyclerView is null");
        }
    }

    // API 호출을 수행하는 AsyncTask
    private class GetDataTask extends AsyncTask<Void, Void, List<Ground>> {

        @Override
        protected List<Ground> doInBackground(Void... voids) {

            try {
                String SIGUN_NM = editText.getText().toString();
                // API 키를 포함한 URL 생성
                URL url = new URL(API_URL + "?key=" + API_KEY );
                if (SIGUN_NM != null){
                    url = new URL(API_URL + "?key=" + API_KEY + "&SIGUN_NM=" + SIGUN_NM);
                }


                // URL에 연결
                HttpURLConnection urlConnection = (HttpURLConnection) url.openConnection();
                try {
                    // 연결에서 입력 스트림 가져오기
                    InputStream in = urlConnection.getInputStream();

                    // XML 파서를 사용하여 데이터를 읽고 Ground 객체의 리스트로 반환
                    List<Ground> result = parseXml(in);

                    // 로그 추가
                    Log.d("GetDataTask", "Data size: " + (result != null ? result.size() : 0));

                    return result;
                } finally {
                    // URL 연결 해제
                    urlConnection.disconnect();
                }
            } catch (IOException e) {
                // 예외 처리 및 오류 메시지 반환
                Log.e("GetDataTask", "Error fetching data", e);
                return null;
            }
        }


        // doInBackground가 완료된 후 메인 스레드에서 실행
        @Override
        protected void onPostExecute(List<Ground> result) {
            // 결과가 null이 아닌지 확인하고 RecyclerView를 업데이트
            if (result != null) {
                // RecyclerView의 데이터 업데이트
                groundList.clear();
                groundList.addAll(result);
                groundAdapter.notifyDataSetChanged();
            } else {
                // 데이터를 가져오는 중 오류가 발생한 경우 처리
                tvResult.setText("데이터를 가져오는 중 오류가 발생했습니다.");
            }
        }

        // XML을 파싱하고 Ground 객체의 리스트로 반환
        private List<Ground> parseXml(InputStream in) {
            List<Ground> groundList = new ArrayList<>();
            try {
                // XML 파서 생성
                DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
                DocumentBuilder builder = factory.newDocumentBuilder();
                Document document = builder.parse(in);

                // "item" 태그를 기준으로 NodeList를 가져오기
                NodeList itemList = document.getElementsByTagName("row");

                // NodeList를 반복하며 Ground 객체 생성
                for (int i = 0; i < itemList.getLength(); i++) {
                    Element itemElement = (Element) itemList.item(i);
                    Ground ground = new Ground(itemElement);
                    groundList.add(ground);

                    // 로그 추가
                    Log.d("parseXml", "Ground added - FacilityName: " + ground.getFacilityName() + ", PlotArea: " + ground.getPlotArea());
                }
            } catch (Exception e) {
                // XML 파싱 예외 처리
                e.printStackTrace();
                // 로그 추가
                Log.e("parseXml", "Error parsing XML", e);
            }
            return groundList;
        }

    }
}