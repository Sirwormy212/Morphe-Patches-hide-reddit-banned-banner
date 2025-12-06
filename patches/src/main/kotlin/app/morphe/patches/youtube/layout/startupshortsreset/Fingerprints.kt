package app.morphe.patches.youtube.layout.startupshortsreset

import app.morphe.patcher.InstructionLocation.*
import app.morphe.patcher.StringComparisonType
import app.morphe.patcher.checkCast
import app.morphe.patcher.fingerprint
import app.morphe.patcher.literal
import app.morphe.patcher.methodCall
import app.morphe.patcher.opcode
import app.morphe.patcher.string
import com.android.tools.smali.dexlib2.AccessFlags
import com.android.tools.smali.dexlib2.Opcode

/**
 * 20.02+
 */
internal val userWasInShortsAlternativeFingerprint = fingerprint {
    returns("V")
    accessFlags(AccessFlags.PUBLIC, AccessFlags.FINAL)
    parameters("Ljava/lang/Object;")
    instructions(
        checkCast("Ljava/lang/Boolean;"),
        methodCall(smali = "Ljava/lang/Boolean;->booleanValue()Z", location = MatchAfterImmediately()),
        opcode(Opcode.MOVE_RESULT, MatchAfterImmediately()),
        // 20.40+ string was merged into another string and is a partial match.
        string("userIsInShorts: ", StringComparisonType.CONTAINS, MatchAfterWithin(15))
    )
}

/**
 * Pre 20.02
 */
internal val userWasInShortsLegacyFingerprint = fingerprint {
    returns("V")
    accessFlags(AccessFlags.PUBLIC, AccessFlags.FINAL)
    parameters("Ljava/lang/Object;")
    instructions(
        string("Failed to read user_was_in_shorts proto after successful warmup")
    )
}

/**
 * 18.15.40+
 */
internal val userWasInShortsConfigFingerprint = fingerprint {
    accessFlags(AccessFlags.PUBLIC, AccessFlags.FINAL)
    returns("Z")
    parameters()
    instructions(
        literal(45358360L)
    )
}
