package com.al_qatawi.theprophet;

import android.content.Intent;
import android.media.MediaPlayer;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.widget.PopupMenu;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.viewpager.widget.ViewPager;

import android.util.Log;
import android.view.ActionMode;
import android.view.ContextMenu;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.al_qatawi.theprophet.db.AccesDataBase;
import com.al_qatawi.theprophet.db.Control_Database;
import com.al_qatawi.theprophet.interfase.OnClickLasenarRecyclerView;
import com.al_qatawi.theprophet.modle.Content;
import com.al_qatawi.theprophet.modle.RecyclerAdapter;

import java.util.ArrayList;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link CategaryFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class CategaryFragment extends Fragment {
    private static final String TAG = "CategaryFragment";
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_ID = "id";
    String textH;
    RecyclerView recyclerView;
    android.view.ActionMode actionMode = null;
    ViewPager pager;


    // TODO: Rename and change types of parameters
    private int id;

    public CategaryFragment() {

    }


    // TODO: Rename and change types and number of parameters
    public static CategaryFragment newInstance(int id) {
        CategaryFragment fragment = new CategaryFragment();
        Bundle args = new Bundle();
        args.putInt(ARG_ID, id);

        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            id = getArguments().getInt(ARG_ID);



        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {


        return inflater.inflate(R.layout.fragment_categary, container, false);

    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {


        super.onViewCreated(view, savedInstanceState);

        View v = new View(getContext());
        pager = v.findViewById(R.id.viewPager);


        recyclerView = view.findViewById(R.id.recyclerView);

        switch (id) {

            case 1:
                Dasblay(Control_Database.TABLE_A);

                break;
            case 2:
                Dasblay(Control_Database.TABLE_B);
                break;
            case 3:
                Dasblay(Control_Database.TABLE_C);
                break;
            case 4:
                Dasblay(Control_Database.TABLE_D);
                break;
            case 5:
                Dasblay(Control_Database.TABLE_E);
                break;
            case 6:
                Dasblay(Control_Database.TABLE_F);
                break;
            case 7:
                Dasblay(Control_Database.TABLE_H);

                break;
            case 8:
                Dasblay(Control_Database.TABLE_S);
                break;
            case 9:
                Dasblay(Control_Database.TABLE_M);
                break;


        }


        registerForContextMenu(recyclerView);

    }


    public void Dasblay(String tableName) {

        AccesDataBase accesDataBase = AccesDataBase.getInstance(getContext());
        ArrayList<Content> contents = new ArrayList<>();
        accesDataBase.open();
        contents = accesDataBase.getData(tableName);
        accesDataBase.close();

        RecyclerAdapter adapter = new RecyclerAdapter(contents, new OnClickLasenarRecyclerView() {
            @Override
            public boolean onClick(String text) {

                textH = text;
                    getActivity().startActionMode(callback);
                return true;

            }


        });

        RecyclerView.LayoutManager lm = new LinearLayoutManager(getContext());
        recyclerView.setLayoutManager(lm);
        recyclerView.setHasFixedSize(true);
        recyclerView.setAdapter(adapter);

    }

    ActionMode.Callback callback = new ActionMode.Callback() {
        @Override
        public boolean onCreateActionMode(ActionMode mode, Menu menu) {
            getActivity().getMenuInflater().inflate(R.menu.menu,menu);
            MenuItem item = menu.findItem(R.id.share);

            if (textH != null){

                item.setVisible(true);
            }


            return true;
        }

        @Override
        public boolean onPrepareActionMode(ActionMode mode, Menu menu) {
            return false;
        }

        @Override
        public boolean onActionItemClicked(ActionMode mode, MenuItem item) {

            switch (item.getItemId()) {

                case R.id.share:

                    Intent intent = new Intent();
                    intent.setAction(Intent.ACTION_SEND);
                    intent.putExtra(Intent.EXTRA_TEXT, textH);
                    intent.setType("text/plain");
                    startActivity(Intent.createChooser(intent, "sand text"));

                    getActivity().startActionMode(callback).finish();
            }

            return false;
        }

        @Override
        public void onDestroyActionMode(ActionMode mode) {


        }
    };

}




