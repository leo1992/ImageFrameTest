package torizhang.imageframetest;

import android.content.Context;
import android.net.Uri;

import com.facebook.drawee.backends.pipeline.Fresco;
import com.facebook.drawee.drawable.ProgressBarDrawable;
import com.facebook.drawee.generic.GenericDraweeHierarchy;
import com.facebook.drawee.generic.GenericDraweeHierarchyBuilder;
import com.facebook.drawee.view.SimpleDraweeView;

/**
 * Created by zhangying on 5/22/18.
 */

public class FrescoViewReceiver implements Receiver {

    private SimpleDraweeView simpleDraweeView;
    private Context context;

    public FrescoViewReceiver(SimpleDraweeView view, Context context) {
        simpleDraweeView = view;
        this.context = context;
    }

    @Override
    public void loadJPG(String url) {
        clearProgressBar();
        Uri uri = Uri.parse(url);
        simpleDraweeView.setImageURI(uri);
    }

    @Override
    public void loadGif(String url) {
        clearProgressBar();
        simpleDraweeView.setController(Fresco.newDraweeControllerBuilder()
                .setUri(url)
                .setAutoPlayAnimations(true)
                .build());
    }

    @Override
    public void loadWithProgressBar(String gifUrl) {
        GenericDraweeHierarchyBuilder builder = new GenericDraweeHierarchyBuilder(context.getResources());
        GenericDraweeHierarchy hierarchy = builder
                .setProgressBarImage(new ProgressBarDrawable())
                .build();
        simpleDraweeView.setHierarchy(hierarchy);
        simpleDraweeView.setController(Fresco.newDraweeControllerBuilder()
                .setUri(gifUrl)
                .setAutoPlayAnimations(true)
                .build());
    }

    private void clearProgressBar(){
        GenericDraweeHierarchyBuilder builder = new GenericDraweeHierarchyBuilder(context.getResources());
        GenericDraweeHierarchy hierarchy = builder
                .setProgressBarImage(null)
                .build();
        simpleDraweeView.setHierarchy(hierarchy);
    }
}
