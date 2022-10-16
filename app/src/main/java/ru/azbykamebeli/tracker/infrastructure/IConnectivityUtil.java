package ru.azbykamebeli.tracker.infrastructure;

import java.util.AbstractMap.SimpleImmutableEntry;

import io.reactivex.rxjava3.core.Observable;

public interface IConnectivityUtil {
    ConnectivityUtil.NetworkInfo getActiveNetworkInfo();

    Observable<SimpleImmutableEntry<ConnectivityUtil.EState, ConnectivityUtil.NetworkInfo>> getObservable();
}
