package com.example.farmproduce;

import android.os.Bundle;
import android.view.View;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import java.text.NumberFormat;

public class MainActivity extends AppCompatActivity {
    int i = 1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }


    private void display(int i) {
        TextView quantityTextView = findViewById(R.id.quantity_textview);
        quantityTextView.setText("" + i);
    }

    public void decrement(View view) {
        if (i == 1) {
            i = 1;
            Toast.makeText(MainActivity.this, getString(R.string.less_than_1), Toast.LENGTH_SHORT).show();
        }
        else
            i--;
        display(i);
    }

    public void increment(View view) {
        i++;
        display(i);
    }


    public void submit(View view) {
        EditText name = (EditText)findViewById(R.id.name_editText);
        String userName = name.getText().toString();

        CheckBox bagCheck = (CheckBox)findViewById(R.id.bag_checkBox);
        CheckBox boxCheck = (CheckBox)findViewById(R.id.box_checkBox);
        boolean hasbag = bagCheck.isChecked();
        boolean hasbox = boxCheck.isChecked();

        displayMessage(createSummary(userName, hasbag, hasbox));
    }



    private void displayMessage(String s) {
        TextView priceTextView = findViewById(R.id.price_textview);
        priceTextView.setText(s);
    }

    private double calculatePrice(boolean hasBag, boolean hasBox) {
        double basePrice = 20;
        if (hasBag)
            basePrice += 0.5;
        if (hasBox)
            basePrice += 1.0;

        double you = 1;
        if (i >= 3)
            you = 0.8;
        return i * basePrice * you;
    }

    private String createSummary(String userName, boolean hasBag, boolean hasBox) {
        String s = getString(R.string.order_summary_name, userName);
        s += "\n" + getString(R.string.order_summary_quantity, i);
        s += "\n" + getString(R.string.order_summary_bag, hasBag);
        s += "\n" + getString(R.string.order_summary_box, hasBox);
        s += "\n" + getString(R.string.order_summary_price, NumberFormat.getCurrencyInstance().format(calculatePrice(hasBag, hasBox)));
        return s;
    }

}






