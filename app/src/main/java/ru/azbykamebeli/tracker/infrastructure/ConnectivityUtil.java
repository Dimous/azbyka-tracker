package ru.azbykamebeli.tracker.infrastructure;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.LinkProperties;
import android.net.Network;
import android.net.NetworkCapabilities;
import android.net.NetworkRequest;

import java.util.AbstractMap.SimpleImmutableEntry;
import java.util.Optional;

import javax.inject.Inject;

import dagger.hilt.android.qualifiers.ApplicationContext;
import io.reactivex.rxjava3.core.Observable;

public final class ConnectivityUtil implements IConnectivityUtil {
    private final ConnectivityManager
            __connectivity_manager;

    private final Observable<SimpleImmutableEntry<EState, NetworkInfo>>
            __observable;

    @Inject
    ConnectivityUtil(@ApplicationContext final Context __context) {
        this.__connectivity_manager = __context.getSystemService(ConnectivityManager.class);

        this.__observable = Observable.create(
                __observable_emitter -> {
                    final ConnectivityManager.NetworkCallback
                            __network_callback = new ConnectivityManager.NetworkCallback() {
                        @Override
                        public void onLost(final Network __network) {
                            if (!__observable_emitter.isDisposed()) {
                                __observable_emitter.onNext(new SimpleImmutableEntry(EState.LOST, buildNetworkInfo(__network)));
                            }
                        }
                        //---

                        @Override
                        public void onAvailable(final Network __network) {
                            if (!__observable_emitter.isDisposed()) {
                                __observable_emitter.onNext(new SimpleImmutableEntry(EState.AVAILABLE, buildNetworkInfo(__network)));
                            }
                        }
                        //---

                        @Override
                        public void onLinkPropertiesChanged(final Network __network, final LinkProperties __link_properties) {
                            if (!__observable_emitter.isDisposed()) {
                                __observable_emitter.onNext(new SimpleImmutableEntry(EState.LINK_PROPERTIES_CHANGED, buildNetworkInfo(__network, __link_properties)));
                            }
                        }
                        //---

                        @Override
                        public void onCapabilitiesChanged(final Network __network, final NetworkCapabilities __network_capabilities) {
                            if (!__observable_emitter.isDisposed()) {
                                __observable_emitter.onNext(new SimpleImmutableEntry(EState.CAPABILITIES_CHANGED, buildNetworkInfo(__network, __network_capabilities)));
                            }
                        }
                    };

                    this.__connectivity_manager.registerNetworkCallback(new NetworkRequest.Builder().addCapability(NetworkCapabilities.NET_CAPABILITY_INTERNET).addCapability(NetworkCapabilities.NET_CAPABILITY_VALIDATED).addTransportType(NetworkCapabilities.TRANSPORT_WIFI).addTransportType(NetworkCapabilities.TRANSPORT_CELLULAR).build(), __network_callback);

                    __observable_emitter.setCancellable(
                            () -> {
                                this.__connectivity_manager.unregisterNetworkCallback(__network_callback);
                            }
                    );
                }
        );
    }
    //---

    @Override
    public Observable<SimpleImmutableEntry<EState, NetworkInfo>> getObservable() {
        return this.__observable;
    }
    //---

    @Override
    public NetworkInfo getActiveNetworkInfo() {
        return this.buildNetworkInfo(this.__connectivity_manager.getActiveNetwork());
    }
    //---

    private NetworkInfo buildNetworkInfo(final Network __network) {
        return this.buildNetworkInfo(__network, null, null);
    }

    private NetworkInfo buildNetworkInfo(final Network __network, final LinkProperties __link_properties) {
        return this.buildNetworkInfo(__network, __link_properties, null);
    }

    private NetworkInfo buildNetworkInfo(final Network __network, final NetworkCapabilities __network_capabilities) {
        return this.buildNetworkInfo(__network, null, __network_capabilities);
    }

    private NetworkInfo buildNetworkInfo(final Network __network, final LinkProperties __link_properties, final NetworkCapabilities __network_capabilities) {
        return new NetworkInfo(null == __link_properties ? this.__connectivity_manager.getLinkProperties(__network) : __link_properties, null == __network_capabilities ? this.__connectivity_manager.getNetworkCapabilities(__network) : __network_capabilities);
    }
    //---

    public enum EState {
        LOST,
        AVAILABLE,
        CAPABILITIES_CHANGED,
        LINK_PROPERTIES_CHANGED,
    }
    //---

    public final class NetworkInfo {
        private final Optional<LinkProperties>
                __link_properties;

        private final Optional<NetworkCapabilities>
                __network_capabilities;

        NetworkInfo(final LinkProperties __link_properties, final NetworkCapabilities __network_capabilities) {
            this.__link_properties = Optional.ofNullable(__link_properties);
            this.__network_capabilities = Optional.ofNullable(__network_capabilities);
        }
        //---

        public Optional<LinkProperties> getLinkProperties() {
            return this.__link_properties;
        }
        //---

        public Optional<NetworkCapabilities> getNetworkCapabilities() {
            return this.__network_capabilities;
        }
    }
}
