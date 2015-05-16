package com.jorgecastillo.kanadrill;

import android.app.AlertDialog;
import android.app.Dialog;
import android.app.DialogFragment;
import android.content.DialogInterface;
import android.os.Bundle;
import android.view.Gravity;
import android.widget.TextView;

public class KanaDrillDialog extends DialogFragment {

    String first, second, title;

    public void setValues(String first, String second){
        this.first = first;
        this.second = second;
    }

    public  void setFontSize(){
        TextView textView = (TextView) getDialog().findViewById(android.R.id.message);
        textView.setTextSize(40);
    }

    public void setTitle(String title){
        this.title = title;
    }

    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        builder.setTitle(title).setMessage(first + second)
                .setNeutralButton(R.string.okay, new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                    }
                });
        AlertDialog dialog = builder.show();
        TextView messageText = (TextView) dialog.findViewById(android.R.id.message);
        messageText.setTextSize(42);
        messageText.setGravity(Gravity.CENTER);

        return dialog;
    }
}
