package ru.azbykamebeli.tracker.data.mapper;

import retrofit2.Converter;
import ru.azbykamebeli.tracker.domain.model.VehicleModel;

public interface IMainMapper {
    Converter.Factory getConverterFactory();

    VehicleModel toVehicle(final String __string_json);

    String fromVehicle(final VehicleModel __vehicle_model);
}
