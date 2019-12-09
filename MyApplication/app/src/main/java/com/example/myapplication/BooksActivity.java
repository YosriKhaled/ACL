package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class BooksActivity extends AppCompatActivity {

    TextView output;
    ListView bookList;
    ArrayList<String> booksNames = new ArrayList<>();
    ArrayList<Boolean> booksAvailability = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_books);


        output = findViewById(R.id.registerView);
        bookList = findViewById(R.id.listvView);

        Retrofit retrofit = new Retrofit.Builder()

                .baseUrl(getString(R.string.ip_address))
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        nodeApi nodeApi = retrofit.create(nodeApi.class);

        final Call<List<Book>> call = nodeApi.getBooks();

        final ArrayAdapter arrayAdapter = new ArrayAdapter(this,android.R.layout.simple_list_item_1,booksNames);

        call.enqueue(new Callback<List<Book>>() {
            @Override
            public void onResponse(Call<List<Book>> call, Response<List<Book>> response) {

                if (!response.isSuccessful()) {
                    output.setText("Code: " + response.code());

                    return;
                }
                List<Book> books = response.body();



                for (Book book : books) {

                    booksAvailability.add(book.getAvailability());
                    booksNames.add(book.getName());

                    //output.append(content);
                }
                bookList.setAdapter(arrayAdapter);
                bookList.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                    @Override
                    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                        String s = "";
                        if(booksAvailability.get(position)==true)
                            s = "Available";
                        else
                            s = "Not available";
                        Toast.makeText(BooksActivity.this,s,Toast.LENGTH_SHORT).show();
                    }
                });




            }

            @Override
            public void onFailure(Call<List<Book>> call, Throwable t) {
                output.setText(t.getMessage());
            }
        });

    }
}
