package BackEnd;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.os.Build;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;
import com.capstone.photoshare.R;
import org.json.JSONArray;
import org.json.JSONException;

/**
 * Created by Lee Mills on 4/9/2016.
 * This class represents the adapter that will load a user's Friend list in a listview
 */
public class FriendsListAdapter extends BaseAdapter {
    private Context mContext;
    private JSONArray jsonArray;
    private Drawable[] friends;
    private LayoutInflater layoutInflater;

    //Constructor
    @SuppressWarnings("deprecation")
    public FriendsListAdapter(Context c, JSONArray jsonArray) {
        mContext = c;
        this.jsonArray = jsonArray;
        friends = new Drawable[jsonArray.length()];
        for(int i = 0; i < jsonArray.length(); i++)
            friends[i] = mContext.getResources().getDrawable(R.drawable.friend);

        layoutInflater = LayoutInflater.from(mContext);
    }

    //Returns the length of the list
    public int getCount() {
        return friends.length;
    }

    //Returns the object at specified position
    public Object getItem(int position) {
        return friends[position];
    }

    //Returns ItemId at specified postion
    public long getItemId(int position) {
        return position;
    }

    //Returns the view to populate each list position
    @SuppressWarnings("deprecation")
    public View getView(int position, View convertView, ViewGroup parent) {
        View list;

        if (convertView == null) {
            list = layoutInflater.inflate(R.layout.friendicon_friendname, null);
        } else
            list = convertView;

        if (Build.VERSION.SDK_INT >= 16) {
            ImageView imageView = (ImageView) list.findViewById(R.id.friendIcon);
            imageView.setBackgroundDrawable(friends[position]);
            TextView textView = (TextView) list.findViewById(R.id.friendName);
            try {
                textView.setText(jsonArray.getJSONObject(position).getString("username"));
            } catch (JSONException e) {
                e.printStackTrace();
            }
        }
        else {
            ImageView imageView = (ImageView) list.findViewById(R.id.friendIcon);
            imageView.setBackgroundDrawable(friends[position]);
            TextView textView = (TextView) list.findViewById(R.id.friendName);
            try {
                textView.setText(jsonArray.getJSONObject(position).getString("username"));
            } catch (JSONException e) {
                e.printStackTrace();
            }
        }
        return list;
    }
}
