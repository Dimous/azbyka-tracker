package ru.azbykamebeli.tracker.presentation.di;

import javax.inject.Singleton;

import dagger.Binds;
import dagger.Module;
import dagger.hilt.InstallIn;
import dagger.hilt.components.SingletonComponent;
import ru.azbykamebeli.tracker.presentation.mapper.IRegistrationPlateMapper;
import ru.azbykamebeli.tracker.presentation.mapper.RegistrationPlateMapper;

@Module
@InstallIn(SingletonComponent.class)
public abstract class PresentationModule {
    @Binds
    @Singleton
    public abstract IRegistrationPlateMapper bindRegistrationPlateMapper(final RegistrationPlateMapper __);
}
