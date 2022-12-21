package ru.azbykamebeli.tracker.infrastructure.service;

import android.annotation.SuppressLint;
import android.app.Notification;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.app.Service;
import android.content.Context;
import android.content.Intent;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.IBinder;

import javax.inject.Inject;

import dagger.hilt.android.AndroidEntryPoint;
import ru.azbykamebeli.tracker.R;
import ru.azbykamebeli.tracker.application.INetworkInteractor;
import ru.azbykamebeli.tracker.domain.ISettingsRepository;
import ru.azbykamebeli.tracker.domain.model.SettingsModel;
import ru.azbykamebeli.tracker.presentation.activity.MainActivity;

@AndroidEntryPoint
public final class MainService extends Service {
    @Inject
    protected INetworkInteractor
            _network_interactor;

    @Inject
    protected ISettingsRepository
            _settings_repository;

    private LocationManager
            __location_manager = null;

    private final LocationListener
            __location_listener = new LocationListener() {
        @Override
        public void onLocationChanged(final Location __location) {
            _network_interactor.sendCurrentLocation(__location.getLatitude(), __location.getLongitude());
        }
    };

    @Override
    public IBinder onBind(final Intent __intent) {
        return null;
    }
    //---

    @Override
    public int onStartCommand(final Intent __intent, final int __int_flags, final int __int_start_id) {
        this.startForeground(1, new Notification.Builder(this, this.createNotificationChannel("main", "AzbykaTracker")).setContentTitle("AzbykaTracker").setSmallIcon(R.drawable.ic_launcher_foreground).setContentText("Идёт отслеживание местоположения").setContentIntent(PendingIntent.getActivity(this, 0, new Intent(this, MainActivity.class), PendingIntent.FLAG_IMMUTABLE)).build());

        return START_STICKY;
    }
    //---

    @SuppressLint("MissingPermission")
    @Override
    public void onCreate() {
        super.onCreate();

        final SettingsModel
                __settings_model = this._settings_repository.getSettings().orElseGet(SettingsModel::new);

        this.__location_manager = (LocationManager) this.getApplicationContext().getSystemService(Context.LOCATION_SERVICE);

        // todo перенести в слушатель изменения значений (отписывать, подписывать заново)
        this.__location_manager.requestLocationUpdates(LocationManager.FUSED_PROVIDER, __settings_model.getLocationInterval(), __settings_model.getLocationDistance(), this.__location_listener);
    }
    //---

    @Override
    public void onDestroy() {
        this.__location_manager.removeUpdates(this.__location_listener);

        super.onDestroy();
    }
    //---

    private String createNotificationChannel(final String __string_channel_id, final String __string_channel_name) {
        final NotificationManager
                __notification_manager = (NotificationManager) this.getSystemService(Context.NOTIFICATION_SERVICE);

        final NotificationChannel
                __notification_channel = new NotificationChannel(__string_channel_id, __string_channel_name, NotificationManager.IMPORTANCE_NONE);

        __notification_channel.setLockscreenVisibility(Notification.VISIBILITY_PRIVATE);

        __notification_manager.createNotificationChannel(__notification_channel);

        return __string_channel_id;
    }
}
