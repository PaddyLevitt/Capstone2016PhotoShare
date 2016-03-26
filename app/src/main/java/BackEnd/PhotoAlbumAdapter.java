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
 * This class shows user photo albums in a listview
 */

public class PhotoAlbumAdapter extends BaseAdapter {
    private Context mContext;
    private JSONArray jsonArray;
    private Drawable[] albums;
    private LayoutInflater layoutInflater;

    @SuppressWarnings("deprecation")
    public PhotoAlbumAdapter(Context c, JSONArray jsonArray) {
        mContext = c;
        this.jsonArray = jsonArray;
        albums = new Drawable[jsonArray.length()];
        for(int i = 0; i < jsonArray.length(); i++)
            albums[i] = mContext.getResources().getDrawable(R.drawable.albumcover);

        layoutInflater = LayoutInflater.from(mContext);
    }

    public int getCount() {
        return albums.length;
    }

    public Object getItem(int position) {
        return albums[position];
    }

    public long getItemId(int position) {
        return position;
    }

    @SuppressWarnings("deprecation")
    public View getView(int position, View convertView, ViewGroup parent) {
        View grid;

        if (convertView == null) {
            grid = layoutInflater.inflate(R.layout.albumicon_albumname, null);
        } else
            grid = convertView;

        if (Build.VERSION.SDK_INT >= 16) {
            ImageView imageView = (ImageView) grid.findViewById(R.id.albumCover);
            imageView.setBackgroundDrawable(albums[position]);
            TextView textView = (TextView) grid.findViewById(R.id.albumName);
            try {
                textView.setText(jsonArray.getJSONObject(position).getString("name"));
            } catch (JSONException e) {
                e.printStackTrace();
            }
        }
        else {
            ImageView imageView = (ImageView) grid.findViewById(R.id.albumCover);
            imageView.setBackgroundDrawable(albums[position]);
            TextView textView = (TextView) grid.findViewById(R.id.albumName);
            try {
                textView.setText(jsonArray.getJSONObject(position).getString("name"));
            } catch (JSONException e) {
                e.printStackTrace();
            }
        }
        return grid;
    }

}
