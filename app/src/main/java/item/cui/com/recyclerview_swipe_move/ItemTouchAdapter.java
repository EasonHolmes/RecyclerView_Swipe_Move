package item.cui.com.recyclerview_swipe_move;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import java.util.Collections;
import java.util.List;

public class ItemTouchAdapter extends RecyclerView.Adapter<ItemTouchAdapter.ViewHolder> implements onMoveAndSwipedListener {


    private List<String> list_string;
    private ClickListener clickListener;
    private onStartDragListener dragListener;


    public ItemTouchAdapter(List<String> list_string) {
        this.list_string = list_string;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new ViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.item_move, parent, false));
    }

    @Override
    public void onBindViewHolder(final ViewHolder holder, int position) {
        holder.txt.setText(list_string.get(position));
        holder.imgMove.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (dragListener != null)
                    dragListener.startDrag(holder);
            }
        });
    }

    @Override
    public int getItemCount() {
        return list_string.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        TextView txt;
        ImageView imgMove;

        public ViewHolder(View itemView) {
            super(itemView);
            itemView.setOnClickListener(this);
            txt = (TextView) itemView.findViewById(R.id.item_txt);
            imgMove = (ImageView) itemView.findViewById(R.id.img_move);

        }

        @Override
        public void onClick(View v) {
            if (clickListener != null)
                clickListener.onItemClick(getAdapterPosition(), v);
        }
    }

    public interface onStartDragListener {
        void startDrag(RecyclerView.ViewHolder viewHolder);
    }

    public void setOnItemClickListener(ClickListener clickListener) {
        this.clickListener = clickListener;
    }

    public void setOnstartDragListener(onStartDragListener listener) {
        this.dragListener = listener;
    }

    public interface ClickListener {
        void onItemClick(int position, View v);
    }

    @Override
    public boolean onItemMove(int fromPosition, int toPosition) {
        //需要对数据源也同时进行操作 将两个元素位置互换
        Collections.swap(list_string, fromPosition, toPosition);
        notifyItemMoved(fromPosition, toPosition);
        return true;
    }

    @Override
    public void onItemDismiss(int position) {
        list_string.remove(position);
        notifyItemRemoved(position);
    }

}
