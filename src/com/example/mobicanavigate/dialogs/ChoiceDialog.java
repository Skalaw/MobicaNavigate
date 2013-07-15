package com.example.mobicanavigate.dialogs;

import android.app.Dialog;
import android.os.Bundle;
import android.support.v4.app.DialogFragment;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.example.mobicanavigate.R;

/**
 * @author Miłosz Skalski
 */

public class ChoiceDialog extends DialogFragment {
    private String mMainText;
    private String mPositiveText = "";
    private String mNegativeText = "";
    private View.OnClickListener mListenerPositive;
    private View.OnClickListener mListenerNegative;

    public ChoiceDialog(String text) {
        super();
        mMainText = text;
    }

    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        super.onCreateDialog(savedInstanceState);

        View dialogView = getActivity().getLayoutInflater().inflate(R.layout.dialog_choice, null);
        final Dialog myDialog = new Dialog(getActivity());
        myDialog.setContentView(dialogView);

        TextView textViewDialogText = (TextView) dialogView.findViewById(R.id.textViewDialogChoiceText);
        textViewDialogText.setText(mMainText);
        Button btnNegative = (Button) dialogView.findViewById(R.id.dialogBtnNegative);
        Button btnPositive = (Button) dialogView.findViewById(R.id.dialogBtnPositive);
        btnPositive.setOnClickListener(mListenerPositive);
        if (mListenerNegative == null) {
            btnNegative.setOnClickListener(new View.OnClickListener() {
                public void onClick(View v) {
                    myDialog.dismiss();
                }
            });
        } else {
            btnNegative.setOnClickListener(mListenerNegative);
        }

        if (!mPositiveText.isEmpty()) {
            btnPositive.setText(mPositiveText);
        }
        if (!mNegativeText.isEmpty()) {
            btnNegative.setText(mNegativeText);
        }
        myDialog.setTitle(R.string.choice);
        return myDialog;
    }

    public void setmListenerPositive(View.OnClickListener mListenerPositive) {
        this.mListenerPositive = mListenerPositive;
    }

    public void setmListenerNegative(View.OnClickListener mListenerNegative) {
        this.mListenerNegative = mListenerNegative;
    }

    public void setmPositiveText(String mPositiveText) {
        this.mPositiveText = mPositiveText;
    }

    public void setmNegativeText(String mNegativeText) {
        this.mNegativeText = mNegativeText;
    }

}
