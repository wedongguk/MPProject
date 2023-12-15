package com.example.mp_project;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Toast;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Calendar;

public class WriteActivity extends AppCompatActivity{
    Button button;
    DatePicker datePicker;
    EditText editText;
    String filename;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_write);
        setTitle("축구 일기 쓰기");

        button = findViewById(R.id.button);
        datePicker = findViewById(R.id.datepicker);
        editText = findViewById(R.id.edit_text);


        Calendar cal = Calendar.getInstance();
        int year = cal.get(Calendar.YEAR);
        int month = cal.get(Calendar.MONTH);
        int day = cal.get(Calendar.DAY_OF_MONTH);



        datePicker.init(year, month, day, new DatePicker.OnDateChangedListener() {
            @Override
            public void onDateChanged(DatePicker datePicker, int i, int i1, int i2) {

                filename = Integer.toString(i) + "_" + Integer.toString(i1+1) + "_" + Integer.toString(i2);
                String str = diaryRead(filename);
                editText.setText(str);
                button.setEnabled(true);


            }
        });

        button.setOnClickListener(new Button.OnClickListener() {
            @Override
            public void onClick(View view) {
                try {
                    FileOutputStream outFs = openFileOutput(filename, Context.MODE_PRIVATE);
                    String str = editText.getText().toString();
                    outFs.write(str.getBytes());
                    outFs.close();
                    Toast.makeText(WriteActivity.this, filename + "일기 저장", Toast.LENGTH_SHORT).show();
                    button.setText("수정");

                } catch (IOException e) {
                    e.printStackTrace();
                }

            }
        });



    }

    String diaryRead(String filename){

        String diaryStr = null;
        FileInputStream inFs;
        try {
            inFs = openFileInput(filename);

            byte[] txt = new byte[500];
            inFs.read(txt);
            inFs.close();
            diaryStr = (new String(txt)).trim();
            button.setText("수정");


        }catch (IOException e){  // 파일이 없는 경우
            editText.setHint("일기 없음");
            button.setText("새로 저장");

        }

        return diaryStr;
    }
}
