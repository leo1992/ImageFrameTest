package torizhang.imageframetest;

import java.util.ArrayList;

/**
 * Created by zhangying on 5/22/18.
 */

public class ImageURLFactory {

    private volatile static ImageURLFactory instance; // volatile实现内存可见

    private static ArrayList<String> jpgList;
    private static ArrayList<String> gifList;

    private static int jpgPos = 0;
    private static int gifPos = 0;

    private ImageURLFactory() {
        jpgList = new ArrayList<>();
        gifList = new ArrayList<>();
        loadJPGUrl();
        loadGIFUrl();
    }

    // 尝试双重加锁单例模式，并不必须同步，不存在多线程问题
    public static ImageURLFactory getInstance() {
        if (instance == null) {
            synchronized (ImageURLFactory.class) {//加锁解决instance可能重复创建的问题，延迟到代码块加锁降低性能消耗
                if (instance == null)
                    instance = new ImageURLFactory();
            }

        }
        return instance;
    }

    private void loadJPGUrl() {
        jpgList.add("http://pic27.photophoto.cn/20130629/0832083540026904_b.jpg");
        jpgList.add("http://pic19.photophoto.cn/20110613/0042040308906995_b.jpg");
        jpgList.add("http://pic35.photophoto.cn/20150406/0042040329116507_b.jpg");
        jpgList.add("http://pic34.photophoto.cn/20150112/0042040358925724_b.jpg");
    }

    private void loadGIFUrl() {
        gifList.add("http://img.soogif.com/YySPQJ68ak53G99mWzgzqNPNRCvRC0ky.gif");
        gifList.add("http://img.soogif.com/3rsdXZa3ufe2jkq7WBizKZuEaxRZbJtQ.gif");
        gifList.add("http://img.mp.itc.cn/upload/20170710/0b2851fd6c0f497d8ba4e35ae987e370_th.jpg");
        gifList.add("http://img.mp.itc.cn/upload/20170526/8631225f44ea430a82f4e2969a14daae_th.jpg");
        gifList.add("http://img.mp.itc.cn/upload/20160910/d4476f65bd1e4c139242cc1fdf6be638_th.gif");
    }

    public String fetchJPGUrl() {
        if (jpgList == null || jpgList.size() == 0) return "";
        int size = jpgList.size();
        if (jpgPos < 0 || jpgPos >= size) {
            jpgPos = 0;
        }
        String url = jpgList.get(jpgPos);
        jpgPos++;
        return url;
    }

    public String fetchGifUrl() {
        if (gifList == null || gifList.size() == 0) return "";
        int size = gifList.size();
        if (gifPos < 0 || gifPos >= size) {
            gifPos = 0;
        }
        String url = gifList.get(gifPos);
        gifPos++;
        return url;
    }
}
