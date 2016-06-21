package com.neo.neoandroidlib;

import android.app.PendingIntent;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.support.v4.app.NotificationCompat.WearableExtender;
import android.telephony.SmsManager;
import android.widget.Toast;
import java.util.Iterator;

public class NeoMsgUtil {
    private static String DELIVERED_SMS_ACTION;
    private static String SENT_SMS_ACTION;
    private static BroadcastReceiver receiver;
    private static BroadcastReceiver sendMessage;

    static {
        SENT_SMS_ACTION = "SENT_SMS_ACTION";
        DELIVERED_SMS_ACTION = "DELIVERED_SMS_ACTION";
        sendMessage = new BroadcastReceiver() {
            public void onReceive(Context context, Intent intent) {
                switch (getResultCode()) {
                    case WearableExtender.UNSET_ACTION_INDEX /*-1*/:
                        Toast.makeText(context, "\u77ed\u4fe1\u53d1\u9001\u6210\u529f", 0).show();
                    default:
                        Toast.makeText(context, "\u53d1\u9001\u5931\u8d25", 1).show();
                }
            }
        };
        receiver = new BroadcastReceiver() {
            public void onReceive(Context context, Intent intent) {
                Toast.makeText(context, "\u5bf9\u65b9\u63a5\u6536\u6210\u529f", 1).show();
            }
        };
    }

    public static void sendMessage(Context context, String text, String phoneNumber) {
        context.registerReceiver(sendMessage, new IntentFilter(SENT_SMS_ACTION));
        context.registerReceiver(receiver, new IntentFilter(DELIVERED_SMS_ACTION));
        PendingIntent sentPI = PendingIntent.getBroadcast(context, 0, new Intent(SENT_SMS_ACTION), 0);
        PendingIntent deliverPI = PendingIntent.getBroadcast(context, 0, new Intent(DELIVERED_SMS_ACTION), 0);
        SmsManager smsManager = SmsManager.getDefault();
        if (text.length() > 70) {
            Iterator it = smsManager.divideMessage(text).iterator();
            while (it.hasNext()) {
                smsManager.sendTextMessage(phoneNumber, null, (String) it.next(), sentPI, deliverPI);
            }
            return;
        }
        smsManager.sendTextMessage(phoneNumber, null, text, sentPI, deliverPI);
    }
}
