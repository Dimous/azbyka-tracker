package ru.azbykamebeli.tracker.data.local.repository;

import java.util.Optional;

import javax.inject.Inject;

import io.reactivex.rxjava3.schedulers.Schedulers;
import ru.azbykamebeli.tracker.data.local.data_source.ISharedPreferencesDataSource;
import ru.azbykamebeli.tracker.data.mapper.IMainMapper;
import ru.azbykamebeli.tracker.domain.ISettingsRepository;
import ru.azbykamebeli.tracker.domain.model.SettingsModel;

public class SettingsRepository implements ISettingsRepository {
    private final IMainMapper
            __main_mapper;

    private SettingsModel
            __settings_model_current = null;

    // подменяемый через квалифаер, убрать специфику
    private final ISharedPreferencesDataSource
            __shared_preferences_data_source;

    private static final String SOURCE_KEY = "8300d13d-5529-49b8-8541-99defa9acef2";

    @Inject
    public SettingsRepository(final IMainMapper __main_mapper, final ISharedPreferencesDataSource __shared_preferences_data_source) {
        this.__main_mapper = __main_mapper;
        this.__shared_preferences_data_source = __shared_preferences_data_source;

        this.__shared_preferences_data_source.getObservable().observeOn(Schedulers.io()).subscribeOn(Schedulers.io()).filter(__string_key -> __string_key.equals(SOURCE_KEY)).subscribe( // если будет сабджект, это будет в ComposableSubscription
                __string_key -> this.resetCurrentSettings()
        );
    }
    //---

    @Override
    public Optional<SettingsModel> getSettings() {
        if (null == this.__settings_model_current) {
            this.resetCurrentSettings();
        }

        return Optional.ofNullable(this.__settings_model_current);
    }
    //---

    @Override
    public void setSettings(final SettingsModel __settings_model) {
        this.__shared_preferences_data_source.set(SOURCE_KEY, this.__main_mapper.fromSettings(__settings_model));
    }
    //---

    private void resetCurrentSettings() {
        this.__settings_model_current = this.__main_mapper.toSettings(this.__shared_preferences_data_source.get(SOURCE_KEY));
    }
}
