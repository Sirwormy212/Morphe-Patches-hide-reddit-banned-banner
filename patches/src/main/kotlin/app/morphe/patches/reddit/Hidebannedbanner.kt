package app.morphe.patches.reddit.layout.banner

import app.morphe.patcher.extensions.InstructionExtensions.addInstructions
import app.morphe.patcher.patch.bytecodePatch
import app.morphe.patches.reddit.utils.compatibility.Constants.COMPATIBILITY_REDDIT

@Suppress("unused")
val hideBannedBannerPatch = bytecodePatch(
    name = "Hide banned account banner",
    description = "Hides the banned account banner (cosmetic only)."
) {
    compatibleWith(COMPATIBILITY_REDDIT)

    execute {
        classes.forEach { classDef ->
            // Look for classes related to banned banner
            if (classDef.type.contains("Banner", ignoreCase = true) && 
                classDef.type.contains("banned", ignoreCase = true)) {
                
                classDef.methods.forEach { method ->
                    // Find methods that show or set visibility
                    if (method.name.contains("show", ignoreCase = true) || 
                        method.name == "setVisibility") {
                        
                        method.implementation?.let {
                            method.addInstructions(
                                0,
                                """
                                    return-void
                                """
                            )
                        }
                    }
                }
            }
        }
    }
}
