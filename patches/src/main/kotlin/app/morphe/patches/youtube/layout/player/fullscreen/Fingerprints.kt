package app.morphe.patches.youtube.layout.player.fullscreen

import app.morphe.patcher.InstructionLocation.MatchAfterWithin
import app.morphe.patcher.fingerprint
import app.morphe.patcher.literal
import app.morphe.patcher.opcode
import com.android.tools.smali.dexlib2.AccessFlags
import com.android.tools.smali.dexlib2.Opcode

/**
 * 19.46+
 */
internal val openVideosFullscreenPortraitFingerprint = fingerprint {
    returns("V")
    parameters("L", "Lj\$/util/Optional;")
    instructions(
        opcode(Opcode.MOVE_RESULT), // Conditional check to modify.
        // Open videos fullscreen portrait feature flag.
        literal(45666112L, location = MatchAfterWithin(5)), // Cannot be more than 5.
        opcode(Opcode.MOVE_RESULT, location = MatchAfterWithin(10)),
    )
}

/**
 * Pre 19.46.
 */
internal val openVideosFullscreenPortraitLegacyFingerprint = fingerprint {
    accessFlags(AccessFlags.PUBLIC, AccessFlags.FINAL)
    returns("V")
    parameters("L", "Lj\$/util/Optional;")
    opcodes(
        Opcode.GOTO,
        Opcode.SGET_OBJECT,
        Opcode.GOTO,
        Opcode.SGET_OBJECT,
        Opcode.INVOKE_VIRTUAL,
        Opcode.MOVE_RESULT,
        Opcode.IF_EQ,
        Opcode.IF_EQ,
        Opcode.IGET_OBJECT,
        Opcode.INVOKE_VIRTUAL,
        Opcode.MOVE_RESULT  // Conditional check to modify.
    )
}

internal val openVideosFullscreenHookPatchExtensionFingerprint = fingerprint {
    accessFlags(AccessFlags.PRIVATE, AccessFlags.STATIC)
    returns("Z")
    parameters()
    custom { methodDef, classDef ->
        methodDef.name == "isFullScreenPatchIncluded" && classDef.type == EXTENSION_CLASS_DESCRIPTOR
    }
}
