package com.example.directory;

import androidx.appcompat.app.AppCompatActivity;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.RoundedCorner;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextClock;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {
    private EditText mEtName, mEtPhone;
    private TextView mTvShow;
    private Button mBtnAdd, mBtnQuery, mBtnUpdate, mBtnDelete;

    private SQLiteDatabase db;
    private MyHelper myHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        myHelper = new MyHelper(this);
        db = myHelper.getWritableDatabase();
        init();
    }

    private void init() {
        mEtName=findViewById(R.id.et_name);
        mEtPhone=findViewById(R.id.et_phone);
        mTvShow=findViewById(R.id.tv_show);
        mBtnAdd=findViewById(R.id.btn_add);
        mBtnQuery=findViewById(R.id.btn_query);
        mBtnUpdate=findViewById(R.id.btn_update);
        mBtnDelete=findViewById(R.id.btn_delete);

        mBtnAdd.setOnClickListener(this);
        mBtnQuery.setOnClickListener(this);
        mBtnUpdate.setOnClickListener(this);
        mBtnDelete.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        String name, phone;
        ContentValues values;
        switch (view.getId()) {
            case R.id.btn_add:
                name=mEtName.getText().toString();
                phone=mEtPhone.getText().toString();
                values=new ContentValues();
                values.put("name", name);
                values.put("phone", phone);
                long id=db.insert("information", null, values);
                if (id != -1) {
                    Toast.makeText(this, "信息已添加", Toast.LENGTH_SHORT).show();
                    query();
                } else {
                    Toast.makeText(this, "信息添加失败", Toast.LENGTH_SHORT).show();
                }
                break;

            case R.id.btn_delete:
                Cursor cursor2=db.query("information", null, null, null, null, null, null);
                if (cursor2.getCount() == 0)
                    Toast.makeText(this, "没有数据可删除", Toast.LENGTH_SHORT).show();
                else {
                    int delRows=db.delete("information", null, null);
                    if (delRows > 0) {
                        Toast.makeText(this, "信息已删除", Toast.LENGTH_SHORT).show();
                        mTvShow.setText("");
                    } else {
                        Toast.makeText(this, "信息删除失败", Toast.LENGTH_SHORT).show();
                    }
                }
                break;

            case R.id.btn_update:
                Cursor cursor1=db.query("information", null, null, null, null, null, null);
                if (cursor1.getCount() == 0)
                    Toast.makeText(this, "没有数据可修改", Toast.LENGTH_SHORT).show();
                else {
                    values=new ContentValues();
                    values.put("phone", phone=mEtPhone.getText().toString());
                    int rows=db.update("information", values, "name=?", new String[]{mEtName.getText().toString()});
                    if (rows > 0) {
                        Toast.makeText(this, "信息已修改", Toast.LENGTH_SHORT).show();
                        query();
                    } else {
                        Toast.makeText(this, "信息修改失败", Toast.LENGTH_SHORT).show();
                    }
                }
                break;

            case R.id.btn_query:
                query();
                break;
        }
    }

    private void query() {
        Cursor cursor=db.query("information", null, null, null, null, null, null);
        mTvShow.setText("");
        if (cursor.getCount() == 0)
            Toast.makeText(this, "没有数据", Toast.LENGTH_SHORT).show();
        else {
            while (cursor.moveToNext())
                mTvShow.append("\n" + "Name : " + cursor.getString(1) + " ;Tel : " +cursor.getString(2));
        }
        cursor.close();
    }

}