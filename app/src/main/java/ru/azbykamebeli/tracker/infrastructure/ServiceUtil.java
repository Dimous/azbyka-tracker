package ru.azbykamebeli.tracker.infrastructure;

import android.app.ActivityManager;
import android.app.Service;
import android.content.Context;
import android.content.Intent;

import javax.inject.Inject;

import dagger.hilt.android.qualifiers.ApplicationContext;

public final class ServiceUtil implements IServiceUtil {
    private final Context
            __context;

    @Inject
    ServiceUtil(@ApplicationContext final Context __context) {
        this.__context = __context;
    }
    //---

    public boolean isRunning(final Class<? extends Service> __class_service) {
        final ActivityManager
                __activity_manager = (ActivityManager) this.__context.getSystemService(Context.ACTIVITY_SERVICE);

        // метод устарел, но для обратной совместимости оставлено получение сервисов, запущенных прилагой
        return __activity_manager.getRunningServices(Integer.MAX_VALUE).stream().anyMatch(__running_service_info -> __class_service.getName().equalsIgnoreCase(__running_service_info.service.getClassName()));
    }
    //---

    public void start(final Class<? extends Service> __class_service) {
        this.start(__class_service, false);
    }

    public void start(final Class<? extends Service> __class_service, final boolean __boolean_is_foreground) {
        if (!this.isRunning(__class_service)) {
            try {
                final Intent
                        __intent = new Intent(this.__context, __class_service);

                if (__boolean_is_foreground) {
                    this.__context.startForegroundService(__intent);
                } else {
                    this.__context.startService(__intent);
                }
            } catch (final Exception __exception) {
            }
        }
    }
}
