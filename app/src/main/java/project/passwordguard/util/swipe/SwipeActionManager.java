package project.passwordguard.util.swipe;

import android.content.Context;
import android.graphics.Canvas;

import androidx.annotation.NonNull;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.ItemTouchHelper;
import androidx.recyclerview.widget.RecyclerView;

import java.util.Collections;

import it.xabaras.android.recyclerview.swipedecorator.RecyclerViewSwipeDecorator;
import project.passwordguard.R;
import project.passwordguard.listener.RecyclerViewSwipeListener;

public class SwipeActionManager {

    private RecyclerViewSwipeListener listener;

    public ItemTouchHelper.SimpleCallback callback = new ItemTouchHelper.SimpleCallback(0, ItemTouchHelper.LEFT | ItemTouchHelper.RIGHT) {

        @Override
        public boolean onMove(@NonNull RecyclerView recyclerView, @NonNull RecyclerView.ViewHolder viewHolder, @NonNull RecyclerView.ViewHolder target) {
            return false;
        }

        @Override
        public void onSwiped(@NonNull RecyclerView.ViewHolder viewHolder, int direction) {
            final int position = viewHolder.getAdapterPosition();

            switch (direction) {
                case ItemTouchHelper.LEFT:
                    listener.onSwipeLeft(position);
                    break;
                case ItemTouchHelper.RIGHT:
                    listener.onSwipeRight(position);
                    break;
                default:
                    break;
            }
        }


        @Override
        public void onChildDraw(@NonNull Canvas c, @NonNull RecyclerView recyclerView, @NonNull RecyclerView.ViewHolder viewHolder, float dX, float dY, int actionState, boolean isCurrentlyActive) {

            new RecyclerViewSwipeDecorator.Builder(c, recyclerView, viewHolder, dX, dY, actionState, isCurrentlyActive)
                    .addSwipeRightBackgroundColor(ContextCompat.getColor(((Fragment) listener).requireActivity(), R.color.fanteOrange))
                    .addSwipeLeftBackgroundColor(ContextCompat.getColor(((Fragment) listener).requireActivity(), R.color.googleRed))
                    .addSwipeRightActionIcon(R.drawable.ic_edit)
                    .addSwipeLeftActionIcon(R.drawable.ic_delete)
                    .create()
                    .decorate();

            super.onChildDraw(c, recyclerView, viewHolder, dX, dY, actionState, isCurrentlyActive);
        }
    };

    public SwipeActionManager(RecyclerViewSwipeListener listener) {
        this.listener = listener;
    }

    public ItemTouchHelper.Callback getCallback() {
        return callback;
    }
}
