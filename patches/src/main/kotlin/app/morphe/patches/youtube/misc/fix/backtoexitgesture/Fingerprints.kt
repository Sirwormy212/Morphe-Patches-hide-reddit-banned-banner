package app.morphe.patches.youtube.misc.fix.backtoexitgesture

import app.morphe.patcher.InstructionLocation.*
import app.morphe.patcher.checkCast
import app.morphe.patcher.fingerprint
import app.morphe.patcher.literal
import app.morphe.patcher.methodCall
import app.morphe.patcher.opcode
import com.android.tools.smali.dexlib2.AccessFlags
import com.android.tools.smali.dexlib2.Opcode

internal val scrollPositionFingerprint = fingerprint {
    accessFlags(AccessFlags.PROTECTED, AccessFlags.FINAL)
    returns("V")
    parameters("L")
    opcodes(
        Opcode.IF_NEZ,
        Opcode.INVOKE_DIRECT,
        Opcode.RETURN_VOID
    )
    strings("scroll_position")
}

internal val recyclerViewTopScrollingFingerprint = fingerprint {
    accessFlags(AccessFlags.PUBLIC, AccessFlags.FINAL)
    returns("V")
    parameters()
    instructions(
        methodCall(smali = "Ljava/util/Iterator;->next()Ljava/lang/Object;"),
        opcode(Opcode.MOVE_RESULT_OBJECT, MatchAfterImmediately()),
        checkCast("Landroid/support/v7/widget/RecyclerView;", MatchAfterImmediately()),
        literal(0, location = MatchAfterImmediately()),
        methodCall(definingClass = "Landroid/support/v7/widget/RecyclerView;", location = MatchAfterImmediately()),
        opcode(Opcode.GOTO, MatchAfterImmediately())
    )
}
