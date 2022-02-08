package com.example.smsbomber;

import androidx.fragment.app.Fragment;

import android.os.Bundle;
import android.telephony.gsm.SmsManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.Nullable;



public class SMSenvoye extends Fragment {

    public SMSenvoye (){

    }

    public View onCreateView(LayoutInflater inflater , ViewGroup container ,
                             Bundle savedInstanceState){
        return inflater.inflate(R.layout.activity_smsenvoye , container , false);
    }
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        Button btnEnvoie = (Button)view.findViewById(R.id.envoyer);
        final EditText numero =(EditText)view.findViewById(R.id.numero);
        final EditText message = (EditText)view.findViewById(R.id.message);
        final EditText nb = (EditText)view.findViewById(R.id.numbreEnvoyer);
        btnEnvoie.setOnClickListener(new OnClickListener() {


            @SuppressWarnings("deprecation")
            public void onClick(View v) {
                String num = numero.getText().toString();
                String msg = message.getText().toString();
                int  y = Integer.parseInt(nb.getText().toString());
                if(num.length()>= 4 && msg.length() > 0 && y > 0){
                    for(int i = 0; i < y; i++) {
                        SmsManager.getDefault().sendTextMessage(num, null, msg, null, null);
                    }
                    numero.setText("");
                    message.setText("");
                    nb.setText("");
                }else{
                    Toast.makeText(getContext(), "Entrer le numero et/ou le message", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }
}