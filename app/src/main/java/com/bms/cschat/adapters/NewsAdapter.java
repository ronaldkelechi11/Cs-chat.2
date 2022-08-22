package com.bms.cschat.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bms.cschat.R;
import com.bms.cschat.classes.News;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

public class NewsAdapter extends RecyclerView.Adapter<NewsAdapter.MyViewHolder> {
    Context context;
   ArrayList<News> news;

    public NewsAdapter(Context context, ArrayList<News> news) {
        this.context = context;
        this.news = news;
    }

    @NonNull
    @Override
    public NewsAdapter.MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(context).inflate(R.layout.custom_news_item,parent,false);
        return new MyViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull NewsAdapter.MyViewHolder holder, int position) {
        News n = news.get(position);
        holder.content.setText(n.getContent());
        holder.reporter.setText(n.getReporter());
        Picasso.get().load(n.getImage().trim()).into(holder.contentImage);
    }

    @Override
    public int getItemCount() {
        return news.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder{
            TextView content,reporter;
            ImageView contentImage;
        public MyViewHolder(@NonNull View itemView) {
            super(itemView);

            content = itemView.findViewById(R.id.newsContent);
            reporter = itemView.findViewById(R.id.newsReporter);
            contentImage = itemView.findViewById(R.id.newsImage);
        }
    }
}
