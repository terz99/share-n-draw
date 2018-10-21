package com.example.terz99.whiteboard;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;


import java.util.ArrayList;

public class BoardsAdapter extends RecyclerView.Adapter<BoardsAdapter.BoardItemViewHolder> {

    public interface OnItemClickListener{
        void onItemClick(Boards board);
    }

    private OnItemClickListener listener;
    private ArrayList<Boards> boards;

    BoardsAdapter(ArrayList<Boards> boards, OnItemClickListener listener) {
        this.boards = boards;
        this.listener = listener;
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
//        boardItemViewHolder.mTextView.setText(boards.get(index).getBoard());
        boardItemViewHolder.bind(boards.get(index), listener);
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
