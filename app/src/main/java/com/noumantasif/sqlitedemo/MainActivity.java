package com.noumantasif.sqlitedemo;

import androidx.appcompat.app.AppCompatActivity;

import android.database.Cursor;
import android.os.Bundle;
import android.text.Editable;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
Button button_savedata;
Button button_viewData;

EditText editText_id;EditText editText_city;
EditText editText_name;
EditText editText_age;




TextView textView_viewData;
    sqliteDatabaseHelper sqlDB_Helper;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        button_savedata=findViewById(R.id.btn_Save);
        button_viewData=findViewById(R.id.btn_view);
        editText_id=findViewById(R.id.Student_id);
        editText_name=findViewById(R.id.Student_name);
        editText_age=findViewById(R.id.Student_age);
        editText_city=findViewById(R.id.Student_city);
        textView_viewData=findViewById(R.id.tv_viewData);
        sqlDB_Helper = new sqliteDatabaseHelper(this);
getSupportActionBar().hide();
     button_savedata.setOnClickListener(new View.OnClickListener() {
         @Override
         public void onClick(View v) {
           String id= editText_id.getText().toString();
             String name= editText_name.getText().toString();
             String city= editText_city.getText().toString();
            String age= editText_age.getText().toString();

             boolean isDataInsert =sqlDB_Helper.insertStudentData(id,name,city,age);

             if (isDataInsert=true){
                 Toast.makeText(MainActivity.this, "Data Entered Successfully", Toast.LENGTH_SHORT).show();
             }
             else
             {
                 Toast.makeText(MainActivity.this, "Data Not Entered", Toast.LENGTH_SHORT).show();
             }
         }
     });
        button_viewData.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                viewDataONTextView();
            }
        });


    }
public  void  viewDataONTextView(){

  String   allRecords ="";
    Cursor eachRecods_cursor = sqlDB_Helper.getStudentData();
    if(eachRecods_cursor.getCount()==0){
        Toast.makeText(this, "NO Records Exist", Toast.LENGTH_SHORT).show();
    }

     while (eachRecods_cursor.moveToNext()){
         String eachRecord;
         eachRecord="id: " + eachRecods_cursor.getString(0)+  "\n";



     eachRecord = eachRecord + "Name: " + eachRecods_cursor.getString(1) + "\n" ;
     eachRecord = eachRecord + "City: " + eachRecods_cursor.getString(2) + "\n";
     eachRecord = eachRecord + "AGE: " + eachRecods_cursor.getString(3) + "\n";
     eachRecord = eachRecord + "---------------------------------\n";
     allRecords =  allRecords + eachRecord;

     }
     textView_viewData.setText(allRecords);
}

}