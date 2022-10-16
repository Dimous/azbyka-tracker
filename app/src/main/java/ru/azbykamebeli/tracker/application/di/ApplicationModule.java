package ru.azbykamebeli.tracker.application.di;

import javax.inject.Singleton;

import dagger.Binds;
import dagger.Module;
import dagger.hilt.InstallIn;
import dagger.hilt.components.SingletonComponent;
import ru.azbykamebeli.tracker.application.INetworkInteractor;
import ru.azbykamebeli.tracker.application.IServiceInteractor;
import ru.azbykamebeli.tracker.application.NetworkInteractor;
import ru.azbykamebeli.tracker.application.ServiceInteractor;

@Module
@InstallIn(SingletonComponent.class)
public abstract class ApplicationModule {
    @Binds
    @Singleton
    public abstract IServiceInteractor bindServiceInteractor(final ServiceInteractor __);
    //---

    @Binds
    @Singleton
    public abstract INetworkInteractor bindNetworkInteractor(final NetworkInteractor __);
}
