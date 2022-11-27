package ru.azbykamebeli.tracker.data.mapper.adapter;

import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonDeserializer;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParseException;
import com.google.gson.JsonSerializationContext;
import com.google.gson.JsonSerializer;

import java.lang.reflect.Type;

import ru.azbykamebeli.tracker.domain.model.VehicleModel;

public final class VehicleAdapter implements JsonSerializer<VehicleModel>, JsonDeserializer<VehicleModel> {
    @Override
    public JsonElement serialize(final VehicleModel __vehicle_model, final Type __type, final JsonSerializationContext __json_serialization_context) {
        final JsonObject
                __json_object = new JsonObject();

        __json_object.addProperty("registration_plate", __vehicle_model.getRegistrationPlate().getValue());

        return __json_object;
    }
    //---

    @Override
    public VehicleModel deserialize(final JsonElement __json_element, final Type __type, final JsonDeserializationContext __json_deserialization_context) throws JsonParseException {
        return new VehicleModel(new VehicleModel.RegistrationPlateVO(__json_element.getAsJsonObject().getAsJsonPrimitive("registration_plate").getAsString()));
    }
}
