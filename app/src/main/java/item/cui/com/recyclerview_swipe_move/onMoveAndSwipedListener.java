package item.cui.com.recyclerview_swipe_move;

public interface onMoveAndSwipedListener {
    boolean onItemMove(int fromPosition, int toPosition);
    void onItemDismiss(int position);
}
