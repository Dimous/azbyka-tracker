package ru.azbykamebeli.tracker.infrastructure;

import android.app.AlarmManager;
import android.app.PendingIntent;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;

import javax.inject.Inject;

import dagger.hilt.android.qualifiers.ApplicationContext;

public final class AlarmUtil implements IAlarmUtil {
    private final Context
            __context;

    @Inject
    AlarmUtil(@ApplicationContext final Context __context) {
        this.__context = __context;
    }
    //---

    public void schedule(final Class<? extends BroadcastReceiver> __class_broadcast_receiver, final long __long_timeout, final String __string_action) {
        this.schedule(__class_broadcast_receiver, __long_timeout, __string_action, 1);
    }
    //---

    public void schedule(final Class<? extends BroadcastReceiver> __class_broadcast_receiver, final long __long_timeout, final String __string_action, final int __int_code) {
        final Intent
                __intent = new Intent(this.__context, __class_broadcast_receiver);

        final AlarmManager
                __alarm_manager = (AlarmManager) this.__context.getSystemService(Context.ALARM_SERVICE);

        __intent.setAction(__string_action);

        __alarm_manager.setExactAndAllowWhileIdle(AlarmManager.RTC_WAKEUP, __long_timeout + System.currentTimeMillis(), PendingIntent.getBroadcast(this.__context, __int_code, __intent, PendingIntent.FLAG_IMMUTABLE | PendingIntent.FLAG_CANCEL_CURRENT));
    }
}
