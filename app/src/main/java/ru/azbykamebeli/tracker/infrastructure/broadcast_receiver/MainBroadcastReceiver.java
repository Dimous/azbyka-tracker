package ru.azbykamebeli.tracker.infrastructure.broadcast_receiver;

import static ru.azbykamebeli.tracker.content.Intent.ACTION_HEART_BEAT;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;

import javax.inject.Inject;

import dagger.hilt.android.AndroidEntryPoint;
import ru.azbykamebeli.tracker.application.IServiceInteractor;

@AndroidEntryPoint
public final class MainBroadcastReceiver extends BroadcastReceiver {
    @Inject
    protected IServiceInteractor
            _service_interactor;

    @Override
    public void onReceive(final Context __context, final Intent __intent) {
        if (null != __intent) {
            switch (__intent.getAction()) {
                // case ACTION_BOOT_COMPLETED:
                case ACTION_HEART_BEAT: // чтобы перезапускать, если в режиме сна ведро решит остановить сервис
                    this._service_interactor.start();
                    break;
            }
        }
    }
}
