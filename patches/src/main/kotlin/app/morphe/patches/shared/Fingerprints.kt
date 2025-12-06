package app.morphe.patches.shared

import app.morphe.patcher.fingerprint
import app.morphe.patcher.literal
import app.morphe.patcher.string
import com.android.tools.smali.dexlib2.AccessFlags

internal val castContextFetchFingerprint = fingerprint {
    instructions(
        string("Error fetching CastContext.")
    )
}

internal val primeMethodFingerprint = fingerprint {
    instructions(
        string("com.android.vending"),
        string("com.google.android.GoogleCamera")
    )
}

//
// YouTube / YT Music fingerprints
//

// Flag is present in YT 20.23, but bold icons are missing and forcing them crashes the app.
// 20.31 is the first target with all the bold icons present.
internal val boldIconsFeatureFlagFingerprint = fingerprint {
    accessFlags(AccessFlags.PUBLIC, AccessFlags.FINAL)
    returns("Z")
    parameters()
    instructions(
        literal(45685201L)
    )
}

