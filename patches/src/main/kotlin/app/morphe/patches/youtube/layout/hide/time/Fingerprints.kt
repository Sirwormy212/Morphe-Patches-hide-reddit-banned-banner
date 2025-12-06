package app.morphe.patches.youtube.layout.hide.time

import app.morphe.patcher.InstructionLocation.*
import app.morphe.patcher.fieldAccess
import app.morphe.patcher.fingerprint
import app.morphe.patcher.methodCall
import app.morphe.patcher.opcode
import com.android.tools.smali.dexlib2.AccessFlags
import com.android.tools.smali.dexlib2.Opcode

internal val timeCounterFingerprint = fingerprint {
    returns("V")
    accessFlags(AccessFlags.PUBLIC, AccessFlags.FINAL)
    parameters()
    instructions(
        opcode(Opcode.SUB_LONG_2ADDR),
        methodCall(
            opcode = Opcode.INVOKE_STATIC,
            returnType = "Ljava/lang/CharSequence;",
            location = MatchAfterImmediately()
        ),
        opcode(Opcode.MOVE_RESULT_OBJECT, location = MatchAfterImmediately()),
        fieldAccess(opcode = Opcode.IGET_WIDE, type = "J", location = MatchAfterImmediately()),
        fieldAccess(opcode = Opcode.IGET_WIDE, type = "J", location = MatchAfterImmediately()),
        opcode(Opcode.SUB_LONG_2ADDR, location = MatchAfterImmediately()),

        methodCall(
            opcode = Opcode.INVOKE_STATIC,
            returnType = "Ljava/lang/CharSequence;",
            location = MatchAfterWithin(5)
        )
    )
}
