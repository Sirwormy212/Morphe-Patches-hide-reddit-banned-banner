package app.morphe.patches.shared.layout.theme

import app.morphe.patcher.InstructionLocation.MatchAfterImmediately
import app.morphe.patcher.InstructionLocation.MatchAfterWithin
import app.morphe.patcher.fieldAccess
import app.morphe.patcher.fingerprint
import app.morphe.patcher.methodCall
import com.android.tools.smali.dexlib2.AccessFlags
import com.android.tools.smali.dexlib2.Opcode

internal val lithoOnBoundsChangeFingerprint = fingerprint {
    accessFlags(AccessFlags.PROTECTED, AccessFlags.FINAL)
    returns("V")
    parameters("Landroid/graphics/Rect;")
    instructions(
        fieldAccess(
            opcode = Opcode.IPUT_OBJECT,
            definingClass = "this",
            type = "Landroid/graphics/Path;"
        ),

        methodCall(
            definingClass = "this",
            name = "isStateful",
            returnType = "Z",
            location = MatchAfterWithin(5)
        ),

        fieldAccess(
            opcode = Opcode.IGET_OBJECT,
            definingClass = "this",
            type = "Landroid/graphics/Paint",
            location = MatchAfterWithin(5)
        ),
        methodCall(
            smali = "Landroid/graphics/Paint;->setColor(I)V",
            location = MatchAfterImmediately()
        )
    )
    custom { method, _ ->
        method.name == "onBoundsChange"
    }
}
