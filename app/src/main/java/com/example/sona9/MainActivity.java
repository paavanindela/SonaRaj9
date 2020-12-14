package com.example.sona9;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;

import com.example.sona9.Model.Articles;
import com.example.sona9.Model.Headlines;
import com.google.gson.JsonObject;

import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

import javax.net.ssl.HttpsURLConnection;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class MainActivity extends AppCompatActivity {

    private RecyclerView recyclerView;
    private RecyclerView.Adapter recyclerViewAdapter;
    private RecyclerView.LayoutManager nLayout;
    private  String _url = "http://newsapi.org/v2/top-headlines?" +"country=us&" + "apiKey=7c07df0406db445e91dd1679d964a5b8";
    ArrayList<News> news = new ArrayList<>();
    //private static Retrofit retrofit = null;
    List<Articles> articles = new ArrayList<>();
    final String Api = "7c07df0406db445e91dd1679d964a5b8";
    String country = "in";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        GetData();
        //news.add(new News("Hello"));
        //news.add(new News("dvsdvsv"));
        recyclerView = findViewById(R.id.recyclerView);
        recyclerView.setHasFixedSize(true);
        nLayout = new LinearLayoutManager(this);
        //recyclerViewAdapter = new NewsAdapter(news);
        recyclerView.setLayoutManager(nLayout);
        //recyclerView.setAdapter(recyclerViewAdapter);
    }
    void GetData(){
        Call<Headlines> call;
        call = ApiClient.getInstance().getApi().getHeadlines(country,Api);
        call.enqueue(new Callback<Headlines>() {
            @Override
            public void onResponse(Call<Headlines> call, Response<Headlines> response) {
                if (response.isSuccessful() && response.body().getArticles() != null){
                    articles.clear();
                    articles = response.body().getArticles();
                    for(int i = 0;i<articles.size();++i){
                        news.add(new News(articles.get(i).getTitle()));
                    }
                    recyclerViewAdapter = new NewsAdapter(news);
                    recyclerView.setAdapter(recyclerViewAdapter);
                }
            }

            @Override
            public void onFailure(Call<Headlines> call, Throwable t) {

                Toast.makeText(MainActivity.this, t.getLocalizedMessage(), Toast.LENGTH_SHORT).show();
            }
        });
        /*Thread thread = new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    URL url = new URL(_url);
                    HttpURLConnection conn = (HttpURLConnection) url.openConnection();
                    //conn.setRequestMethod("GET");Reyy idi vere format kada ante json kaad kad
                    conn.setDoOutput(true);
                    conn.setDoInput(true);
                    Log.i("STATUS", String.valueOf(conn.getResponseCode()));
                    Log.i("MSG" , conn.getResponseMessage());
                    BufferedReader br = new BufferedReader(new InputStreamReader((conn.getInputStream())));
                    StringBuilder sb = new StringBuilder();
                    String output;
                    while ((output = br.readLine()) != null) {
                        sb.append(output);
                    }
                    Log.d("JSON", sb.toString());
                    JSONObject json = new  JSONObject(sb.toString());
                    conn.disconnect();
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
        thread.start();*/
    }

}