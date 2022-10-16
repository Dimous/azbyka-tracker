package ru.azbykamebeli.tracker.presentation.mapper;

import ru.azbykamebeli.tracker.domain.model.VehicleModel;

public interface IRegistrationPlateMapper {
    String toView(final VehicleModel.RegistrationPlateVO __registration_plate);
}
