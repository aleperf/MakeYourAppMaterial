package com.example.xyzreader.ui;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.xyzreader.R;

public class ArticleAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    private static final int TITLE_TYPE = 1;
    private static final int ARTICLE_BODY_TYPE = 2;

    Context context;
    String title;
    String author;
    String date;
    String[] articleBody;

    public ArticleAdapter(Context context, String title, String author, String date, String[] articleBody) {
        this.context = context;
        this.title = title;
        this.author = author;
        this.date = date;
        this.articleBody = articleBody;
    }


    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(context);
        View view;
        switch (viewType) {
            case TITLE_TYPE:
                view = inflater.inflate(R.layout.article_title_item, parent, false);
                return new TitleViewHolder(view);
            default:
                view = inflater.inflate(R.layout.article_body_item, parent, false);
                return new ArticleBodyViewHolder(view);

        }

    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {

        switch(holder.getItemViewType()){
            case TITLE_TYPE:
                TitleViewHolder titleViewHolder = (TitleViewHolder) holder;
                titleViewHolder.bindTitle();
                break;
            default:
                ArticleBodyViewHolder articleBodyViewHolder = (ArticleBodyViewHolder) holder;
                String articleSection = articleBody[position - 1];
                articleBodyViewHolder.bindArticleBody(articleSection);
        }

    }

    @Override
    public int getItemCount() {
        int count = 0;
        if (!TextUtils.isEmpty(title) && !TextUtils.isEmpty(author)) {
            count++;
        }
        if (articleBody != null) {
            count += articleBody.length;
        }
        return count;
    }

    @Override
    public int getItemViewType(int position) {
        if (position == 0) {
            return TITLE_TYPE;
        }
        return ARTICLE_BODY_TYPE;
    }

    public void setArticles(String[] articles){
        this.articleBody = articles;
        notifyDataSetChanged();
    }

    class TitleViewHolder extends RecyclerView.ViewHolder{

        TextView titleTextView;
        TextView authorTextView;
        TextView dateTextView;

        public TitleViewHolder(View view){
            super(view);
            titleTextView = view.findViewById(R.id.detail_title);
            authorTextView = view.findViewById(R.id.detail_author);
            dateTextView = view.findViewById(R.id.detail_date);
        }

        public void bindTitle(){
            titleTextView.setText(title);
            authorTextView.setText(author);
            dateTextView.setText(date);
        }

    }

    class ArticleBodyViewHolder extends RecyclerView.ViewHolder{

        TextView articleBodyTextView;

        public ArticleBodyViewHolder(View view){
            super(view);
            articleBodyTextView = view.findViewById(R.id.article_body_text);
        }

        public void bindArticleBody(String article){
            articleBodyTextView.setText(article);
        }
    }
}