package com.poribarbazar;

import android.app.Activity;
import android.graphics.Color;
import android.view.View;

import androidx.core.content.ContextCompat;

import com.google.android.material.snackbar.Snackbar;

public class Tools {


    public static void showSnackbarWithColor(
            Activity activity,
            final String mainTextString,
            final String actionString,
            int LENGTH,
            View.OnClickListener listener, int color) {
        Snackbar snackbar = Snackbar.make(
                activity.findViewById(android.R.id.content),
                mainTextString,
                LENGTH)
                .setActionTextColor(Color.WHITE)
                .setAction(actionString, listener);
        snackbar.getView().setBackgroundColor(ContextCompat.getColor(activity, color));
        snackbar.show();
    }

    public static void snackErr(Activity activity,
                                final String mainTextString,
                                View.OnClickListener listener) {
        Snackbar snackbar = Snackbar.make(
                activity.findViewById(android.R.id.content),
                mainTextString,
                Snackbar.LENGTH_INDEFINITE)
                .setActionTextColor(Color.WHITE)
                .setAction("OK", listener);
        snackbar.getView().setBackgroundColor(ContextCompat.getColor(activity, R.color.red_900));
        snackbar.show();
    }

    public static void snackOK(Activity activity,
                               final String mainTextStringId,
                               View.OnClickListener listener) {
        Snackbar snackbar = Snackbar.make(
                activity.findViewById(android.R.id.content),
                mainTextStringId,
                Snackbar.LENGTH_INDEFINITE)
                .setActionTextColor(Color.WHITE)
                .setAction("OK", listener);

        snackbar.getView().setBackgroundColor(ContextCompat.getColor(activity, R.color.green_500));
        snackbar.show();
    }

    public static void snackInfo_Listener(Activity activity,
                                 final String mainTextStringId,
                                 View.OnClickListener listener) {
        Snackbar snackbar = Snackbar.make(
                activity.findViewById(android.R.id.content),
                mainTextStringId,
                Snackbar.LENGTH_LONG)
                .setActionTextColor(Color.WHITE)
                .setAction("Cart List", listener);

        snackbar.getView().setBackgroundColor(ContextCompat.getColor(activity, R.color.yellow_900));
        snackbar.show();
    }

    public static void snackInfo(Activity activity, final String body) {
        Snackbar snackbar = Snackbar.make(
                activity.findViewById(android.R.id.content),
                body,
                Snackbar.LENGTH_LONG)
                .setActionTextColor(Color.WHITE)
                .setAction("OK", null);

        snackbar.getView().setBackgroundColor(ContextCompat.getColor(activity, R.color.teal_700));
        snackbar.show();
    }

    public static void snackErrInfo(Activity activity,
                                    final String mainTextStringId,
                                    View.OnClickListener listener) {
        Snackbar snackbar = Snackbar.make(
                activity.findViewById(android.R.id.content),
                mainTextStringId,
                Snackbar.LENGTH_LONG)
                .setActionTextColor(Color.WHITE)
                .setAction("OK", listener);

        snackbar.getView().setBackgroundColor(ContextCompat.getColor(activity, R.color.red_800));
        snackbar.show();
    }
}
