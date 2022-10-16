package ru.azbykamebeli.tracker.application;

import java.util.concurrent.CompletableFuture;

public interface INetworkInteractor {
    void sendCurrentLocation(final double __double_latitude, final double __double_longitude);

    void sendCurrentLocation(final double __double_latitude, final double __double_longitude, final CompletableFuture<Void> __completable_future_result);
}
