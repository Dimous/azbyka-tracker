package ru.azbykamebeli.tracker.data.local.repository;

import java.util.Optional;

import javax.inject.Inject;

import io.reactivex.rxjava3.schedulers.Schedulers;
import ru.azbykamebeli.tracker.data.local.data_source.ISharedPreferencesDataSource;
import ru.azbykamebeli.tracker.data.mapper.IMainMapper;
import ru.azbykamebeli.tracker.domain.IVehicleRepository;
import ru.azbykamebeli.tracker.domain.model.VehicleModel;

public final class VehicleRepository implements IVehicleRepository {
    private final IMainMapper
            __main_mapper;

    private VehicleModel
            __vehicle_model_current = null;

    // подменяемый через квалифаер, убрать специфику
    private final ISharedPreferencesDataSource
            __shared_preferences_data_source;

    private static final String SOURCE_KEY = "vehicle";

    @Inject
    VehicleRepository(final IMainMapper __main_mapper, final ISharedPreferencesDataSource __shared_preferences_data_source) {
        this.__main_mapper = __main_mapper;
        this.__shared_preferences_data_source = __shared_preferences_data_source;

        // finalize может не вызваться, а AutoClosable -- для try-with-resource; репозиторий синглтон уровня приложения, можно не беспокоиться об отписке
        this.__shared_preferences_data_source.getObservable().observeOn(Schedulers.io()).subscribeOn(Schedulers.io()).filter(__string_key -> SOURCE_KEY == __string_key).subscribe( // если будет сабджект, это будет в ComposableSubscription
                __string_key -> {
                    this.resetCurrentVehicle(); // если будет сабджект, перенести в doOnNext, чтобы current всегда освежался первым
                }
        );

        // todo завести сабджект, подписывающийся на отфильтрованный обзёрвабл, пусть делается mapTo с getVehicle
    }
    //---

    @Override
    public Optional<VehicleModel> getVehicle() {
        if (null == this.__vehicle_model_current) {
            this.resetCurrentVehicle();
        }

        return Optional.ofNullable(this.__vehicle_model_current);
    }
    //---

    @Override
    public void setVehicle(final VehicleModel __vehicle_model) {
        this.__shared_preferences_data_source.set(SOURCE_KEY, this.__main_mapper.fromVehicle(__vehicle_model));
    }
    //---

    private void resetCurrentVehicle() {
        this.__vehicle_model_current = this.__main_mapper.toVehicle(this.__shared_preferences_data_source.get(SOURCE_KEY));
    }
}
