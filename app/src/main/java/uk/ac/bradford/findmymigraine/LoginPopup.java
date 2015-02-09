package uk.ac.bradford.findmymigraine;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.Dialog;
import android.app.DialogFragment;
import android.content.DialogInterface;
import android.os.Bundle;

/**
 * Created by George on 11/12/2014.
 */
public class LoginPopup extends DialogFragment{

    public interface LoginPopupListener {
        public void onDialogPositiveClick(DialogFragment dialog);
    }

    LoginPopupListener mListen;

    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
        try {
            mListen = (LoginPopupListener) activity;
        }
        catch (ClassCastException e) {
            throw new ClassCastException(activity.toString() + " must implement LoginPopupListener");
        }
    }

    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        AlertDialog.Builder b = new AlertDialog.Builder(getActivity());
        b.setMessage(R.string.login)
                .setPositiveButton(R.string.okay, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        mListen.onDialogPositiveClick(LoginPopup.this);
                    }
                });

        return b.create();
    }

}
