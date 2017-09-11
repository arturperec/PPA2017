package some.weird.ppafirebaselogin2017.Adapters;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

import some.weird.ppafirebaselogin2017.R;

/**
 * Created by artur on 11.09.2017.
 */

public class RecyclerViewHolders extends RecyclerView.ViewHolder {

    private static final String TAG = RecyclerViewHolders.class.getSimpleName();

    public TextView profileHeader;
    public TextView profileContent;

    public RecyclerViewHolders(final View itemView){
        super(itemView);
        profileHeader = (TextView)itemView.findViewById(R.id.heading);
        profileContent = (TextView)itemView.findViewById(R.id.profile_content);
    }
}
