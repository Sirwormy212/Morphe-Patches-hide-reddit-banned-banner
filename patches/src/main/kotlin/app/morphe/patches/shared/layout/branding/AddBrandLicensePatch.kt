package app.morphe.patches.shared.layout.branding

import app.morphe.patcher.patch.rawResourcePatch
import app.morphe.util.inputStreamFromBundledResource
import java.nio.file.Files

/**
 * Copies a branding license text file to the target apk.
 */
internal val addBrandLicensePatch = rawResourcePatch {
    execute {
        val sourceFileName = "LICENSE_MORPHE.TXT"

        val inputFileStream = inputStreamFromBundledResource(
            "branding-license",
            sourceFileName
        )!!

        val targetFile = get(sourceFileName, false).toPath()

        Files.copy(inputFileStream, targetFile)
    }
}
