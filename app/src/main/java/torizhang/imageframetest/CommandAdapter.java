package torizhang.imageframetest;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.util.TypedValue;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.List;
import java.util.zip.Inflater;

/**
 * Created by zhangying on 5/21/18.
 */

public class CommandAdapter extends RecyclerView.Adapter {

    private Context context;
    private List<Command> commandList;

    public CommandAdapter(Context context) {
        this.context = context;
    }

    public void setList(List<Command> commandList) {
        this.commandList = commandList;
        notifyDataSetChanged();
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.command_item, parent, false);
        return new CommandItemHolder(view);
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        if (commandList == null || position >= commandList.size()) return;
        final Command command = commandList.get(position);
        CommandItemHolder commandItemHolder = (CommandItemHolder) holder;
        commandItemHolder.mCommandTV.setText(command.commandName);
        commandItemHolder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                command.doCommand();
            }
        });
    }

    @Override
    public int getItemCount() {
        return commandList == null ? 0 : commandList.size();
    }

    class CommandItemHolder extends RecyclerView.ViewHolder {
        TextView mCommandTV;

        public CommandItemHolder(View itemView) {
            super(itemView);
            mCommandTV = itemView.findViewById(R.id.name_iv);
        }
    }
}
