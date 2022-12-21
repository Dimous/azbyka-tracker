package ru.azbykamebeli.tracker.data.di;

import javax.inject.Singleton;

import dagger.Binds;
import dagger.Module;
import dagger.Provides;
import dagger.hilt.InstallIn;
import dagger.hilt.components.SingletonComponent;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava3.RxJava3CallAdapterFactory;
import ru.azbykamebeli.tracker.data.local.data_source.ISharedPreferencesDataSource;
import ru.azbykamebeli.tracker.data.local.data_source.SharedPreferencesDataSource;
import ru.azbykamebeli.tracker.data.local.repository.SettingsRepository;
import ru.azbykamebeli.tracker.data.local.repository.VehicleRepository;
import ru.azbykamebeli.tracker.data.mapper.IMainMapper;
import ru.azbykamebeli.tracker.data.mapper.MainMapper;
import ru.azbykamebeli.tracker.data.remote.data_source.IHTTPDataSource;
import ru.azbykamebeli.tracker.data.remote.repository.VehicleLocationRepository;
import ru.azbykamebeli.tracker.domain.ISettingsRepository;
import ru.azbykamebeli.tracker.domain.IVehicleLocationRepository;
import ru.azbykamebeli.tracker.domain.IVehicleRepository;
import ru.azbykamebeli.tracker.domain.model.SettingsModel;

@Module
@InstallIn(SingletonComponent.class)
public abstract class DataModule {
    @Binds
    @Singleton
    public abstract IMainMapper bindMainMapper(final MainMapper __);
    //---

    @Binds
    @Singleton
    public abstract IVehicleRepository bindVehicleRepository(final VehicleRepository __);
    //---

    @Binds
    @Singleton
    public abstract ISettingsRepository bindSettingsRepository(final SettingsRepository __);
    //---

    @Binds
    @Singleton
    public abstract IVehicleLocationRepository bindVehicleLocationRepository(final VehicleLocationRepository __);
    //---

    @Binds
    @Singleton
    public abstract ISharedPreferencesDataSource bindSharedPreferencesDataSource(final SharedPreferencesDataSource __);
    //---

    /*
    @Provides
    @Singleton
    public static OkHttpClient provideOkHttpClient() {
        final HttpLoggingInterceptor
                __http_logging_interceptor = new HttpLoggingInterceptor();

        __http_logging_interceptor.setLevel(HttpLoggingInterceptor.Level.BODY);

        return new OkHttpClient.Builder().addInterceptor(__http_logging_interceptor).build();
    }
    */
    //---

    @Provides
    @Singleton
    public static IHTTPDataSource provideHTTPDataSource(/*final OkHttpClient __ok_http_client, */final IMainMapper __main_mapper, final ISettingsRepository __settings_repository) {
        final SettingsModel
                __settings_model = __settings_repository.getSettings().orElseGet(SettingsModel::new);

        // todo найти способ вызова пересоздания при изменении установок
        return new Retrofit.Builder()/*.client(__ok_http_client)*/.baseUrl(__settings_model.getBaseUrl()).addConverterFactory(__main_mapper.getConverterFactory()).addCallAdapterFactory(RxJava3CallAdapterFactory.create()).build().create(IHTTPDataSource.class);
    }
}
