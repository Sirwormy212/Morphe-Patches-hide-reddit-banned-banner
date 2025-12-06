package app.morphe.patches.shared.misc.extension

import app.morphe.patcher.fingerprint
import com.android.tools.smali.dexlib2.AccessFlags

internal val morpheUtilsPatchesVersionFingerprint = fingerprint {
    accessFlags(AccessFlags.PUBLIC, AccessFlags.STATIC)
    returns("Ljava/lang/String;")
    parameters()
    custom { method, _ ->
        method.name == "getPatchesReleaseVersion" && method.definingClass == EXTENSION_CLASS_DESCRIPTOR
    }
}
