package app.morphe.patches.youtube.misc.dimensions.spoof

import app.morphe.patcher.fingerprint
import app.morphe.patcher.string

internal val deviceDimensionsModelToStringFingerprint = fingerprint {
    returns("L")
    instructions(
        string("minh."),
        string(";maxh.")
    )
}
