package ru.azbykamebeli.tracker.application;

import android.net.NetworkCapabilities;

import java.util.Optional;
import java.util.concurrent.CompletableFuture;

import javax.inject.Inject;

import io.reactivex.rxjava3.schedulers.Schedulers;
import ru.azbykamebeli.tracker.domain.IVehicleLocationRepository;
import ru.azbykamebeli.tracker.domain.IVehicleRepository;
import ru.azbykamebeli.tracker.domain.model.VehicleLocationModel;
import ru.azbykamebeli.tracker.infrastructure.IConnectivityUtil;

public final class NetworkInteractor implements INetworkInteractor {
    private final IConnectivityUtil
            __connectivity_util;

    private final IVehicleRepository
            __vehicle_repository;

    private final IVehicleLocationRepository
            __vehicle_location_repository;

    @Inject
    NetworkInteractor(final IConnectivityUtil __connectivity_util, final IVehicleRepository __vehicle_repository, final IVehicleLocationRepository __vehicle_location_repository) {
        this.__connectivity_util = __connectivity_util;
        this.__vehicle_repository = __vehicle_repository;
        this.__vehicle_location_repository = __vehicle_location_repository;
    }
    //---

    public void sendCurrentLocation(final double __double_latitude, final double __double_longitude) {
        this.sendCurrentLocation(__double_latitude, __double_longitude, new CompletableFuture<>());
    }

    public void sendCurrentLocation(final double __double_latitude, final double __double_longitude, final CompletableFuture<Void> __completable_future_result) {
        // интеракторы не должны иметь состояния, поэтому делается опрос подключения, а не прослушивание
        final Optional<NetworkCapabilities>
                __optional_network_capabilities = this.__connectivity_util.getActiveNetworkInfo().getNetworkCapabilities();

        // ifPresentOrElse появился в 9 версии
        if (!__optional_network_capabilities.isPresent()) {
            __completable_future_result.completeExceptionally(new RuntimeException("Can't get network capabilities"));
        }

        __optional_network_capabilities.ifPresent(
                __network_capabilities -> {
                    final VehicleLocationModel
                            __vehicle_location_model;

                    if (__network_capabilities.hasCapability(NetworkCapabilities.NET_CAPABILITY_INTERNET) && __network_capabilities.hasCapability(NetworkCapabilities.NET_CAPABILITY_VALIDATED)) {
                        __vehicle_location_model = new VehicleLocationModel(new VehicleLocationModel.LatitudeVO(__double_latitude), new VehicleLocationModel.LongitudeVO(__double_longitude));

                        this.__vehicle_repository.getVehicle().ifPresent(__vehicle_location_model::setVehicle);

                        if (__vehicle_location_model.isValid()) {
                            this.__vehicle_location_repository.sendLocation(__vehicle_location_model).observeOn(Schedulers.io()).subscribeOn(Schedulers.io()).subscribe(() -> __completable_future_result.complete(null), __completable_future_result::completeExceptionally);
                        } else {
                            __completable_future_result.completeExceptionally(new RuntimeException("Invalid model"));
                        }
                    } else {
                        __completable_future_result.completeExceptionally(new RuntimeException("No internet connection"));
                    }
                }
        );
    }
}
