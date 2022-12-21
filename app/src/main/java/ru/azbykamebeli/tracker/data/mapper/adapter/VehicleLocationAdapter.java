package ru.azbykamebeli.tracker.data.mapper.adapter;

import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonSerializationContext;
import com.google.gson.JsonSerializer;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;

import ru.azbykamebeli.tracker.domain.model.VehicleLocationModel;
import ru.azbykamebeli.tracker.domain.model.VehicleModel;

public final class VehicleLocationAdapter implements JsonSerializer<VehicleLocationModel> {
    private static final String FIELD_LATITUDE = "latitude", FIELD_LONGITUDE = "longitude", FIELD_VEHICLE = "vehicle";

    @Override
    public JsonElement serialize(final VehicleLocationModel __vehicle_location_model, final Type __type, final JsonSerializationContext __json_serialization_context) {
        final JsonObject
                __json_object = new JsonObject();

        final TypeToken<VehicleModel>
                __type_token_vehicle_model = new TypeToken<VehicleModel>() {
        };

        __json_object.addProperty(FIELD_LATITUDE, __vehicle_location_model.getLatitude().getValue());
        __json_object.addProperty(FIELD_LONGITUDE, __vehicle_location_model.getLongitude().getValue());
        __json_object.add(FIELD_VEHICLE, __json_serialization_context.serialize(__vehicle_location_model.getVehicle(), __type_token_vehicle_model.getType()));

        return __json_object;
    }
}
