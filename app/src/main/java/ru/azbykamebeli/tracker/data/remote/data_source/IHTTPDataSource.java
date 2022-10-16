package ru.azbykamebeli.tracker.data.remote.data_source;

import io.reactivex.rxjava3.core.Completable;
import retrofit2.http.Body;
import retrofit2.http.Headers;
import retrofit2.http.POST;
import ru.azbykamebeli.tracker.domain.model.VehicleLocationModel;

public interface IHTTPDataSource {
    @Headers({
            "User-Agent: azbyka-tracker",
            "Accept: application/vnd.azbykamebeli.vehicle-location.v1+json",
    })
    @POST("/ping/")
    Completable sendLocation(@Body final VehicleLocationModel __);
}
