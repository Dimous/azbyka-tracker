package ru.azbykamebeli.tracker.domain;

import io.reactivex.rxjava3.core.Completable;
import ru.azbykamebeli.tracker.domain.model.VehicleLocationModel;

public interface IVehicleLocationRepository {
    Completable sendLocation(final VehicleLocationModel __vehicle_location_model);
}
