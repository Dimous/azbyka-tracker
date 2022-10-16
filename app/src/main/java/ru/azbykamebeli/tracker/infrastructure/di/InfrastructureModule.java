package ru.azbykamebeli.tracker.infrastructure.di;

import javax.inject.Singleton;

import dagger.Binds;
import dagger.Module;
import dagger.hilt.InstallIn;
import dagger.hilt.components.SingletonComponent;
import ru.azbykamebeli.tracker.infrastructure.AlarmUtil;
import ru.azbykamebeli.tracker.infrastructure.ConnectivityUtil;
import ru.azbykamebeli.tracker.infrastructure.IAlarmUtil;
import ru.azbykamebeli.tracker.infrastructure.IConnectivityUtil;
import ru.azbykamebeli.tracker.infrastructure.IServiceUtil;
import ru.azbykamebeli.tracker.infrastructure.ServiceUtil;

@Module
@InstallIn(SingletonComponent.class)
public abstract class InfrastructureModule {
    @Binds
    @Singleton
    public abstract IAlarmUtil bindAlarmUtil(final AlarmUtil __);
    //---

    @Binds
    @Singleton
    public abstract IServiceUtil bindServiceUtil(final ServiceUtil __);
    //---

    @Binds
    @Singleton
    public abstract IConnectivityUtil bindConnectivityUtil(final ConnectivityUtil __);
}
