// NoticeAdapter.java
package com.example.mitbuddyapp;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import java.util.List;

public class NoticeAdapter extends RecyclerView.Adapter<NoticeAdapter.NoticeViewHolder> {

    private Context context;
    private List<Notice> noticeList;

    public NoticeAdapter(Context context, List<Notice> noticeList) {
        this.context = context;
        this.noticeList = noticeList;
    }

    @NonNull
    @Override
    public NoticeViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.notice_card_layout, parent, false);
        return new NoticeViewHolder(view);
    }

    @Override
    public void onBindViewHolder(NoticeViewHolder holder, final int position) {
        final Notice notice = noticeList.get(position);
        holder.noticeTitleTextView.setText(notice.getTitle());
        holder.noticeDescriptionTextView.setText(notice.getDescription());
        holder.noticeCategoryTextView.setText(notice.getCategory());

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String url = notice.getLink();
                Intent intent = new Intent(Intent.ACTION_VIEW);
                intent.setData(Uri.parse(url));
                context.startActivity(intent);
            }
        });
    }

    @Override
    public int getItemCount() {
        return noticeList.size();
    }

    public static class NoticeViewHolder extends RecyclerView.ViewHolder {
        TextView noticeTitleTextView, noticeDescriptionTextView, noticeCategoryTextView;

        public NoticeViewHolder(@NonNull View itemView) {
            super(itemView);
            noticeTitleTextView = itemView.findViewById(R.id.titleTextView);
            noticeDescriptionTextView = itemView.findViewById(R.id.descriptionTextView);
            noticeCategoryTextView = itemView.findViewById(R.id.categoryTextView);
        }
    }
}
