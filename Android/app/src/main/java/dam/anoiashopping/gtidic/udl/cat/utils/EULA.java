package dam.anoiashopping.gtidic.udl.cat.utils;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.Dialog;
import android.content.DialogInterface;
import android.content.pm.ActivityInfo;
import android.os.Build;
import android.text.Html;
import android.widget.CheckBox;

import dam.anoiashopping.gtidic.udl.cat.R;


public class EULA {

    /**
     * This file provides simple End User License Agreement
     * It shows a simple dialog with the license text, and two buttons.
     * If user clicks on 'cancel' button, app unselect the checkbox.
     * If user clicks on 'accept' button, app select the checkbox.
     * so next time this will not show, until next upgrade.
     */

    private Activity mContext;
    public Boolean accepted;

    public EULA(Activity context) {
        mContext = context;
    }


    public void show(int id) {
        // EULA title
        String title = mContext.getString(R.string.app_name);

        // EULA text
        String message = mContext.getString(R.string.eula_string);

        // Disable orientation changes, to prevent parent activity
        // reinitialization
        mContext.setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_UNSPECIFIED);

        final CheckBox agreement_terms_and_conditions = (CheckBox)
                mContext.findViewById(id);

        AlertDialog.Builder builder = new AlertDialog.Builder(mContext);
        builder.setTitle(title);
        builder.setMessage(Html.fromHtml(message, Build.VERSION.SDK_INT));
        builder.setCancelable(false);
        builder.setPositiveButton(R.string.accept,
                new Dialog.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        // Close dialog
                        dialogInterface.dismiss();

                        // Enable orientation changes based on
                        // device's sensor
                        mContext.setRequestedOrientation(
                                ActivityInfo.SCREEN_ORIENTATION_SENSOR);

                        agreement_terms_and_conditions.setChecked(true);

                    }


                }); builder.setNegativeButton(android.R.string.cancel,
                new Dialog.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        // Close the activity as they have declined
                        // the EULA
                        mContext.setRequestedOrientation(
                                ActivityInfo.SCREEN_ORIENTATION_SENSOR);
                        agreement_terms_and_conditions.setChecked(false);
                    }

                });
        builder.create().show();
    }
}

