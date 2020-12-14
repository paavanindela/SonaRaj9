package com.example.sona9;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class NewsAdapter extends RecyclerView.Adapter<NewsAdapter.NewsView> {

    private  ArrayList<News> news;

    public static class NewsView extends RecyclerView.ViewHolder{
        public TextView text;

        public NewsView(@NonNull View itemView) {
            super(itemView);
            text = itemView.findViewById(R.id.textView);

        }
    }

    public  NewsAdapter(ArrayList<News> newsList){
        news = newsList;
    }

    @NonNull
    @Override
    public NewsView onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.activity_new,parent,false);
        NewsView nv = new NewsView(v);
        return  nv;
    }

    @Override
    public void onBindViewHolder(@NonNull NewsView holder, int position) {
        News mnews = news.get(position);
        holder.text.setText(mnews.getText());
    }

    @Override
    public int getItemCount() {
        return news.size();
    }
}
