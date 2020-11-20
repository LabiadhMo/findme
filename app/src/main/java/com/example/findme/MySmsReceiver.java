package com.example.findme;

import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.media.RingtoneManager;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.telephony.SmsMessage;
import android.widget.Toast;

import androidx.core.app.NotificationCompat;
import androidx.core.app.NotificationManagerCompat;

public class MySmsReceiver extends BroadcastReceiver {

    @Override
    public void onReceive(Context context, Intent intent) {
        // TODO: This method is called when the BroadcastReceiver is receiving
        // an Intent broadcast.
    //    throw new UnsupportedOperationException("Not yet implemented");

        Toast.makeText(context,"sms reçu",Toast.LENGTH_SHORT).show();
        String messageBody,phoneNumber;
        if(intent.getAction().equals("android.provider.Telephony.SMS_RECEIVED"))
        {
            Bundle bundle =intent.getExtras();
            if (bundle != null) {
                Object[] pdus = (Object[]) bundle.get("pdus");
                final SmsMessage[] messages = new SmsMessage[pdus.length];
                for (int i = 0; i < pdus.length; i++) {
                    messages[i] = SmsMessage.createFromPdu((byte[]) pdus[i]);
                }
                if (messages.length > -1) {
                    messageBody = messages[0].getMessageBody();
                    phoneNumber = messages[0].getDisplayOriginatingAddress();

                  /*  Toast.makeText(context,
                            "Message : "+messageBody+"Reçu de la part de;"+ phoneNumber,
                            Toast.LENGTH_LONG )
                            .show();*/

                    if(messageBody.contains("FindMe"))
                    {
                        NotificationCompat.Builder mbuilder =
                                new NotificationCompat.Builder(context,"CHANNEL_FINDME");
                        mbuilder.setSmallIcon(android.R.drawable.ic_dialog_alert);
                        mbuilder.setContentTitle("FindMe....");
                        mbuilder.setContentText("position: "+messageBody);
                        mbuilder.setAutoCancel(true);
                        mbuilder.setPriority(NotificationCompat.PRIORITY_DEFAULT);

                        mbuilder.setVibrate(new long[]{0,1000,500,2000});

                        Uri sound = RingtoneManager.getDefaultUri(RingtoneManager.TYPE_ALARM);
                        mbuilder.setSound(sound);

                        //action sur la notification
                        Intent i = new Intent(context,Ajout.class);
                        i.putExtra("Phone",phoneNumber);
                        i.putExtra("Body",messageBody);

                        PendingIntent pi= PendingIntent.getActivity(context,0,i,PendingIntent.FLAG_ONE_SHOT);
                        mbuilder.setContentIntent(pi);

                        NotificationManagerCompat notificationManager =
                                NotificationManagerCompat.from(context);

                        //creation du Canal


                        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
                            NotificationChannel channel = new
                                    NotificationChannel("CHANNEL_FINDME",
                                    "FINDME",
                                    NotificationManager.IMPORTANCE_DEFAULT);
                            channel.setDescription("description"); // facultatif

                            notificationManager.createNotificationChannel(channel);

                        }
                        notificationManager.notify(0,mbuilder.build());

                    }

                }
            }
        }
    }
}