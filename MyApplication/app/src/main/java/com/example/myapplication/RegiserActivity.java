package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class RegiserActivity extends AppCompatActivity {

    TextView output;
    EditText name;
    EditText id;
    EditText password;
    Button register;
    List<Student> students;
    ArrayList<String> ids = new ArrayList();

    nodeApi nodeApi;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        output = findViewById(R.id.registerView);

        name = findViewById(R.id.studentnameText);
        id = findViewById(R.id.studentidText);
        password = findViewById(R.id.studentpasswordText);

        register = findViewById(R.id.buRegisterAccount);

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(getString(R.string.ip_address))
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        nodeApi = retrofit.create(nodeApi.class);

        Call<List<Student>> getCall = nodeApi.getStudents();

        getCall.enqueue(new Callback<List<Student>>() {
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


        register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if(!validateName(name.getText().toString()))
                    output.setText("Please enter a name");
                else
                    if(!validateID(id.getText().toString()))
                        output.setText("Please enter a valid student id");
                    else
                        if(!validatepassword(password.getText().toString()))
                            output.setText("Password should contain 6 or more characters");
                        else {
                            createStudent(name.getText().toString(),id.getText().toString(),password.getText().toString());
                            output.setText("hada5alak");
                        }

            }
        });

        //;




    }

    private boolean validateName(String s){
        if(s.length()==0||s==null)
            return false;
        else
            return true;
    }

    private boolean validatepassword(String s){
        if(s.length()<6)
            return false;
        else return true;
    }

    private boolean validateID(String s){
        int dashes =0;
        for(String id:ids){
            if(id.equals(s))
                return false;
        }
        if(s.length()<3){
            return false;
        }
        for(int i=0;i<s.length();i++){
            if(s.charAt(0)=='-')
                return false;
            if(s.charAt(s.length()-1)=='-')
                return false;
            if(s.charAt(i)!='0'&&s.charAt(i)!='1'&&s.charAt(i)!='2'&&s.charAt(i)!='3'&&s.charAt(i)!='4'
            &&s.charAt(i)!='5'&&s.charAt(i)!='6'&&s.charAt(i)!='7'&&s.charAt(i)!='8'
            &&s.charAt(i)!='9'&&s.charAt(i)!='-')
                return  false;
            if(s.charAt(i)=='-')
                dashes++;

        }
        if(dashes==1)
        return true;
        else return false;
    }

    private void createStudent(String name, String id, String password) {
       Student student = new Student(name,id,password);
       Call<Student> call = nodeApi.createStudent(student);
       call.enqueue(new Callback<Student>() {
           @Override
           public void onResponse(Call<Student> call, Response<Student> response) {

               if(!response.isSuccessful()) {
                   output.setText("Code: " + response.code());
                   return;
               }

               output.setText("Your account was successfully registered!");
           }

           @Override
           public void onFailure(Call<Student> call, Throwable t) {
            output.setText(t.getMessage());
           }
       });

    }
}
