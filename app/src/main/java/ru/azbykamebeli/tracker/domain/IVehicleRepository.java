package ru.azbykamebeli.tracker.domain;

import java.util.Optional;

import ru.azbykamebeli.tracker.domain.model.VehicleModel;

public interface IVehicleRepository {
    Optional<VehicleModel> getVehicle();

    void setVehicle(final VehicleModel __vehicle_model);
}
