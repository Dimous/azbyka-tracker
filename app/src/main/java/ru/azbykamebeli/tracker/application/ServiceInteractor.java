package ru.azbykamebeli.tracker.application;

import static ru.azbykamebeli.tracker.content.Intent.ACTION_HEART_BEAT;

import javax.inject.Inject;

import ru.azbykamebeli.tracker.infrastructure.IAlarmUtil;
import ru.azbykamebeli.tracker.infrastructure.IServiceUtil;
import ru.azbykamebeli.tracker.infrastructure.broadcast_receiver.MainBroadcastReceiver;
import ru.azbykamebeli.tracker.infrastructure.service.MainService;

public final class ServiceInteractor implements IServiceInteractor {
    private final IAlarmUtil
            __alarm_util;

    private final IServiceUtil
            __service_util;

    @Inject
    ServiceInteractor(final IAlarmUtil __alarm_util, final IServiceUtil __service_util) {
        this.__alarm_util = __alarm_util;
        this.__service_util = __service_util;
    }
    //---

    /**
     * Хитро зацикливается
     */
    public void start() {
        this.__service_util.start(MainService.class, true);

        // todo интервал запрашивать с ConfigurationRepository
        // нельзя запускать foreground service, если приложение в фоне
        this.__alarm_util.schedule(MainBroadcastReceiver.class, 15_000, ACTION_HEART_BEAT);
    }
}
