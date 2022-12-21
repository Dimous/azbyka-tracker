package ru.azbykamebeli.tracker.presentation.view_model;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import javax.inject.Inject;

import dagger.hilt.android.lifecycle.HiltViewModel;
import ru.azbykamebeli.tracker.domain.ISettingsRepository;
import ru.azbykamebeli.tracker.domain.model.SettingsModel;

@HiltViewModel
public class SettingsDialogViewModel extends ViewModel {
    public final MutableLiveData<String>
            base_url = new MutableLiveData<>(),
            location_distance = new MutableLiveData<>(),
            location_interval = new MutableLiveData<>();

    private final ISettingsRepository
            __settings_repository;

    @Inject
    SettingsDialogViewModel(final ISettingsRepository __settings_repository) {
        this.__settings_repository = __settings_repository;
    }
    //---

    public void onInitialize() {
        this.__settings_repository.getSettings().ifPresent(
                __settings_model -> {
                    this.base_url.postValue(
                            __settings_model.getBaseUrl()
                    );

                    this.location_distance.postValue(
                            Float.toString(__settings_model.getLocationDistance())
                    );

                    this.location_interval.postValue(
                            Integer.toString(__settings_model.getLocationInterval())
                    );
                }
        );
    }
    //---

    public boolean onBaseUrlAction(final int __int_action_id) {
        final SettingsModel
                __settings_model = this.__settings_repository.getSettings().orElseGet(SettingsModel::new);

        __settings_model.setBaseUrl(this.base_url.getValue());

        if (__settings_model.isValid()) {
            this.__settings_repository.setSettings(__settings_model);
        }

        return false;
    }
    //---

    public boolean onLocationDistanceAction(final int __int_action_id) {
        final SettingsModel
                __settings_model = this.__settings_repository.getSettings().orElseGet(SettingsModel::new);

        __settings_model.setLocationDistance(Float.parseFloat(this.location_distance.getValue()));

        if (__settings_model.isValid()) {
            this.__settings_repository.setSettings(__settings_model);
        }

        return false;
    }
    //---

    public boolean onLocationIntervalAction(final int __int_action_id) {
        final SettingsModel
                __settings_model = this.__settings_repository.getSettings().orElseGet(SettingsModel::new);

        __settings_model.setLocationInterval(Integer.parseInt(this.location_interval.getValue()));

        if (__settings_model.isValid()) {
            this.__settings_repository.setSettings(__settings_model);
        }

        return false;
    }
}
