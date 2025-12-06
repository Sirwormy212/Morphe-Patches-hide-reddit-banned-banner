package app.morphe.patches.music.layout.compactheader

import com.android.tools.smali.dexlib2.Opcode
import app.morphe.patcher.fingerprint
import app.morphe.util.literal

internal val chipCloudFingerprint = fingerprint {
    returns("V")
    opcodes(
        Opcode.CONST,
        Opcode.CONST_4,
        Opcode.INVOKE_STATIC,
        Opcode.MOVE_RESULT_OBJECT
    )
    literal { chipCloud }
}
