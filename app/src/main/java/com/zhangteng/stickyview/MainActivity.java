package com.zhangteng.stickyview;

import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    RecyclerView recyclerView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        recyclerView = (RecyclerView) findViewById(R.id.recyclerView);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        ArrayList<Data> datas = new ArrayList<>();
        for (int i = 0; i < 100; i++) {
            Data data = new Data();
            data.setItem(String.valueOf(i));
            datas.add(data);
        }
        ItemStickyDecoration.GroupInfoInterface groupInfoInterface = new ItemStickyDecoration.GroupInfoInterface() {
            @Override
            public GroupInfo getGroupInfo(int position) {
                int groupId = position / 5;
                int index = position % 5;
                GroupInfo groupInfo = new GroupInfo(groupId, String.valueOf(groupId), index, 5);
                groupInfo.setPosition(index);
                return groupInfo;
            }
        };
        recyclerView.setAdapter(new MyAdapter(datas));
        recyclerView.addItemDecoration(new ItemStickyDecoration(groupInfoInterface));
    }

    class MyAdapter extends RecyclerView.Adapter<MyAdapter.MyViewHolder> {
        private ArrayList<Data> datas;

        public MyAdapter(ArrayList<Data> datas) {
            this.datas = datas;
        }

        @NonNull
        @Override
        public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
            TextView textView = new TextView(parent.getContext());
            textView.setHeight(200);
            MyViewHolder myViewHolder = new MyViewHolder(textView);
            return myViewHolder;
        }

        @Override
        public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
            holder.textView.setText(datas.get(position).getItem());
        }

        @Override
        public int getItemCount() {
            return datas.size();
        }

        class MyViewHolder extends RecyclerView.ViewHolder {
            public TextView textView;

            public MyViewHolder(View itemView) {
                super(itemView);
                textView = (TextView) itemView;
            }
        }
    }
}
