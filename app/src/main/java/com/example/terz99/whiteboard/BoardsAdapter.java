package com.example.terz99.whiteboard;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;


import java.util.ArrayList;
import android.os.Handler;

/**
 * Custom adapter used to populate the view in MainActivity
 *
 * @see     MainActivity
 */
public class BoardsAdapter extends RecyclerView.Adapter<BoardsAdapter.BoardItemViewHolder> {

    private Context context;
    private SwipeRefreshLayout swiper;

    /**
     * Interface for click on the items in the view in MainActivity.
     */
    public interface OnItemClickListener{
        void onItemClick(Boards board);
    }

    private OnItemClickListener listener;
    private ArrayList<Boards> boards;

    /**
     * @param context   The parent context.
     * @param boards
     * @param swiper    SwipeRefreshLayout object for refreshing the content.
     * @param listener  On click listener for the items in the view.
     */
    BoardsAdapter(Context context, ArrayList<Boards> boards, SwipeRefreshLayout swiper, OnItemClickListener listener) {
        this.context = context;
        this.boards = boards;
        this.listener = listener;
        this.swiper = swiper;
    }

    @NonNull
    @Override
    public BoardItemViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        TextView v = (TextView) LayoutInflater.from(parent.getContext())
                .inflate(R.layout.list_item, parent, false);
        return new BoardItemViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull BoardItemViewHolder boardItemViewHolder, int index) {
        boardItemViewHolder.bind(boards.get(index), listener);
        swiper.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                refresh();
            }
        });
    }

    /**
     * Method which refreshes the content in MainActivity.
     */
    private void refresh() {
        (new Handler()).postDelayed(new Runnable() {
            @Override
            public void run() {
                BoardsAdapter.this.notifyDataSetChanged();
                swiper.setRefreshing(false);
            }
        }, 2000);
    }

    @Override
    public int getItemCount() {
        return boards.size();
    }

    static class BoardItemViewHolder extends RecyclerView.ViewHolder{
        TextView mTextView;
        BoardItemViewHolder(@NonNull View itemView) {
            super(itemView);
            mTextView = (TextView) itemView;
        }

        void bind(final Boards board, final OnItemClickListener listener){
            mTextView.setText(board.getBoard());
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    listener.onItemClick(board);
                }
            });
        }
    }
}
