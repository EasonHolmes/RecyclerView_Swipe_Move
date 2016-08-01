package item.cui.com.recyclerview_swipe_move;

/**
 * Created by tangyangkai on 16/5/17.
 */
public interface onMoveAndSwipedListener {
    boolean onItemMove(int fromPosition, int toPosition);
    void onItemDismiss(int position);
}
