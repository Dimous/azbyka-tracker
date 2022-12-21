package ru.azbykamebeli.tracker.domain;

import java.util.Optional;

import ru.azbykamebeli.tracker.domain.model.SettingsModel;

public interface ISettingsRepository {
    Optional<SettingsModel> getSettings();

    void setSettings(final SettingsModel __settings_model);
}
