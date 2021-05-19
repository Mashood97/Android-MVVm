package com.example.mvvm_practice_app.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.mvvm_practice_app.R;
import com.example.mvvm_practice_app.model.MovieModel;
import com.example.mvvm_practice_app.model.TodoModel;

import java.util.List;

public class TodoListAdapter extends RecyclerView.Adapter<TodoListAdapter.MyViewHolder>{


    private Context context;
    private List<TodoModel> todoList;
    private TodoListAdapter.ItemClickListener itemClickListener;

    public TodoListAdapter(Context context, List<TodoModel> todoList, TodoListAdapter.ItemClickListener itemClickListener) {
        this.context = context;
        this.todoList = todoList;
        this.itemClickListener = itemClickListener;
    }
    public void setTodoListUpdate(List<TodoModel> todoList) {
        this.todoList = todoList;
        notifyDataSetChanged();
    }


    @NonNull
    @org.jetbrains.annotations.NotNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull @org.jetbrains.annotations.NotNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.todo_list_item, parent, false);
        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull @org.jetbrains.annotations.NotNull MyViewHolder holder, int position) {
        holder.tvTitle.setText(todoList.get(position).getTitle());
        holder.tvId.setText(String.valueOf(todoList.get(position).getId()));
        holder.completedCheckBox.setChecked(this.todoList.get(position).isCompleted());
    }

    @Override
    public int getItemCount() {
        if(todoList != null){
            return todoList.size();
        }
        return 0;
    }

    public static class MyViewHolder extends RecyclerView.ViewHolder {
        TextView tvTitle,tvId;
        CheckBox completedCheckBox;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            tvId = (TextView) itemView.findViewById(R.id.idtxtviews);
            tvTitle = (TextView) itemView.findViewById(R.id.titletxtview);
            completedCheckBox = (CheckBox) itemView.findViewById(R.id.completed_chcbox);
        }
    }

    public interface ItemClickListener{
        public void onTodoItemClick(TodoModel model);
    }
}
