package app.morphe.patches.youtube.interaction.dialog

import app.morphe.patcher.fingerprint
import app.morphe.patcher.methodCall

internal val createDialogFingerprint = fingerprint {
    returns("V")
    parameters("L", "L", "Ljava/lang/String;")
    instructions(
        methodCall(smali = "Landroid/app/AlertDialog\$Builder;->setNegativeButton(ILandroid/content/DialogInterface\$OnClickListener;)Landroid/app/AlertDialog\$Builder;"),
        methodCall(smali = "Landroid/app/AlertDialog\$Builder;->setOnCancelListener(Landroid/content/DialogInterface\$OnCancelListener;)Landroid/app/AlertDialog\$Builder;"),
        methodCall(smali = "Landroid/app/AlertDialog\$Builder;->create()Landroid/app/AlertDialog;"),
        methodCall(smali = "Landroid/app/AlertDialog;->show()V")
    )
}
