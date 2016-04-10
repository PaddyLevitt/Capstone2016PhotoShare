package BackEnd;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.os.Build;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.TextView;
import android.view.LayoutInflater;
import com.capstone.photoshare.R;

/**
 * Created by Lee K. Mills on 2/28/2016.
 * This class represents the adapter that will load a user's photo album collection in a gridview
 */

public class ImageAdapter extends BaseAdapter {
    private Context mContext;
    private Drawable[] pics;
    private LayoutInflater layoutInflater;

    //Constructor
    public ImageAdapter(Context c, Drawable[] d) {
        mContext = c;
        pics = d;
        layoutInflater = LayoutInflater.from(mContext);
    }

    //Returns the length of the list
    public int getCount() {
        return pics.length;
    }

    //Returns the object at specified position
    public Object getItem(int position) {
        return pics[position];
    }

    //Returns ItemId at specified postion
    public long getItemId(int position) {
        return position;
    }

    //Returns the view to populate each list position
    @SuppressWarnings("deprecation")
    public View getView(int position, View convertView, ViewGroup parent) {
        View grid;

        if (convertView == null) {
            grid = layoutInflater.inflate(R.layout.imageview_textview, null);
            grid.setLayoutParams(new GridView.LayoutParams(150, 150));
        } else
            grid = convertView;

        if (Build.VERSION.SDK_INT >= 16) {
            ImageView imageView = (ImageView) grid.findViewById(R.id.image);
            imageView.setBackgroundDrawable(pics[position]);
            TextView textView = (TextView) grid.findViewById(R.id.name);
            textView.setText(String.valueOf(position + 1));
        }
        else {
            ImageView imageView = (ImageView) grid.findViewById(R.id.image);
            imageView.setBackgroundDrawable(pics[position]);
            TextView textView = (TextView) grid.findViewById(R.id.name);
            textView.setText(String.valueOf(position + 1));
        }
        return grid;
    }

}
