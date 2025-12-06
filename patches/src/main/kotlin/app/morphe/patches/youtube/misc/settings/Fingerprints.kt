package app.morphe.patches.youtube.misc.settings

import app.morphe.patcher.InstructionLocation.MatchAfterWithin
import app.morphe.patcher.fingerprint
import app.morphe.patcher.literal
import app.morphe.patcher.opcode
import app.morphe.patches.shared.misc.mapping.ResourceType
import app.morphe.patches.shared.misc.mapping.resourceLiteral
import com.android.tools.smali.dexlib2.AccessFlags
import com.android.tools.smali.dexlib2.Opcode

internal val licenseActivityOnCreateFingerprint = fingerprint {
    accessFlags(AccessFlags.PUBLIC, AccessFlags.FINAL)
    returns("V")
    parameters("Landroid/os/Bundle;")
    custom { method, classDef ->
        method.name == "onCreate" && classDef.endsWith("/LicenseActivity;")
    }
}

internal val setThemeFingerprint = fingerprint {
    accessFlags(AccessFlags.PUBLIC, AccessFlags.FINAL)
    returns("L")
    parameters()
    instructions(
        resourceLiteral(ResourceType.STRING, "app_theme_appearance_dark"),
    )
}

internal val cairoFragmentConfigFingerprint = fingerprint {
    accessFlags(AccessFlags.PUBLIC, AccessFlags.FINAL)
    returns("Z")
    instructions(
        literal(45532100L),
        opcode(Opcode.MOVE_RESULT, location = MatchAfterWithin(10))
    )
}
