package torizhang.imageframetest;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.ImageView;

import com.facebook.drawee.backends.pipeline.Fresco;
import com.facebook.drawee.view.SimpleDraweeView;


public class MainActivity extends AppCompatActivity {

    private RecyclerView recyclerView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Fresco.initialize(this);// 一定要在setContentView之前，否则在解析xml中就会因为找不到simpledraweeview不能
        setContentView(R.layout.activity_main);
        init();
    }

    private void init() {
        ImageView imageView = findViewById(R.id.image_glide);
        SimpleDraweeView simpleDraweeView = findViewById(R.id.image_fresco);
        recyclerView = findViewById(R.id.recycler_view);

        recyclerView.setLayoutManager(new GridLayoutManager(this, 3));

        CommandAdapter commandAdapter = new CommandAdapter(this);
        recyclerView.setAdapter(commandAdapter);

        CommandController controller = new CommandController();
        GlideViewReceiver glideViewReceiver = new GlideViewReceiver(imageView, this);
        FrescoViewReceiver frescoViewReceiver = new FrescoViewReceiver(simpleDraweeView, this);
        controller.addReceiver(glideViewReceiver);
        controller.addReceiver(frescoViewReceiver);

        controller.loadCommands();

        commandAdapter.setList(controller.getCommands());

    }
}
