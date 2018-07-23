package khan.examstressbuster;

import android.content.Context;
import android.content.SharedPreferences;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.List;

/**
 * Created by Khan on 03-Mar-17.
 */

public class DuaCardAdapter extends RecyclerView.Adapter<DuaViewHolder>
{

    private List<DuaInfo> duaList;
    private Context context;
    public DuaCardAdapter(List<DuaInfo> contactList)
    {
        this.duaList = contactList;
    }

    @Override
    public int getItemCount()
    {
        return duaList.size();
    }

    @Override
    public void onBindViewHolder(DuaViewHolder duaViewHolder, int i)
    {
        DuaInfo di = duaList.get(i);
        duaViewHolder.dName.setText(di.name);
        duaViewHolder.dText.setText(di.text);
        duaViewHolder.dReference.setText(di.reference);
    }

    @Override
    public DuaViewHolder onCreateViewHolder(ViewGroup viewGroup, int i)
    {
        View itemView = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.dua_card_layout, viewGroup, false);
        context = itemView.getContext();
        return new DuaViewHolder(itemView);
    }
}