package com.example.smsbomber;

import androidx.annotation.Nullable;

import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import android.Manifest;
import android.annotation.SuppressLint;
import androidx.fragment.app.Fragment;
import android.content.ContentResolver;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.os.Bundle;
import android.provider.ContactsContract;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ListView;


import java.util.ArrayList;

public class ListContact extends Fragment {

    ListView listView;
    ArrayList<String> arrayList;
    ArrayAdapter arrayAdapter;

    public ListContact(){

    }

    public View onCreateView(LayoutInflater inflater , ViewGroup container ,
                             Bundle savedInstanceState){
        return inflater.inflate(R.layout.activity_list_contact , container , false);
    }


    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        listView = view.findViewById(R.id.lstview);
        arrayList = new ArrayList<>();
        arrayAdapter = new ArrayAdapter(getContext(), android.R.layout.simple_list_item_1, arrayList);
        listView.setAdapter(arrayAdapter);

        if (ContextCompat.checkSelfPermission(getContext(), Manifest.permission.READ_CONTACTS) != PackageManager.PERMISSION_GRANTED) {

            ActivityCompat.requestPermissions(getActivity(), new String[]{Manifest.permission.READ_CONTACTS}, 100);

        } else {

            readContacts();
        }

    }



    @SuppressLint("Range")
    private void readContacts() {
        ContentResolver contentResolver=getContext().getContentResolver();
        Cursor cursor=contentResolver.query(ContactsContract.Contacts.CONTENT_URI,null,null,null,null);
        if (cursor.moveToFirst()){
            do {     arrayList.add(cursor.getString(cursor.getColumnIndex(ContactsContract.Contacts.DISPLAY_NAME)));
            }while (cursor.moveToNext());
            arrayAdapter.notifyDataSetChanged();
        }
    }
}