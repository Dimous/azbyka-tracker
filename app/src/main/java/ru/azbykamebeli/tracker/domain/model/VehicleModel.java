package ru.azbykamebeli.tracker.domain.model;

import java.util.Objects;
import java.util.regex.Pattern;

/**
 * Поведения нет, анемичная модель
 */
public final class VehicleModel implements IValidatable {
    private final RegistrationPlateVO
            __registration_plate;

    public VehicleModel() {
        this.__registration_plate = new RegistrationPlateVO();
    }
    //---

    public RegistrationPlateVO getRegistrationPlate() {
        return this.__registration_plate;
    }
    //---

    @Override
    public int hashCode() {
        return Objects.hash(this.__registration_plate);
    }
    //---

    @Override
    public boolean equals(final Object __object_target) {
        return null != __object_target && (this == __object_target || __object_target instanceof VehicleModel && Objects.equals(this.__registration_plate, ((VehicleModel) __object_target).getRegistrationPlate()));
    }
    //---

    @Override
    public String toString() {
        return new StringBuilder().append(this.getClass().getSimpleName()).append("(registration plate: ").append(this.getRegistrationPlate()).append(")").toString();
    }
    //---

    @Override
    public boolean isValid() {
        return this.__registration_plate.isValid();
    }
    //---

    public final class RegistrationPlateVO {
        private String
                __string_value = null;

        private final Pattern
                __pattern_validation_rule;

        public RegistrationPlateVO() {
            // https://ru.stackoverflow.com/a/824910
            this.__pattern_validation_rule = Pattern.compile("(^[АВЕКМНОРСТУХ]{2}\\d{3}(?<!000)\\d{2,3}$)|(^[АВЕКМНОРСТУХ]{2}\\d{4}(?<!0000)\\d{2,3}$)|(^[АВЕКМНОРСТУХ]\\d{3}(?<!000)[АВЕКМНОРСТУХ]{2}\\d{2,3}$)|(^\\d{4}(?<!0000)[АВЕКМНОРСТУХ]{2}\\d{2,3}$)|(^[АВЕКМНОРСТУХ]{2}\\d{3}(?<!000)[АВЕКМНОРСТУХ]\\d{2,3}$)|(^Т[АВЕКМНОРСТУХ]{2}\\d{3}(?<!000)\\d{2,3}$)", Pattern.CASE_INSENSITIVE);
        }
        //---

        public String getValue() {
            return this.__string_value;
        }

        public void setValue(final String __string_value) {
            this.__string_value = __string_value;
        }
        //---

        @Override
        public int hashCode() {
            return Objects.hash(this.__string_value);
        }
        //---

        @Override
        public boolean equals(final Object __object_target) {
            return null != __object_target && (this == __object_target || __object_target instanceof RegistrationPlateVO && Objects.equals(this.__string_value, ((RegistrationPlateVO) __object_target).getValue()));
        }
        //---

        @Override
        public String toString() {
            return new StringBuilder().append(this.getClass().getSimpleName()).append("(value: ").append(this.getValue()).append(")").toString();
        }
        //---

        private boolean isValid() {
            return null != this.__string_value && !this.__string_value.isEmpty() && this.__pattern_validation_rule.matcher(this.__string_value).matches();
        }
    }
}
