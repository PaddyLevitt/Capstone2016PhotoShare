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
 * Created by Lee K. Mills on 2/28/2016.
 * This class represents the adapter that will load a user's photo albums in a listview
 */

public class PhotoAlbumAdapter extends BaseAdapter {
    private Context mContext;
    private JSONArray jsonArray;
    private Drawable[] albums;
    private LayoutInflater layoutInflater;

    //Constructor
    @SuppressWarnings("deprecation")
    public PhotoAlbumAdapter(Context c, JSONArray jsonArray) {
        mContext = c;
        this.jsonArray = jsonArray;
        albums = new Drawable[jsonArray.length()];
        for(int i = 0; i < jsonArray.length(); i++)
            albums[i] = mContext.getResources().getDrawable(R.drawable.albumcover);

        layoutInflater = LayoutInflater.from(mContext);
    }

    //Returns the length of the list
    public int getCount() {
        return albums.length;
    }

    //Returns the object at specified position
    public Object getItem(int position) {
        return albums[position];
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
            list = layoutInflater.inflate(R.layout.albumicon_albumname, null);
        } else
            list = convertView;

        if (Build.VERSION.SDK_INT >= 16) {
            ImageView imageView = (ImageView) list.findViewById(R.id.albumCover);
            imageView.setBackgroundDrawable(albums[position]);
            TextView textView = (TextView) list.findViewById(R.id.albumName);
            try {
                textView.setText(jsonArray.getJSONObject(position).getString("name"));
            } catch (JSONException e) {
                e.printStackTrace();
            }
        }
        else {
            ImageView imageView = (ImageView) list.findViewById(R.id.albumCover);
            imageView.setBackgroundDrawable(albums[position]);
            TextView textView = (TextView) list.findViewById(R.id.albumName);
            try {
                textView.setText(jsonArray.getJSONObject(position).getString("name"));
            } catch (JSONException e) {
                e.printStackTrace();
            }
        }
        return list;
    }

}
