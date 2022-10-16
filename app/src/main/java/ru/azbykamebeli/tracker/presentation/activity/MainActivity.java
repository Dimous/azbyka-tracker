package ru.azbykamebeli.tracker.presentation.activity;

import android.Manifest;
import android.content.ComponentName;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.view.WindowManager;
import android.widget.Toast;

import androidx.activity.ComponentActivity;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.databinding.DataBindingUtil;
import androidx.lifecycle.ViewModelProvider;

import dagger.hilt.android.AndroidEntryPoint;
import ru.azbykamebeli.tracker.R;
import ru.azbykamebeli.tracker.databinding.ActivityMainBinding;
import ru.azbykamebeli.tracker.presentation.view_model.MainViewModel;

@AndroidEntryPoint
public final class MainActivity extends ComponentActivity {
    @Override
    protected void onCreate(final Bundle __bundle_saved_state) {
        super.onCreate(__bundle_saved_state);

        final MainViewModel
                __main_view_model = new ViewModelProvider(this).get(MainViewModel.class);

        final ActivityMainBinding
                __activity_main_binding = DataBindingUtil.setContentView(this, R.layout.activity_main);

        __activity_main_binding.setLifecycleOwner(this);
        __activity_main_binding.setModel(__main_view_model);

        __main_view_model.toast.observeForever(
                __string_text -> {
                    Toast.makeText(this, __string_text, Toast.LENGTH_LONG).show();
                }
        );

        // не поможет, если экспедитор нажмёт кнопку
        this.getWindow().addFlags(WindowManager.LayoutParams.FLAG_KEEP_SCREEN_ON);

        this.registerForActivityResult(
                        new ActivityResultContracts.RequestMultiplePermissions(),
                        __map_result -> {
                            final String[]
                                    __array_denied_permissions = __map_result.entrySet().stream().filter(__entry -> !__entry.getValue()).map(__entry -> __entry.getKey()).toArray(String[]::new);

                            if (0 == __array_denied_permissions.length) {
                                __main_view_model.onInitialize();
                            } else {
                                this.requestPermissions(__array_denied_permissions, 0);
                            }
                        }
                )
                .launch(
                        new String[]{
                                Manifest.permission.INTERNET,
                                Manifest.permission.WAKE_LOCK,
                                Manifest.permission.FOREGROUND_SERVICE,
                                Manifest.permission.ACCESS_NETWORK_STATE,
                                Manifest.permission.SCHEDULE_EXACT_ALARM,
                                Manifest.permission.ACCESS_FINE_LOCATION,
                                Manifest.permission.ACCESS_COARSE_LOCATION,
                                Manifest.permission.ACCESS_BACKGROUND_LOCATION,
                        }
                );
    }
    //---

    /**
     * @deprecated пользователь сможет взаимодействовать с приложением через главный экран
     */
    private void setIsPersistent(final boolean __boolean_is_persistent) {
        this.getPackageManager().setComponentEnabledSetting(new ComponentName(this, MainActivity.class), __boolean_is_persistent ? PackageManager.COMPONENT_ENABLED_STATE_DISABLED : PackageManager.COMPONENT_ENABLED_STATE_ENABLED, PackageManager.DONT_KILL_APP);
    }
}