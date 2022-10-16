package ru.azbykamebeli.tracker.infrastructure;

import android.content.BroadcastReceiver;

public interface IAlarmUtil {
    void schedule(final Class<? extends BroadcastReceiver> __class_broadcast_receiver, final long __long_timeout, final String __string_action);

    void schedule(final Class<? extends BroadcastReceiver> __class_broadcast_receiver, final long __long_timeout, final String __string_action, final int __int_code);
}
