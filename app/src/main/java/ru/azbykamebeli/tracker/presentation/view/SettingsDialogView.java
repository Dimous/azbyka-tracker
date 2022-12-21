package ru.azbykamebeli.tracker.presentation.view;

import android.app.AlertDialog;
import android.content.Context;
import android.util.AttributeSet;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.FrameLayout;

import androidx.annotation.Nullable;
import androidx.lifecycle.ViewModelProvider;
import androidx.lifecycle.ViewTreeLifecycleOwner;
import androidx.lifecycle.ViewTreeViewModelStoreOwner;

import ru.azbykamebeli.tracker.databinding.ViewSettingsDialogBinding;
import ru.azbykamebeli.tracker.presentation.view_model.SettingsDialogViewModel;

public class SettingsDialogView extends View {
    private final AlertDialog
            __alert_dialog;

    public SettingsDialogView(final Context __context, final @Nullable AttributeSet __attribute_set) {
        super(__context, __attribute_set);

        this.__alert_dialog = new AlertDialog.Builder(__context).setTitle("Настройки").setCancelable(false).create();

        this.__alert_dialog.getWindow().setGravity(Gravity.CENTER);
    }
    //---

    @Override
    public void onAttachedToWindow() {
        super.onAttachedToWindow();

        final Context
                __context = this.getContext();

        final FrameLayout
                __frame_layout = new FrameLayout(__context);

        final SettingsDialogViewModel
                __settings_dialog_view_model = new ViewModelProvider(ViewTreeViewModelStoreOwner.get(this)).get(SettingsDialogViewModel.class);

        final ViewSettingsDialogBinding
                __view_settings_dialog_binding = ViewSettingsDialogBinding.inflate(LayoutInflater.from(__context), __frame_layout, true);

        __view_settings_dialog_binding.setLifecycleOwner(
                ViewTreeLifecycleOwner.get(this)
        );
        __view_settings_dialog_binding.setModel(__settings_dialog_view_model);

        this.__alert_dialog.setView(__frame_layout);

        __settings_dialog_view_model.onInitialize();
    }
    //---

    @Override
    public void onDetachedFromWindow() {
        this.__alert_dialog.dismiss();

        super.onDetachedFromWindow();
    }
    //---

    @Override
    public void setVisibility(final int __int_visibility) {
        switch (__int_visibility) {
            case GONE:
            case INVISIBLE:
                this.__alert_dialog.hide();
                break;

            case VISIBLE:
                this.__alert_dialog.show();
                break;
        }

        super.setVisibility(__int_visibility);
    }
}
