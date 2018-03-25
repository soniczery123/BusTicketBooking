package com.example.busticketbooking;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    TextView queue;
    Button seat1, seat2, seat3;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        final Spinner dropdownGender = findViewById(R.id.dropDownGender);
        final Spinner dropdownSeat = findViewById(R.id.dropDownSeat);
        Button bookButton = findViewById(R.id.buttonBook);
        Button deleteButton = findViewById(R.id.buttonDelete);
        queue = findViewById(R.id.queue);
        seat1 = findViewById(R.id.seat1);
        seat2 = findViewById(R.id.seat2);
        seat3 = findViewById(R.id.seat3);
        final EditText editName = findViewById(R.id.editName);
        final EditText editSurname = findViewById(R.id.editSurname);
        final EditText editAge = findViewById(R.id.editAge);
        final ArrayList<Customer> arrCus = new ArrayList<>();

        String[] itemGender = new String[]{"Male", "Female"};
        String[] itemSeat = new String[]{"S1", "S2", "S3"};
        final ArrayAdapter<String> adapter1 = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_dropdown_item, itemGender);
        dropdownGender.setAdapter(adapter1);
        ArrayAdapter<String> adapter2 = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_dropdown_item, itemSeat);
        dropdownSeat.setAdapter(adapter2);

        bookButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String name = editName.getText().toString();
                String surname = editSurname.getText().toString();
                String ageStr = editAge.getText().toString();
                int age = Integer.parseInt(ageStr);
                String gender = dropdownGender.getSelectedItem().toString();
                String seat = dropdownSeat.getSelectedItem().toString();
                if (checkQueue(arrCus, seat)) {
                    arrCus.add(new Customer(name, surname, gender, age, seat));
                    setQueue(arrCus);
                } else {
                    Toast.makeText(MainActivity.this, "Please book another seat", Toast.LENGTH_SHORT).show();
                }
            }
        });
        //ลบที่เลือกจาก dropdown แทน //ขก.ทำ
        deleteButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String seat = dropdownSeat.getSelectedItem().toString();
                setSeatColor(seat,"delete");
                for (int i = 0; i < arrCus.size(); i++) {
                    if (arrCus.get(i).getSeat().toString().equals(seat)) {
                        arrCus.remove(i);
                        break;
                    }
                }
                setQueue(arrCus);
            }
        });

    }

    void setSeatColor(String seat, String order) {
        if (order.equals("book")) {
            if (seat.equals("S1")) {
                seat1.setBackgroundResource(R.color.colorRed);
            } else if (seat.equals("S2")) {
                seat2.setBackgroundResource(R.color.colorRed);
            } else {
                seat3.setBackgroundResource(R.color.colorRed);
            }
        } else {
            if (seat.equals("S1")) {
                seat1.setBackgroundResource(R.color.colorGrey);
            } else if (seat.equals("S2")) {
                seat2.setBackgroundResource(R.color.colorGrey);
            } else {
                seat3.setBackgroundResource(R.color.colorGrey);
            }
        }

    }

    boolean checkQueue(ArrayList<Customer> arrCus, String seat) {
        setSeatColor(seat,"book");
        for (int i = 0; i < arrCus.size(); i++) {
            if (arrCus.get(i).getSeat().toString().equals(seat)) {
                return false;
            }
        }
        return true;
    }

    //ขก.ทำตารางเ ทำเป็น string ธรรมดาแทน
    void setQueue(ArrayList<Customer> arrCus) {
        String queueStr = "";
        for (int i = 0; i < arrCus.size(); i++) {
            queueStr += arrCus.get(i).getName().toString() + " " + arrCus.get(i).getSurname().toString() + " " +
                    arrCus.get(i).getGender().toString() + " " + arrCus.get(i).getAge() + " "
                    + arrCus.get(i).getSeat().toString() + "\n";
        }
        queue.setText(queueStr);
    }
}
