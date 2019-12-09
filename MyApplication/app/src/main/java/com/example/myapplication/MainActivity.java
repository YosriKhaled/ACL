package com.example.myapplication;


import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;



import java.util.ArrayList;
import java.util.List;


import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class MainActivity extends AppCompatActivity {

    TextView  output;
    EditText id;
    EditText password;
    Button logIn;
    Button register;
    List<Student> students;
    ArrayList<String> ids = new ArrayList();





    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        id = findViewById(R.id.idText);
        password = findViewById(R.id.passwordText);

        output = findViewById(R.id.loginView);

        logIn = findViewById(R.id.buLogIn);
        register = findViewById(R.id.buRegister);



        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(getString(R.string.ip_address))
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        nodeApi nodeApi = retrofit.create(nodeApi.class);

        final Call<List<Student>> call = nodeApi.getStudents();

        call.enqueue(new Callback<List<Student>>() {
            @Override
            public void onResponse(Call<List<Student>> call, Response<List<Student>> response) {
                if (!response.isSuccessful()) {
                    output.setText("Code: " + response.code());
                    return;
                }
                students = response.body();
                for (Student student : students) {

                    ids.add(student.getStudentId());
                }
            }
            @Override
            public void onFailure(Call<List<Student>> call, Throwable t) {
                output.setText(t.getMessage());
            }
        });

        logIn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if (ids.contains(id.getText().toString())) {
                    for(Student student : students){
                        if(student.getStudentId().equals(id.getText().toString()))
                            if(student.getPassword().equals(password.getText().toString()))
                                openbooksActivity();
                            else
                                output.setText("Incorrect password");
                    }
                }
                else
                    output.setText("Incorrect id");

            }
        });

        register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openregisterActivity();
            }
        });


    }



    private void openbooksActivity() {

        Intent intent = new Intent(this, BooksActivity.class);
        startActivity(intent);

    }

    private void openregisterActivity() {

        Intent intent = new Intent(this, RegiserActivity.class);
        startActivity(intent);

    }

}

