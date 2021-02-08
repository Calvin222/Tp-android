package com.example.myapplication;

import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.os.Build;
import android.util.Log;

import androidx.core.app.NotificationCompat;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.iid.FirebaseInstanceId;
import com.google.firebase.messaging.FirebaseMessagingService;
import com.google.firebase.messaging.RemoteMessage;

public class MyFirebaseMessagingService extends FirebaseMessagingService {

    private static final String CANAL = "MyNotifCANAL";

    @Override
    public void onNewToken(String s) {
        super.onNewToken(s);
        FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
        String RefreshToken = FirebaseInstanceId.getInstance().getToken();
        if (user != null){
        }


    }

    @Override
    public void onMessageReceived(RemoteMessage remoteMessage){
        super.onMessageReceived(remoteMessage);

        String myMessage = remoteMessage.getNotification().getBody();
        Log.e( "FirebaseMessage", "Vous venez de recevoir une notification :" + myMessage);

        //Création d'une notif
        NotificationCompat.Builder notificationBuilder = new NotificationCompat.Builder(this, CANAL);
        notificationBuilder.setContentTitle("Ma notif !");
        notificationBuilder.setContentText(myMessage);

        //Icone notif
        notificationBuilder.setSmallIcon(R.drawable.alarm);

        //Envoyer la notif
        NotificationManager notificationManager = (NotificationManager) getSystemService(NOTIFICATION_SERVICE);

        if(Build.VERSION.SDK_INT > Build.VERSION_CODES.O)
        {
            String channelId = getString(R.string.notification_channel_id);
            String channelTitle = getString(R.string.notification_channel_title);
            String channelDescription = getString(R.string.notification_channel_description);
            NotificationChannel channel = new NotificationChannel(channelId,channelTitle,NotificationManager.IMPORTANCE_DEFAULT);
            channel.setDescription(channelDescription);
            notificationManager.createNotificationChannel(channel);
            notificationBuilder.setChannelId(channelId);
        }

        notificationManager.notify(1, notificationBuilder.build());
    }
}
