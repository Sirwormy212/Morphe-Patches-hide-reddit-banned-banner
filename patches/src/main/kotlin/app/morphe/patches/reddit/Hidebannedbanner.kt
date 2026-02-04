package app.morphe.patches.reddit

import app.morphe.patcher.patch.bytecodePatch

@Suppress("unused")
val hideBannedBannerPatch = bytecodePatch(
    name = "Hide banned account banner",
    description = "Hides the banned account banner using multiple methods.",
) {
    compatibleWith("com.reddit.frontpage")

    execute {
        // Find and hide any class with "banned" or "Banner" in the name
        classes.forEach { classDef ->
            if (classDef.type.contains("Banner") || 
                classDef.type.contains("banned", ignoreCase = true)) {
                
                classDef.methods.forEach { method ->
                    if (method.name == "setVisibility" || 
                        method.name.contains("show", ignoreCase = true)) {
                        
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
