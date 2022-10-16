package ru.azbykamebeli.tracker.data.remote.repository;

import javax.inject.Inject;

import io.reactivex.rxjava3.core.Completable;
import ru.azbykamebeli.tracker.data.remote.data_source.IHTTPDataSource;
import ru.azbykamebeli.tracker.domain.IVehicleLocationRepository;
import ru.azbykamebeli.tracker.domain.model.VehicleLocationModel;

public final class VehicleLocationRepository implements IVehicleLocationRepository {
    // подменяемый через квалифаер, убрать специфику
    private final IHTTPDataSource
            __h_t_t_p_data_source;

    @Inject
    public VehicleLocationRepository(final IHTTPDataSource __h_t_t_p_data_source) {
        this.__h_t_t_p_data_source = __h_t_t_p_data_source;
    }
    //---

    @Override
    public Completable sendLocation(final VehicleLocationModel __vehicle_location_model) {
        return this.__h_t_t_p_data_source.sendLocation(__vehicle_location_model);
    }
}
