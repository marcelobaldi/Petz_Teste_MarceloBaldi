package mb.petz.util;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.widget.ImageView;
import androidx.swiperefreshlayout.widget.CircularProgressDrawable;
import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import mb.petz.R;

public class Utils {
    public static void loadImage(ImageView imageView, String url, CircularProgressDrawable progressDrawable){
        RequestOptions options = new RequestOptions()
                                        .placeholder(progressDrawable)
                                        .error(R.drawable.no_image2);

        Glide.with(imageView.getContext())
                                .setDefaultRequestOptions(options)
                                .load(url)
                                .into(imageView);
    }

    public static CircularProgressDrawable getProgressDrawable(Context context){
        CircularProgressDrawable cpd = new CircularProgressDrawable(context);
            cpd.setStrokeWidth(10f);
            cpd.setCenterRadius(50f);
            cpd.start();
        return cpd;
    }

    public static void messageExit(Context contextV){
        AlertDialog.Builder alert = new AlertDialog.Builder(contextV);
            alert.setTitle(R.string.petz);
            alert.setMessage(R.string.msg_exit);
            alert.setCancelable(false);

            alert.setNegativeButton(R.string.no,
                        (dialogInterface, i) -> {
                            dialogInterface.dismiss();
                        });

            alert.setPositiveButton(R.string.yes,
                        (dialogInterfaceX, i) -> {
                            ((Activity)contextV).finishAffinity();
                        });

        alert.create().show();
    }
}