package torizhang.imageframetest;

import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.facebook.drawee.backends.pipeline.Fresco;
import com.facebook.drawee.view.SimpleDraweeView;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private ImageView imageView;
    private SimpleDraweeView simpleDraweeView;
    private RecyclerView recyclerView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Fresco.initialize(this);
        setContentView(R.layout.activity_main);
        init();
    }

    private void init() {
        imageView = findViewById(R.id.image_glide);
        simpleDraweeView = findViewById(R.id.image_fresco);
        recyclerView = findViewById(R.id.recycler_view);

        recyclerView.setLayoutManager(new GridLayoutManager(this, 3));

        CommandAdapter commandAdapter = new CommandAdapter(this);
        recyclerView.setAdapter(commandAdapter);

        List<Command> commandList = new ArrayList<>();
        Command loadJPG = new Command("加载静图") {
            @Override
            void doCommand() {
                loadJPG();
            }
        };
        commandList.add(loadJPG);
        Command loadGIF = new Command("加载动图") {
            @Override
            void doCommand() {
                loadGIF();
            }
        };
        commandList.add(loadGIF);
        commandAdapter.setList(commandList);

//        loadJPG();
    }

    private void loadJPG() {
        Glide.with(this)
                .load("http://img.sccnn.com/bimg/341/04508.jpg")
                .into(imageView);

        Uri uri = Uri.parse("http://img.sccnn.com/bimg/341/04509.jpg");
        simpleDraweeView.setImageURI(uri);
    }

    private void loadGIF() {
        Glide.with(this)
                .load("http://img.soogif.com/YySPQJ68ak53G99mWzgzqNPNRCvRC0ky.gif")
                .into(imageView);


        String url = "http://img.soogif.com/3rsdXZa3ufe2jkq7WBizKZuEaxRZbJtQ.gif";
        simpleDraweeView.setController(Fresco.newDraweeControllerBuilder()
                .setUri(url)
                .setAutoPlayAnimations(true)
                .build());
    }
}
