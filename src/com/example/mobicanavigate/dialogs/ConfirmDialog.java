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

public class ConfirmDialog extends DialogFragment {
    String mMainText;

    public ConfirmDialog(String text) {
        super();
        mMainText = text;
    }

    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        super.onCreateDialog(savedInstanceState);
        View dialogView = getActivity().getLayoutInflater().inflate(R.layout.dialog_confirm, null);
        final Dialog myDialog = new Dialog(getActivity());
        myDialog.setContentView(dialogView);
        TextView textViewDialogText = (TextView) dialogView.findViewById(R.id.textViewDialogConfirmText);
        textViewDialogText.setText(mMainText);
        Button btnOk = (Button) dialogView.findViewById(R.id.dialogBtnOk);
        btnOk.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                myDialog.dismiss();
            }
        });
        myDialog.setTitle(R.string.end_journey);
        return myDialog;
    }

}
