package com.example.myapplication;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;

public interface nodeApi {


    @GET("api/student-routes")
    Call<List<Student>> getStudents();

    @GET("api/book-routes")
    Call<List<Book>> getBooks();

    @POST("api/student-routes")
    Call<Student> createStudent(@Body Student student);




}
