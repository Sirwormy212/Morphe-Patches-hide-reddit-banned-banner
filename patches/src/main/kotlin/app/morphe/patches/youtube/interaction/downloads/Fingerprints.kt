package app.morphe.patches.youtube.interaction.downloads

import app.morphe.patcher.fingerprint
import app.morphe.patcher.string
import com.android.tools.smali.dexlib2.AccessFlags

internal val offlineVideoEndpointFingerprint = fingerprint {
    accessFlags(AccessFlags.PUBLIC, AccessFlags.FINAL)
    returns("V")
    parameters(
        "Ljava/util/Map;",
        "L",
        "Ljava/lang/String", // VideoId
        "L",
    )
    instructions(
        string("Object is not an offlineable video: ")
    )
}
