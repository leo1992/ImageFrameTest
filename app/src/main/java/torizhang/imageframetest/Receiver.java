package torizhang.imageframetest;

/**
 * Created by zhangying on 5/22/18.
 */

public interface Receiver {

    void loadJPG(String url);
    void loadGif(String url);
    void loadWithProgressBar(String gifUrl);
}
