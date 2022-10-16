package ru.azbykamebeli.tracker.presentation.mapper;

import javax.inject.Inject;

import ru.azbykamebeli.tracker.domain.model.VehicleModel;

public final class RegistrationPlateMapper implements IRegistrationPlateMapper {
    @Inject
    RegistrationPlateMapper() {}
    //---

    /**
     * из-за двустороннего связывания, перевод делается не в DTO
     */
    public String toView(final VehicleModel.RegistrationPlateVO __registration_plate) {
        // форматировать, приводить в понятный представлению вид
        return __registration_plate.getValue();
    }
}
