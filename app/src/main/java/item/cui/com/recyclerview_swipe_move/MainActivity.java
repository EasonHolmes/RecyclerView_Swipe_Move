package item.cui.com.recyclerview_swipe_move;

import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.helper.ItemTouchHelper;
import android.view.View;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity implements
        ItemTouchAdapter.onStartDragListener, ItemTouchAdapter.ClickListener {
    private RecyclerView mRecyclerView;
    private ItemTouchAdapter adapter;
    List<String> list = new ArrayList<String>();
    private ItemTouchHelper mItemTouchHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mRecyclerView = (RecyclerView) findViewById(R.id.recyclview_main);
        //常规设置
        mRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        mRecyclerView.setItemAnimator(new DefaultItemAnimator());
        adapter = new ItemTouchAdapter(getData());
        mRecyclerView.setAdapter(adapter);

        //设置ItemTouchHelper回调
        ItemTouchHelper.Callback callback = new SimpleItemTouchHelper(adapter);
        mItemTouchHelper = new ItemTouchHelper(callback);
        //关联RecyclerView与ItemTouchHelper
        mItemTouchHelper.attachToRecyclerView(mRecyclerView);

        adapter.setOnstartDragListener(this);
        adapter.setOnItemClickListener(this);
    }

    private List<String> getData() {
        for (int i = 0; i < 30; i++) {
            list.add(String.valueOf(i));
        }
        return list;
    }

    @Override
    public void startDrag(RecyclerView.ViewHolder viewHolder) {
        //手动开启拖拽
        mItemTouchHelper.startDrag(viewHolder);
        //手动开启侧滑
//        mItemTouchHelper.startSwipe(viewHolder);
    }

    @Override
    public void onItemClick(int position, View v) {
        Snackbar.make(v, list.get(position), Snackbar.LENGTH_SHORT).show();
    }
}

