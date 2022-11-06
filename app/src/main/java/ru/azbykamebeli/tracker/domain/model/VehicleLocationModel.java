package ru.azbykamebeli.tracker.domain.model;

import java.util.Objects;

/**
 * Это корень агрегации
 */
public final class VehicleLocationModel implements IValidatable {
    private final LatitudeVO
            __latitude;

    private final LongitudeVO
            __longitude;

    private VehicleModel
            __vehicle = null;

    public VehicleLocationModel() {
        this.__latitude = new LatitudeVO();
        this.__longitude = new LongitudeVO();
    }
    //---

    public LatitudeVO getLatitude() {
        return this.__latitude;
    }
    //---

    public LongitudeVO getLongitude() {
        return this.__longitude;
    }
    //---

    public VehicleModel getVehicle() {
        return this.__vehicle;
    }

    public void setVehicle(final VehicleModel __vehicle) {
        this.__vehicle = __vehicle;
    }
    //---

    @Override
    public boolean isValid() {
        return this.__latitude.isValid() && this.__longitude.isValid() && null != this.__vehicle && this.__vehicle.isValid();
    }
    //---

    @Override
    public int hashCode() {
        return Objects.hash(this.__vehicle, this.__latitude, this.__longitude);
    }
    //---

    @Override
    public boolean equals(final Object __object_target) {
        return null != __object_target && (this == __object_target || __object_target instanceof VehicleLocationModel && Objects.equals(this.__vehicle, ((VehicleLocationModel) __object_target).getVehicle()) && Objects.equals(this.__latitude, ((VehicleLocationModel) __object_target).getLatitude()) && Objects.equals(this.__longitude, ((VehicleLocationModel) __object_target).getLongitude()));
    }
    //---

    @Override
    public String toString() {
        return new StringBuilder().append(this.getClass().getSimpleName()).append("(latitude: ").append(this.getLatitude()).append(", longitude: ").append(this.getLongitude()).append(", vehicle: ").append(this.getVehicle()).append(")").toString();
    }
    //---

    public final class LatitudeVO {
        private double
                __double_value = 0.0;

        public double getValue() {
            return this.__double_value;
        }

        /**
         * VO немутабельны
         * по-хорошему, экземпляр должен пересоздаваться
         */
        public void setValue(final double __double_value) {
            this.__double_value = __double_value;
        }
        //---

        @Override
        public int hashCode() {
            return Objects.hash(this.__double_value);
        }
        //---

        @Override
        public boolean equals(final Object __object_target) {
            return null != __object_target && (this == __object_target || __object_target instanceof LatitudeVO && Objects.equals(this.__double_value, ((LatitudeVO) __object_target).getValue()));
        }
        //---

        @Override
        public String toString() {
            return new StringBuilder().append(this.getClass().getSimpleName()).append("(value: ").append(this.getValue()).append(")").toString();
        }
        //---

        private boolean isValid() {
            return Double.isFinite(this.__double_value) && 90 >= Math.abs(this.__double_value);
        }
    }
    //---

    public final class LongitudeVO {
        private double
                __double_value = 0.0;

        public double getValue() {
            return this.__double_value;
        }

        /**
         * VO немутабельны
         * по-хорошему, экземпляр должен пересоздаваться
         */
        public void setValue(final double __double_value) {
            this.__double_value = __double_value;
        }
        //---

        @Override
        public int hashCode() {
            return Objects.hash(this.__double_value);
        }
        //---

        @Override
        public boolean equals(final Object __object_target) {
            return null != __object_target && (this == __object_target || __object_target instanceof LongitudeVO && Objects.equals(this.__double_value, ((LongitudeVO) __object_target).getValue()));
        }
        //---

        @Override
        public String toString() {
            return new StringBuilder().append(this.getClass().getSimpleName()).append("(value: ").append(this.getValue()).append(")").toString();
        }
        //---

        private boolean isValid() {
            return Double.isFinite(this.__double_value) && 180 >= Math.abs(this.__double_value);
        }
    }
}
