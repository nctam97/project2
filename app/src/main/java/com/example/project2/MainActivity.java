package com.example.project2;

import androidx.appcompat.app.AppCompatActivity;

import android.content.ContentValues;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.GridView;
import android.widget.Toast;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    static final String AUTHORITY = "com.example.project1";
    static final String CONTENT_PATH = "bookdata";
    static final String URL = "content://" + AUTHORITY + "/" + CONTENT_PATH;
    static final Uri CONTENT_URI = Uri.parse(URL);

    EditText edt_idbook, edt_title, edt_idauthor;
    Button btn_save, btn_select, btn_exit, btn_delete, btn_update;
    GridView gv_display;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        edt_idbook=(EditText) findViewById(R.id.edtNhapms);
        edt_title=(EditText) findViewById(R.id.edtNhaptd);
        edt_idauthor=(EditText) findViewById(R.id.edtNhaptentg);
        gv_display=(GridView)findViewById(R.id.grvDisplay);
        btn_save=(Button)findViewById(R.id.btnSave);
        btn_save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ContentValues values = new ContentValues();
                values.put("id_book",Integer.parseInt(edt_idbook.getText().toString()));
                values.put("title",edt_title.getText().toString());
                values.put("id_author",Integer.parseInt(edt_idauthor.getText().toString()));
                Uri insert_uri = getContentResolver().insert(CONTENT_URI,values);
                Toast.makeText(getApplicationContext(),":Lưu thành công",Toast.LENGTH_SHORT).show();
            }
        });
        btn_select=(Button)findViewById(R.id.btnSelect);
        btn_select.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ArrayList<String> list = new ArrayList<>();
                Cursor cursor = getContentResolver().query(CONTENT_URI,
                        null,null,null,"title");
                if(cursor != null){
                    cursor.moveToNext();
                    do {
                        list.add(cursor.getInt(0)+"");
                        list.add(cursor.getString(1)+"");
                        list.add(cursor.getInt(2)+"");
                    }while (cursor.moveToNext());
                    ArrayAdapter<String> adapter = new ArrayAdapter<String>(MainActivity.this,android.R.layout.simple_list_item_1,list);
                    gv_display.setAdapter(adapter);
                }
                else
                    Toast.makeText(getApplicationContext(),"Truy vấn không thành công",Toast.LENGTH_SHORT).show();
            }
        });
    }

}

