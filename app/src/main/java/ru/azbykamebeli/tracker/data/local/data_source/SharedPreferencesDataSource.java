package ru.azbykamebeli.tracker.data.local.data_source;

import android.content.Context;
import android.content.SharedPreferences;

import javax.inject.Inject;

import dagger.hilt.android.qualifiers.ApplicationContext;
import io.reactivex.rxjava3.core.Observable;

public final class SharedPreferencesDataSource implements ISharedPreferencesDataSource {
    private final Observable<String>
            __observable;

    private final SharedPreferences
            __shared_preferences;

    @Inject
    SharedPreferencesDataSource(@ApplicationContext final Context __context) {
        this.__shared_preferences = __context.getSharedPreferences(__context.getPackageName(), Context.MODE_PRIVATE);

        this.__observable = Observable.create(
                __observable_emitter -> {
                    final SharedPreferences.OnSharedPreferenceChangeListener
                            __on_shared_preferences_change_listener = (__shared_preferences, __string_key) -> {
                        if (!__observable_emitter.isDisposed()) {
                            __observable_emitter.onNext(__string_key);
                        }
                    };

                    this.__shared_preferences.registerOnSharedPreferenceChangeListener(__on_shared_preferences_change_listener);

                    __observable_emitter.setCancellable(
                            () -> this.__shared_preferences.unregisterOnSharedPreferenceChangeListener(__on_shared_preferences_change_listener)
                    );
                }
        );
    }
    //---

    @Override
    public Observable<String> getObservable() {
        return this.__observable;
    }
    //---

    @Override
    public void clear() {
        this.__shared_preferences.edit().clear().apply();
    }
    //---

    @Override
    public String get(final String __string_key) {
        return this.get(__string_key, null);
    }
    //---

    @Override
    public boolean has(final String __string_key) {
        return this.__shared_preferences.contains(__string_key);
    }
    //---

    @Override
    public void remove(final String __string_key) {
        this.__shared_preferences.edit().remove(__string_key).apply();
    }
    //---

    @Override
    public void set(final String __string_key, final String __string_value) {
        this.__shared_preferences.edit().putString(__string_key, __string_value).apply();
    }
    //---

    @Override
    public String get(final String __string_key, final String __string_default_value) {
        return this.__shared_preferences.getString(__string_key, __string_default_value);
    }
}
