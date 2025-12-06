package app.morphe.patches.youtube.layout.startpage

import app.morphe.patcher.fieldAccess
import app.morphe.patcher.fingerprint
import app.morphe.patcher.literal
import app.morphe.patcher.string
import com.android.tools.smali.dexlib2.Opcode

internal val intentActionFingerprint = fingerprint {
    parameters("Landroid/content/Intent;")
    instructions(
        string("has_handled_intent")
    )
}

internal val browseIdFingerprint = fingerprint {
    returns("Lcom/google/android/apps/youtube/app/common/ui/navigation/PaneDescriptor;")

    //parameters() // 20.30 and earlier is no parameters.  20.31+ parameter is L.
    instructions(
        string("FEwhat_to_watch"),
        literal(512),
        fieldAccess(opcode = Opcode.IPUT_OBJECT, type = "Ljava/lang/String;")
    )
}
