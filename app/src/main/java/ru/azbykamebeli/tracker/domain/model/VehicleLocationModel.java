package ru.azbykamebeli.tracker.domain.model;

import java.util.Objects;

/**
 * Это корень агрегации
 */
public final class VehicleLocationModel implements IValidatable {
    private VehicleModel
            __vehicle = null;

    private LatitudeVO
            __latitude = null;

    private LongitudeVO
            __longitude = null;

    public VehicleLocationModel() {
        this(null, null, null);
    }

    public VehicleLocationModel(final VehicleModel __vehicle, final LatitudeVO __latitude, final LongitudeVO __longitude) {
        this.setVehicle(__vehicle);
        this.setLatitude(__latitude);
        this.setLongitude(__longitude);
    }
    //---

    public LatitudeVO getLatitude() {
        return this.__latitude;
    }

    public void setLatitude(final LatitudeVO __latitude) {
        this.__latitude = __latitude;
    }
    //---

    public LongitudeVO getLongitude() {
        return this.__longitude;
    }

    public void setLongitude(final LongitudeVO __longitude) {
        this.__longitude = __longitude;
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

    public static final class LatitudeVO {
        private final double
                __double_value;

        public LatitudeVO(final double __double_value) {
            this.__double_value = __double_value;
        }
        //---

        /**
         * это должен быть toDouble
         */
        public double getValue() {
            return this.__double_value;
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

        /**
         * должно быть строковое представление toDouble
         */
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

    public static final class LongitudeVO {
        private final double
                __double_value;

        public LongitudeVO(final double __double_value) {
            this.__double_value = __double_value;
        }
        //---

        /**
         * это должен быть toDouble
         */
        public double getValue() {
            return this.__double_value;
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

        /**
         * должно быть строковое представление toDouble
         */
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
