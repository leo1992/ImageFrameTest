package torizhang.imageframetest;

import android.app.Activity;
import android.widget.ImageView;
import android.widget.Toast;

import com.bumptech.glide.Glide;

/**
 * Created by zhangying on 5/22/18.
 */

public class GlideViewReceiver implements Receiver {

    private ImageView imageView;
    private Activity activity; // 建议传入fragment或activity，当其实例销毁时，Glide 会自动取消加载并回收资源。

    public GlideViewReceiver(ImageView imageView, Activity activity) {
        this.imageView = imageView;
        this.activity = activity;
    }

    @Override
    public void loadJPG(String url) {
        Glide.with(activity)
                .load(url)
                .into(imageView);
    }

    @Override
    public void loadGif(String url) {
        Glide.with(activity)
                .load(url)
                .into(imageView);
    }

    @Override
    public void loadWithProgressBar(String gifUrl) {
        Toast.makeText(activity, "Glide不支持自带进度条", Toast.LENGTH_SHORT).show();
        loadGif(gifUrl);
    }
}
