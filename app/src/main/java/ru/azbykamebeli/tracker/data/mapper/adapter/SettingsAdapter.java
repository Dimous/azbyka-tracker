package ru.azbykamebeli.tracker.data.mapper.adapter;

import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonDeserializer;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParseException;
import com.google.gson.JsonSerializationContext;
import com.google.gson.JsonSerializer;

import java.lang.reflect.Type;

import ru.azbykamebeli.tracker.domain.model.SettingsModel;

public final class SettingsAdapter implements JsonSerializer<SettingsModel>, JsonDeserializer<SettingsModel> {
    private static final String FIELD_BASE_URL = "base_url", FIELD_LOCATION_INTERVAL = "location_interval", FIELD_LOCATION_DISTANCE = "location_distance";

    @Override
    public JsonElement serialize(final SettingsModel __settings_model, final Type __type, final JsonSerializationContext __json_serialization_context) {
        final JsonObject
                __json_object = new JsonObject();

        __json_object.addProperty(FIELD_BASE_URL, __settings_model.getBaseUrl());
        __json_object.addProperty(FIELD_LOCATION_INTERVAL, __settings_model.getLocationInterval());
        __json_object.addProperty(FIELD_LOCATION_DISTANCE, __settings_model.getLocationDistance());

        return __json_object;
    }
    //---

    @Override
    public SettingsModel deserialize(final JsonElement __json_element, final Type __type, final JsonDeserializationContext __json_deserialization_context) throws JsonParseException {
        final JsonObject
                __json_object = __json_element.getAsJsonObject();

        return new SettingsModel(__json_object.getAsJsonPrimitive(FIELD_BASE_URL).getAsString(), __json_object.getAsJsonPrimitive(FIELD_LOCATION_INTERVAL).getAsInt(), __json_object.getAsJsonPrimitive(FIELD_LOCATION_DISTANCE).getAsFloat());
    }
}
