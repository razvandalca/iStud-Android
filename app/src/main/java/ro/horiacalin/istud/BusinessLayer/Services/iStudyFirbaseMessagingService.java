package ro.horiacalin.istud.BusinessLayer.Services;

import android.app.Notification;
import android.app.NotificationManager;
import android.content.Context;
import android.graphics.Color;
import android.media.RingtoneManager;
import android.net.Uri;
import android.support.v7.app.NotificationCompat;
import android.util.Log;

import com.google.firebase.messaging.FirebaseMessagingService;
import com.google.firebase.messaging.RemoteMessage;

import ro.horiacalin.istud.R;

/**
 * Created by Razvan'S PC on 05.03.2017.
 */

public class iStudyFirbaseMessagingService extends FirebaseMessagingService {

    @Override
    public void onMessageReceived(RemoteMessage remoteMessage) {
        super.onMessageReceived(remoteMessage);
        Uri uri= RingtoneManager.getDefaultUri(RingtoneManager.TYPE_NOTIFICATION);
        Log.e("NOTIFICATIONS", "onMessageReceived: ");
        NotificationCompat.Builder mBuilder = new NotificationCompat.Builder(this);
        mBuilder.setContentTitle("iStudy Mesaj Important!");
        mBuilder.setSound(uri);
        mBuilder.setDefaults(Notification.DEFAULT_VIBRATE);
        mBuilder.setColor(Color.RED);
        mBuilder.setSmallIcon(R.mipmap.ic_launcher);



        // Check if message contains a notification payload.
//        if (remoteMessage.getNotification() != null) {
//            mBuilder.setContentText(remoteMessage.getNotification().getBody());
//        }

        NotificationManager notificationManager = (NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE);
        notificationManager.notify(0, mBuilder.build());

    }
}
