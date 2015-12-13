package edu.slcc.nahamad.hangdroid;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.telephony.SmsManager;
import android.telephony.SmsMessage;
import android.util.Log;
import android.widget.Toast;

import java.util.Objects;

public class IncomingSms extends BroadcastReceiver {

   final SmsManager sms= SmsManager.getDefault();
    @Override
    public void onReceive(Context context, Intent intent)
    {
        final Bundle bundle= intent.getExtras();
        try {
            if (bundle != null) {

                Log.d("MYLOG", "Bundle "+bundle);
                //get the pdu from bundle
                final Object[] pdus = (Object[])bundle.get("pdus");
                //get format of the bunle
                String format =bundle.getString("format");

                for(int i = 0; i < pdus.length;i++){

                    SmsMessage currentMessage = SmsMessage.createFromPdu((byte[]) pdus[0]);//formate
                    String phoneNumber =currentMessage.getDisplayOriginatingAddress();
                    //string send num = phoneNumber;
                    String message = currentMessage.getDisplayMessageBody();
                    Log.d("MYLOG", "phone: "+ phoneNumber+ " : message" + message);
                    //show alert
                    Toast.makeText(context,"Text Rcvd: "+phoneNumber, Toast.LENGTH_LONG).show();
                    //set up preff
                    SharedPreferences preferences = context.getSharedPreferences("TEXT_MSGS",context.MODE_PRIVATE);
                    //start the pref editor
                    SharedPreferences.Editor editor = preferences.edit();
                    //get prev score using key
                    Log.d("MYLOG","TextedWord:"+message);
                    editor.putString("TextedWord",message);
                    editor.commit();
                }

            }
        }catch (Exception e){
            Log.e("SmsRciever", "Excep sms Reciver"+ e);
        }
    }
}
