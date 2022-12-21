package ru.azbykamebeli.tracker.presentation.view_model;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import javax.inject.Inject;

import dagger.hilt.android.lifecycle.HiltViewModel;
import ru.azbykamebeli.tracker.application.IServiceInteractor;
import ru.azbykamebeli.tracker.domain.IVehicleRepository;
import ru.azbykamebeli.tracker.domain.model.VehicleModel;
import ru.azbykamebeli.tracker.presentation.mapper.IRegistrationPlateMapper;

@HiltViewModel
public final class MainViewModel extends ViewModel {
    public final MutableLiveData<String>
            toast = new MutableLiveData<>(),
            registration_plate = new MutableLiveData<>();

    public final MutableLiveData<Boolean>
            is_settings_dialog_visible = new MutableLiveData<>(false);

    private final IVehicleRepository
            __vehicle_repository;

    private final IServiceInteractor
            __service_interactor;

    private final IRegistrationPlateMapper
            __registration_plate_mapper;

    @Inject
    MainViewModel(final IVehicleRepository __vehicle_repository, final IServiceInteractor __service_interactor, final IRegistrationPlateMapper __registration_plate_mapper) {
        this.__vehicle_repository = __vehicle_repository;
        this.__service_interactor = __service_interactor;
        this.__registration_plate_mapper = __registration_plate_mapper;
    }
    //---

    public void onInitialize() {
        this.__service_interactor.start();

        this.__vehicle_repository.getVehicle().ifPresent(
                __vehicle_model -> this.registration_plate.postValue(this.__registration_plate_mapper.toView(__vehicle_model.getRegistrationPlate()))
        );
    }
    //---

    public void onSettingsButtonClick() {
        this.is_settings_dialog_visible.postValue(!this.is_settings_dialog_visible.getValue());
    }
    //---

    public boolean onRegistrationPlateAction(final int __int_action_id) {
        final VehicleModel
                __vehicle_model = this.__vehicle_repository.getVehicle().orElseGet(VehicleModel::new);

        __vehicle_model.setRegistrationPlate(new VehicleModel.RegistrationPlateVO(this.registration_plate.getValue()));

        if (__vehicle_model.isValid()) {
            this.__vehicle_repository.setVehicle(__vehicle_model);
        } else {
            this.toast.postValue("Введён неверный регистрационный номер");
        }

        return false;
    }
}
