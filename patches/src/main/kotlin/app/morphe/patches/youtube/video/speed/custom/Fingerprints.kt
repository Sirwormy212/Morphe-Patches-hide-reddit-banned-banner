package app.morphe.patches.youtube.video.speed.custom

import app.morphe.patcher.fieldAccess
import app.morphe.patcher.fingerprint
import app.morphe.patcher.literal
import app.morphe.patcher.methodCall
import app.morphe.patcher.newInstance
import app.morphe.patcher.opcode
import app.morphe.patcher.string
import app.morphe.patches.shared.misc.mapping.ResourceType
import app.morphe.patches.shared.misc.mapping.resourceLiteral
import com.android.tools.smali.dexlib2.AccessFlags
import com.android.tools.smali.dexlib2.Opcode


internal val getOldPlaybackSpeedsFingerprint = fingerprint {
    parameters("[L", "I")
    strings("menu_item_playback_speed")
}

internal val showOldPlaybackSpeedMenuFingerprint = fingerprint {
    instructions(
        resourceLiteral(ResourceType.STRING, "varispeed_unavailable_message")
    )
}

internal val showOldPlaybackSpeedMenuExtensionFingerprint = fingerprint {
    custom { method, _ -> method.name == "showOldPlaybackSpeedMenu" }
}

internal val serverSideMaxSpeedFeatureFlagFingerprint = fingerprint {
    accessFlags(AccessFlags.PUBLIC, AccessFlags.FINAL)
    returns("Z")
    instructions(
        literal(45719140L)
    )
}

internal val speedArrayGeneratorFingerprint = fingerprint {
    accessFlags(AccessFlags.PUBLIC, AccessFlags.STATIC)
    returns("[L")
    parameters("Lcom/google/android/libraries/youtube/innertube/model/player/PlayerResponseModel;")
    instructions(
        methodCall(name = "size", returnType = "I"),
        newInstance("Ljava/text/DecimalFormat;"),
        string("0.0#"),
        literal(7),
        opcode(Opcode.NEW_ARRAY),
        fieldAccess(definingClass = "/PlayerConfigModel;", type = "[F")
    )
}

/**
 * 20.34+
 */
internal val speedLimiterFingerprint = fingerprint {
    accessFlags(AccessFlags.PUBLIC, AccessFlags.FINAL)
    returns("V")
    parameters("F", "Lcom/google/android/libraries/youtube/innertube/model/media/PlayerConfigModel;")
    instructions(
        literal(0.25f),
        literal(4.0f)
    )
}

/**
 * 20.33 and lower.
 */
internal val speedLimiterLegacyFingerprint = fingerprint {
    accessFlags(AccessFlags.PUBLIC, AccessFlags.FINAL)
    returns("V")
    parameters("F")
    opcodes(
        Opcode.INVOKE_STATIC,
        Opcode.MOVE_RESULT,
        Opcode.IF_EQZ,
        Opcode.CONST_HIGH16,
        Opcode.GOTO,
        Opcode.CONST_HIGH16,
        Opcode.CONST_HIGH16,
        Opcode.INVOKE_STATIC,
    )
}
