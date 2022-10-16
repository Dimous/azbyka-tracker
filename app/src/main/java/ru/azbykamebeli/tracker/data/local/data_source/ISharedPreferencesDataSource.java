package ru.azbykamebeli.tracker.data.local.data_source;

import io.reactivex.rxjava3.core.Observable;

/**
 * todo переименовать в KeyValueDataSource, конкретизировать через квалифаеры
 */
public interface ISharedPreferencesDataSource {
    Observable<String> getObservable();

    void clear();

    String get(final String __string_key);

    boolean has(final String __string_key);

    void remove(final String __string_key);

    void set(final String __string_key, final String __string_value);

    String get(final String __string_key, final String __string_default_value);

    // оперируем только строковыми
}
