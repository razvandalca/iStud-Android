package ro.horiacalin.istud.BusinessLayer.Services;

import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.media.RingtoneManager;
import android.net.Uri;
import android.support.v7.app.NotificationCompat;
import android.util.Log;

import com.google.firebase.messaging.FirebaseMessagingService;
import com.google.firebase.messaging.RemoteMessage;
import com.google.gson.Gson;

import java.util.List;

import ro.horiacalin.istud.BusinessLayer.Managers.ToolsManager;
import ro.horiacalin.istud.BusinessLayer.Pojo.EventNotif;
import ro.horiacalin.istud.Constants;
import ro.horiacalin.istud.PresentationLayer.Controller.NotificareActivity;
import ro.horiacalin.istud.R;

/**
 * Created by Razvan'S PC on 05.03.2017.
 */

public class iStudyFirbaseMessagingService extends FirebaseMessagingService {

    @Override
    public void onMessageReceived(RemoteMessage remoteMessage) {
        super.onMessageReceived(remoteMessage);
        Gson gson = new Gson();
        EventNotif eventNotif = gson.fromJson(remoteMessage.getData().get("data"), EventNotif.class);
        Uri uri = RingtoneManager.getDefaultUri(RingtoneManager.TYPE_NOTIFICATION);
        Log.e("NOTIFICATIONS", "onMessageReceived: ");
        NotificationCompat.Builder mBuilder = new NotificationCompat.Builder(this);
        mBuilder.setSound(uri);
        mBuilder.setDefaults(Notification.DEFAULT_VIBRATE);
        mBuilder.setColor(Color.RED);
        mBuilder.setSmallIcon(R.drawable.ic_small_icon_notification);
        mBuilder.setAutoCancel(true);

        Bitmap iconBig = BitmapFactory.decodeResource(getApplicationContext().getResources(),
                R.drawable.ic_notification);
        mBuilder.setLargeIcon(iconBig);

        if (eventNotif != null) {
            mBuilder.setContentTitle(eventNotif.getTitle());
            mBuilder.setContentText(eventNotif.getMessage());
            ToolsManager.getInstance().saveNotifEvents(eventNotif, getApplicationContext());

            Intent notificationIntent = new Intent(getApplicationContext(), NotificareActivity.class);
            notificationIntent.putExtra(Constants.NOTIF_KEY, eventNotif);



            PendingIntent contentIntent = PendingIntent.getActivity(this, Constants.requestID_NOtIF ,notificationIntent, PendingIntent.FLAG_UPDATE_CURRENT);
            mBuilder.setContentIntent(contentIntent);
        }


        NotificationManager notificationManager = (NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE);
        notificationManager.notify(0, mBuilder.build());


    }
}
