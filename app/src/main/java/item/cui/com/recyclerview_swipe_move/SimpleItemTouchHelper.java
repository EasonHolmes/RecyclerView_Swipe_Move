package item.cui.com.recyclerview_swipe_move;

import android.graphics.Canvas;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.helper.ItemTouchHelper;

public class SimpleItemTouchHelper extends ItemTouchHelper.Callback {

    private onMoveAndSwipedListener mAdapter;
    public final float ALPHA_FULL = 1.0f;

    public SimpleItemTouchHelper(onMoveAndSwipedListener listener) {
        this.mAdapter = listener;

    }

    /**
     * 是否可以拖动
     *
     * @return
     */
    @Override
    public boolean isLongPressDragEnabled() {
        return true;
    }

    /**
     * 是否可以滑动
     *
     * @return
     */
    @Override
    public boolean isItemViewSwipeEnabled() {
        return true;
    }

    /**
     * 拖动的方向以及侧滑的方向
     */
    @Override
    public int getMovementFlags(RecyclerView recyclerView, RecyclerView.ViewHolder viewHolder) {
        //设置拖拽方向为上下
//        final int dragFlags = ItemTouchHelper.UP | ItemTouchHelper.DOWN |
//                ItemTouchHelper.LEFT | ItemTouchHelper.RIGHT;
        final int dragFlags = ItemTouchHelper.UP | ItemTouchHelper.DOWN;
        //设置侧滑方向 start从右到往左 end从左往右
//        final int swipeFlags = ItemTouchHelper.START | ItemTouchHelper.END;
        final int swipeFlags = ItemTouchHelper.END;
        return makeMovementFlags(dragFlags, swipeFlags);
    }

    /**
     * 拖动item时回调
     */
    @Override
    public boolean onMove(RecyclerView recyclerView, RecyclerView.ViewHolder viewHolder, RecyclerView.ViewHolder target) {
        //当拖拽的item拖拽到另一种item上时不允许拖拽
        if (viewHolder.getItemViewType() != target.getItemViewType()) {
            return false;
        }
        //回调adapter中的onItemMove方法
        mAdapter.onItemMove(viewHolder.getAdapterPosition(), target.getAdapterPosition());
        return true;
    }

    /**
     * 拖动时会回调.在这里可以绘制item
     */
    @Override
    public void onChildDraw(Canvas c, RecyclerView recyclerView, RecyclerView.ViewHolder viewHolder, float dX, float dY, int actionState, boolean isCurrentlyActive) {
        if (actionState == ItemTouchHelper.ACTION_STATE_SWIPE) {
            // Fade out the view as it is swiped out of the parent's bounds
            final float alpha = ALPHA_FULL - Math.abs(dX) / (float) viewHolder.itemView.getWidth();
            viewHolder.itemView.setAlpha(alpha);
            viewHolder.itemView.setTranslationX(dX);
        } else {
            super.onChildDraw(c, recyclerView, viewHolder, dX, dY, actionState, isCurrentlyActive);
        }
    }

    /**
     * 侧滑item后会回调
     */
    @Override
    public void onSwiped(RecyclerView.ViewHolder viewHolder, int direction) {
        mAdapter.onItemDismiss(viewHolder.getAdapterPosition());
    }


}
