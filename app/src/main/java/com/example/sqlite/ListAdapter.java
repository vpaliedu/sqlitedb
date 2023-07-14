package com.example.sqlite;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

/*
    A RecyclerView.Adapter-t és a RecyclerView.ViewHolder-t, amelyek felelősek az adatok megjelenítéséért
    és kezeléséért a RecyclerView-ben.
    A ListAdapter egy testreszabott RecyclerView.Adapter, amely felelős a RecyclerView adatokkal történő feltöltéséért.
    kiterjeszti a RecyclerView.Adapter-t. Ez az osztály kezeli az adatok listáját, amelyeket meg szeretnénk jeleníteni a RecyclerView-ban.
    A ListAdapter konstruktorában átadjuk az adatok listáját
*/

public class ListAdapter extends RecyclerView.Adapter<ListAdapter.ViewHolder> {
    private List<ListItem> items;
    DataBaseHelper dataBaseHelper;
    SQLiteDatabase db;
    Context context;

    public ListAdapter(List<ListItem> items, Context context) {
        this.items = items;
        this.context = context;
    }

    /*
        Az onCreateViewHolder metódus felelős a ViewHolder létrehozásáért, amely tartalmazza a nézetet (a lista egy elemét).
        Ez a metódus inflates a list_item.xml layout-ot és létrehoz belőle egy ViewHolder objektumot
    */
    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.list_item, parent, false);
        return new ViewHolder(v);
    }

    /*
        Az onBindViewHolder metódus felelős az adatok beállításáért a ViewHolder-ben, azaz az adatok összekötéséért a nézettel.
        Ez a metódus meghívódik minden egyes lista elem esetében, és beállítja a megfelelő adatokat az adott elemre.
    */
    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        ListItem item = items.get(position);
        holder.textViewNumber.setText(item.getNumber());
        holder.textViewName.setText(item.getName());
        holder.textViewEmail.setText(item.getEmail());
        holder.textViewNumber.setOnClickListener(v->{
            DataBaseCommands dbCom = new DataBaseCommands();
            dbCom.deleteData(item.getNumber(),context);
        });
    }

    /*
        Az getItemCount metódus visszaadja a lista elemeinek számát. Ez a metódus fontos a RecyclerView.Adapter számára, mert ezzel tudja meg, hány elemet kell megjeleníteni.
    */
    @Override
    public int getItemCount() {
        return items.size();
    }

    /*
    Végül, a ViewHolder osztály egy belső osztály a ListAdapter-ben. Ez az osztály felelős az egyes lista elemek nézeteinek tárolásáért
    és kezeléséért. A ViewHolder általában tartalmazza a nézeteket, amelyek az adatokat tartalmazzák, ebben az esetben egy TextView-t.
    A ViewHolder konstruktorában található a nézetek inicializálása.
    */
    class ViewHolder extends RecyclerView.ViewHolder {
        TextView textViewNumber;
        TextView textViewName;
        TextView textViewEmail;

        ViewHolder(View itemView) {
            super(itemView);
            textViewNumber = itemView.findViewById(R.id.textViewNumber);
            textViewName = itemView.findViewById(R.id.textViewName);
            textViewEmail = itemView.findViewById(R.id.textViewEmail);
        }
    }
}