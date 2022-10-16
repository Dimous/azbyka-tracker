package ru.azbykamebeli.tracker.infrastructure;

import android.app.Service;

public interface IServiceUtil {
    void start(final Class<? extends Service> __class_service);

    boolean isRunning(final Class<? extends Service> __class_service);

    void start(final Class<? extends Service> __class_service, final boolean __boolean_is_foreground);
}
