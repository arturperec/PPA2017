package some.weird.ppafirebaselogin2017.Adapters;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.List;

import some.weird.ppafirebaselogin2017.R;
import some.weird.ppafirebaselogin2017.UserProfile;

/**
 * Created by artur on 11.09.2017.
 */

public class RecyclerViewAdapter extends RecyclerView.Adapter<RecyclerViewHolders> {

    private List<UserProfile> user;

    protected Context context;

    public RecyclerViewAdapter(Context context, List<UserProfile> user){
        this.user = user;
        this.context = context;
    }

    @Override
    public RecyclerViewHolders onCreateViewHolder(ViewGroup parent, int viewType){
        RecyclerViewHolders viewHolder = null;
        View layoutView = LayoutInflater.from(parent.getContext()).inflate(R.layout.profile_data_list, parent, false);
        viewHolder = new RecyclerViewHolders(layoutView);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(RecyclerViewHolders holder, int position) {
        holder.profileHeader.setText(user.get(position).getHeader());
        holder.profileContent.setText(user.get(position).getProfileContent());
    }

    @Override
    public int getItemCount() {
        return this.user.size();
    }

}
