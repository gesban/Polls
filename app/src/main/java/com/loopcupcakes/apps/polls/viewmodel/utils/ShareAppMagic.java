package com.loopcupcakes.apps.polls.viewmodel.utils;

import android.content.ActivityNotFoundException;
import android.content.Intent;
import android.net.Uri;
import android.os.Build;
import android.support.v7.app.AppCompatActivity;

import com.loopcupcakes.apps.polls.R;

/**
 * Created by evin on 1/30/16.
 */
public class ShareAppMagic {
    public static void shareApp(AppCompatActivity context) {
        Intent intent = new Intent(Intent.ACTION_SEND);
        intent.setType("text/plain");

        intent.putExtra(Intent.EXTRA_SUBJECT, context.getString(R.string.app_name));
        intent.putExtra(Intent.EXTRA_TEXT, "Check out this cool app! " + Constants.AppPlayStoreURL + context.getBaseContext().getPackageName());

        Intent chooser = Intent.createChooser(intent, "Tell a friend about " + context.getString(R.string.app_name));
        context.startActivity(chooser);
    }

    public static void openMoreApps(AppCompatActivity context) {
        Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse(Constants.MoreAppsByDeveloperUrl));
        context.startActivity(intent);
    }

    public static void likeApp(AppCompatActivity context) {
        Intent intent = null;
        try {
            context.getPackageManager().getPackageInfo("com.facebook.katana", 0);
            intent = new Intent(Intent.ACTION_VIEW, Uri.parse("fb://page/" + Constants.FacebookPageId));
        } catch (Exception e) {
            intent = new Intent(Intent.ACTION_VIEW, Uri.parse("https://www.facebook.com/" + Constants.FacebookPageId));
        }finally {
            if (intent != null){
                context.startActivity(intent);
            }
        }
    }


    public static void rateApp(AppCompatActivity context) {
        Uri uri = Uri.parse("market://details?id=" + context.getBaseContext().getPackageName());
        Intent goToMarket = new Intent(Intent.ACTION_VIEW, uri);

        if (Build.VERSION.SDK_INT >= 21) {
            goToMarket.addFlags(Intent.FLAG_ACTIVITY_NO_HISTORY | Intent.FLAG_ACTIVITY_NEW_DOCUMENT | Intent.FLAG_ACTIVITY_MULTIPLE_TASK);
        } else {
            goToMarket.addFlags(Intent.FLAG_ACTIVITY_NO_HISTORY | Intent.FLAG_ACTIVITY_CLEAR_WHEN_TASK_RESET | Intent.FLAG_ACTIVITY_MULTIPLE_TASK);
        }

        try {
            context.startActivity(goToMarket);
        } catch (ActivityNotFoundException e) {
            context.startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse(Constants.AppPlayStoreURL + context.getBaseContext().getPackageName())));
        }
    }
}
