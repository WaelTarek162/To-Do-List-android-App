package com.example.todolist;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.os.PersistableBundle;
import android.view.ContextMenu;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {


    final ArrayAdapter<String>my_arr=new ArrayAdapter<String>(this ,  android.R.layout.simple_list_item_1);

    final ToDoDbHelper newDb=new ToDoDbHelper(this);

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        ListView mylist=(ListView)findViewById(android.R.id.list);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        setContentView(R.layout.activity_showing);



        Button grid_btn=(Button)findViewById(R.id.GridViewBtn);
        Button btn=(Button)findViewById(R.id.b1);

        ToDoDbHelper newDb1;
        newDb1=new ToDoDbHelper(getApplicationContext());
        Cursor cursor =newDb1.fitchActivities();


        registerForContextMenu(mylist);
        while (!cursor.isAfterLast()){


            my_arr.add(cursor.getString(0));
            cursor.moveToNext();
        }

        mylist.setAdapter(my_arr);







        btn.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {

                EditText txt=(EditText)findViewById(R.id.t1);
                String val=txt.getText().toString();
                my_arr.add(val);
                newDb.createTime(val);
                Toast.makeText(getApplication(),"Added!",Toast.LENGTH_LONG).show();
                txt.getText().clear();

            }
        });

        mylist.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                Toast.makeText(getApplicationContext(),((TextView)view).getText().toString(),
                        Toast.LENGTH_LONG).show();
            }
        });

        grid_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i=new Intent(MainActivity.this, showing.class);
                startActivity(i);
            }
        });


    }



    /////////////////////////////////////////////////////


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater=new MenuInflater(this);
        inflater.inflate(R.menu.menu1,menu);

        getMenuInflater().inflate(R.menu.menu1,menu);

        return super.onCreateOptionsMenu(menu);
    }


    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {

        TextView t=(TextView)findViewById(R.id.ment);
        if(item.getItemId()==R.id.subitem1){
            t.setText("item1 Added!");
            return true;
        }else if(item.getItemId()==R.id.subitem2){
            t.setText("item2 Added!");
            return true;
        }else if(item.getItemId()==R.id.subitem11){
            t.setText("item 1.1 Added!");
            return true;
        }else if(item.getItemId()==R.id.subitem12){
            t.setText("item 1.2 Added!");
            return true;
        }else if(item.getItemId()==R.id.subitem13){
            t.setText("item 1.3 Added!");
            return true;
        }

        return super.onOptionsItemSelected(item);
    }




    @Override
    public boolean onContextItemSelected( MenuItem item) {
       AdapterView.AdapterContextMenuInfo info=(AdapterView.AdapterContextMenuInfo)item.getMenuInfo();
       String selection= ((TextView)info.targetView).getText().toString();
       int id=item.getItemId();
       if(id==R.id.itemDelete){

           my_arr.remove(selection);
           newDb.deleteTime(selection);
           return true;

       }else if(id==R.id.itemUpDate){

           Intent i=new Intent(MainActivity.this,Grid_View.class);
           i.putExtra("activity",selection);
           startActivity(i);
           return true;


       }

        return super.onContextItemSelected(item);
    }
    ///////////////////


    /*
    @Override
    public void onCreateContextMenu(ContextMenu menu, View v, ContextMenu.ContextMenuInfo menuInfo) {
        super.onCreateContextMenu(menu, v, menuInfo);

        MenuInflater mi=new MenuInflater(this);
        mi.inflate(R.menu.menu1,menu);
    }

   */
    //////////////////////////////////////////////


    @Override
    public void onCreateContextMenu(ContextMenu menu,View v,ContextMenu.ContextMenuInfo menuInfo) {
        getMenuInflater().inflate(R.menu.contextmmenu,menu);
        super.onCreateContextMenu(menu,v,menuInfo);
    }


}
