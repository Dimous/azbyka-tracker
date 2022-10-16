package ru.azbykamebeli.tracker.data.mapper;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import javax.inject.Inject;

import retrofit2.Converter;
import retrofit2.converter.gson.GsonConverterFactory;
import ru.azbykamebeli.tracker.data.mapper.adapter.VehicleAdapter;
import ru.azbykamebeli.tracker.data.mapper.adapter.VehicleLocationAdapter;
import ru.azbykamebeli.tracker.domain.model.VehicleLocationModel;
import ru.azbykamebeli.tracker.domain.model.VehicleModel;

public final class MainMapper implements IMainMapper {
    private final Gson
            __gson;

    private final Converter.Factory
            __gson_converter_factory;

    @Inject
    public MainMapper() {
        this.__gson_converter_factory = GsonConverterFactory.create(this.__gson = new GsonBuilder().registerTypeAdapter(VehicleModel.class, new VehicleAdapter()).registerTypeAdapter(VehicleLocationModel.class, new VehicleLocationAdapter()).create());
    }
    //---

    public Converter.Factory getConverterFactory() {
        return this.__gson_converter_factory;
    }
    //---

    /**
     * мог бы быть обобщённый toDomain, а fromDomain бы через перегрузку
     * public T toDomain(final String __string_json, Class<T> __class_t)
     */
    public VehicleModel toVehicle(final String __string_json) {
        return this.__gson.fromJson(__string_json, VehicleModel.class);
    }
    //---

    public String fromVehicle(final VehicleModel __vehicle_model) {
        return this.__gson.toJson(__vehicle_model);
    }
}
