package com.example.todolist;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.GridView;
import android.widget.TextView;

public class Grid_View extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_grid_view);

        String []arr=getResources().getStringArray(R.array.grid);
        ArrayAdapter<String>adapter=new ArrayAdapter<String>(this,android.R.layout.simple_list_item_1,arr);
        GridView myGrid=(GridView) findViewById(R.id.GridV);
        myGrid.setAdapter(adapter);

        Button back=(Button)findViewById(R.id.back1);

        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent x=new Intent(Grid_View.this,MainActivity.class );
                startActivity(x);
            }
        });


        myGrid.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {

                TextView v=(TextView)view;
                String s=v.getText().toString();


                if(Character.isLowerCase(s.charAt(0))){

                    v.setText(s.toUpperCase());
                }else if (Character.isUpperCase(s.charAt(0))){

                    v.setText(s.toLowerCase());


                }

            }
        });


    }
}