package app_aad_exam.app_firebasecrud;

import android.app.Activity;
import android.graphics.Color;
import android.view.Gravity;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.firebase.client.Query;

/**
 * @author greg
 * @since 6/21/13
 *
 * This class is an example of how to use FirebaseListAdapter. It uses the <code>Chat</code> class to encapsulate the
 * data for each individual chat message
 */
public class ChatListAdapter extends FirebaseListAdapter<Chat> {

    private String mUsername;

    public ChatListAdapter(Query ref, Activity activity, int layout, String mUsername) {
        super(ref, Chat.class, layout, activity);
        this.mUsername = mUsername;
    }

    @Override
    protected void populateView(View view, Chat chat) {
        String author = chat.getAuthor();
        LinearLayout chatLayout = (LinearLayout) view.findViewById(R.id.chat_layout);
        TextView authorText = (TextView) view.findViewById(R.id.author);
        TextView message = ((TextView) view.findViewById(R.id.message));
        authorText.setText(author + ": ");
        if (author != null && author.equals(mUsername)) {
            authorText.setTextColor(Color.RED);
            chatLayout.setGravity(Gravity.RIGHT);
        } else {
            authorText.setTextColor(Color.BLUE);
            chatLayout.setGravity(Gravity.LEFT);
        }
        message.setText(chat.getMessage());
    }
}
