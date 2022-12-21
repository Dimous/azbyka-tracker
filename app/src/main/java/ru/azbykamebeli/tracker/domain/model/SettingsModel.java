package ru.azbykamebeli.tracker.domain.model;

import android.util.Patterns;

import androidx.annotation.NonNull;

import java.util.Objects;

import ru.azbykamebeli.tracker.content.Constant;

public final class SettingsModel implements IValidatable {
    private String
            __string_base_url;

    private int
            __int_location_interval;

    private float
            __float_location_distance;

    public SettingsModel() {
        this(Constant.BASE_URL, Constant.LOCATION_INTERVAL, Constant.LOCATION_DISTANCE);
    }

    public SettingsModel(final String __string_base_url, final int __int_location_interval, final float __float_location_distance) {
        this.setBaseUrl(__string_base_url);
        this.setLocationInterval(__int_location_interval);
        this.setLocationDistance(__float_location_distance);
    }
    //---

    public String getBaseUrl() {
        return this.__string_base_url;
    }

    public void setBaseUrl(final String __string_base_url) {
        this.__string_base_url = __string_base_url;
    }
    //---

    public int getLocationInterval() {
        return this.__int_location_interval;
    }

    public void setLocationInterval(final int __int_location_interval) {
        this.__int_location_interval = __int_location_interval;
    }
    //---

    public float getLocationDistance() {
        return this.__float_location_distance;
    }

    public void setLocationDistance(final float __float_location_distance) {
        this.__float_location_distance = __float_location_distance;
    }
    //---

    @Override
    public int hashCode() {
        return Objects.hash(this.__string_base_url, this.__int_location_interval, this.__float_location_distance);
    }
    //---

    @Override
    public boolean equals(final Object __object_target) {
        return null != __object_target && (this == __object_target || __object_target instanceof SettingsModel && Objects.equals(this.__string_base_url, ((SettingsModel) __object_target).getBaseUrl()) && Objects.equals(this.__int_location_interval, ((SettingsModel) __object_target).getLocationInterval()) && Objects.equals(this.__float_location_distance, ((SettingsModel) __object_target).getLocationDistance()));
    }
    //---

    @NonNull
    @Override
    public String toString() {
        return this.getClass().getSimpleName() + "(base url: " + this.__string_base_url + ", location interval: " + this.__int_location_interval + ", location distance: " + this.__float_location_distance + ")";
    }
    //---

    @Override
    public boolean isValid() {
        return null != this.__string_base_url && !this.__string_base_url.isEmpty() && Patterns.WEB_URL.matcher(this.__string_base_url).matches() && 0 <= this.__int_location_interval && 0f <= this.__float_location_distance;
    }
}
