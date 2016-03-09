package BackEnd;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;

/**
 * Created by Lee Mills on 2/6/2016.
 * This class is created to use the same alert dialog format throughout the app
 */
public class WarningDialog extends AlertDialog.Builder {

    public WarningDialog(Context context, String warning) {
        super(context);
        setMessage(warning);
        setCancelable(true);
        setNeutralButton(android.R.string.ok,
                new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        dialog.cancel();
                    }
                });

        create();
        show();
    }
}

