package torizhang.imageframetest;

/**
 * Created by zhangying on 5/21/18.
 */

public abstract class Command {

    public Command(String name) {
        commandName = name;
    }

    public String commandName;
    abstract void doCommand();
}
