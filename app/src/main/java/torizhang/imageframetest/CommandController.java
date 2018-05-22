package torizhang.imageframetest;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * Created by zhangying on 5/22/18.
 */

public class CommandController {

    private List<Command> commands;
    private List<Receiver> receivers;

    public void loadCommands() {
        if (commands == null)
            commands = new ArrayList<>();
        Command loadJPGCommand = new Command("加载静图") {
            @Override
            void execute() {
                String url = ImageURLFactory.getInstance().fetchJPGUrl();
                // 下载相同的图片，不能直观以图片的顺序反应加载速度，即使图片下载可以多线程，但是ui线程只有一个
                for (Receiver receiver : receivers) {
                    receiver.loadJPG(url);
                }
            }
        };
        commands.add(loadJPGCommand);

        Command loadGifCommand = new Command("加载动图") {
            @Override
            void execute() {
                String url = ImageURLFactory.getInstance().fetchGifUrl();

                for (Receiver receiver : receivers) {
                    receiver.loadGif(url);
                }
            }
        };
        commands.add(loadGifCommand);

        Command loadWithProgressCommand = new Command("加载动图带进度条") {
            @Override
            void execute() {
                String url = ImageURLFactory.getInstance().fetchGifUrl();

                for (Receiver receiver : receivers) {
                    receiver.loadWithProgressBar(url);
                }
            }
        };
        commands.add(loadWithProgressCommand);
    }

    public void addReceiver(Receiver receiver) {
        if (receivers == null)
            receivers = new ArrayList<>();
        receivers.add(receiver);
    }

    public List<Command> getCommands() {
        return Collections.unmodifiableList(commands); //返回只读数组，确保没有任何人能够修改它
    }
}
