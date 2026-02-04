package app.morphe.patches.reddit

import app.revanced.patcher.patch.bytecodePatch

@Suppress("unused")
val hideBannedBannerPatch = bytecodePatch(
    name = "Hide banned account banner",
    description = "Hides the banned account banner using multiple methods.",
) {
    compatibleWith("com.reddit.frontpage")

    execute {
        // Method 1: Find and hide any view with "banned" in the name
        classes.forEach { classDef ->
            if (classDef.type.contains("Banner") || 
                classDef.type.contains("banned", ignoreCase = true)) {
                
                classDef.methods.forEach { method ->
                    // If method sets visibility or shows the banner
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
        
        // Method 2: Find methods with "banned" string and make them do nothing
        classes.forEach { classDef ->
            classDef.methods.forEach { method ->
                method.implementation?.let { impl ->
                    val hasWordBanned = impl.instructions.any { instruction ->
                        instruction.toString().contains("banned", ignoreCase = true)
                    }
                    
                    if (hasWordBanned && method.returnType == "V") {
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
