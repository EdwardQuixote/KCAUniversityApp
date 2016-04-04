package uk.co.edwardquixote.Chaward.kcaapp.Dialogs;

import android.app.ProgressDialog;
import android.content.Context;

/**
 * Created by Edward Quixote on 01/04/2016.
 */
public class Dialogs {

    private Context coxContext;

    private ProgressDialog pdgProgress;

    public Dialogs(Context context) {
        this.coxContext = context;
    }


    /**
     * This method generates Progress Dialogs,
     * depending on the code passed.
     *
     * @param progressMessage (String)
     */
    public void codeToGenerateProgressDialogs(String progressMessage) {

        pdgProgress = new ProgressDialog(coxContext);
        pdgProgress.setMessage(progressMessage);
        pdgProgress.setCancelable(false);
        pdgProgress.setCanceledOnTouchOutside(false);
        pdgProgress.show();

    }

    /**
     * Method to dismiss Dialogs depending on code passed.
     * First checks if Dialog is NULL,
     * if not, it dismisses
     */
    public void codeToDismissDialogs() {

        if (pdgProgress != null) {
            pdgProgress.dismiss();
        }

    }
}
