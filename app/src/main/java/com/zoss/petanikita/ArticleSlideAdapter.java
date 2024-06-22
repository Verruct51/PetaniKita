package com.zoss.petanikita;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class ArticleSlideAdapter extends RecyclerView.Adapter<ArticleSlideAdapter.ArticleViewHolder> {

    private String[] articleTitles;
    private int[] articleImages;
    private String[] articleContents;

    public ArticleSlideAdapter(String[] articleTitles, int[] articleImages, String[] articleContents) {
        this.articleTitles = articleTitles;
        this.articleImages = articleImages;
        this.articleContents = articleContents;
    }

    @NonNull
    @Override
    public ArticleViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_slide, parent, false);
        return new ArticleViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ArticleViewHolder holder, int position) {
        holder.articleTitle.setText(articleTitles[position]);
        holder.articleImage.setImageResource(articleImages[position]);
        holder.articleContent.setText(articleContents[position]);
    }

    @Override
    public int getItemCount() {
        return articleTitles.length;
    }

    class ArticleViewHolder extends RecyclerView.ViewHolder {
        TextView articleTitle;
        ImageView articleImage;
        TextView articleContent;

        ArticleViewHolder(View itemView) {
            super(itemView);
            articleTitle = itemView.findViewById(R.id.article_title);
            articleImage = itemView.findViewById(R.id.article_image);
            articleContent = itemView.findViewById(R.id.article_content);
        }
    }
}
