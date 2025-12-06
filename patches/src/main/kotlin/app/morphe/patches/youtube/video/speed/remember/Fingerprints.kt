package app.morphe.patches.youtube.video.speed.remember

import app.morphe.patcher.fingerprint
import app.morphe.patcher.string

internal val initializePlaybackSpeedValuesFingerprint = fingerprint {
    parameters("[L", "I")
    instructions(
        string("menu_item_playback_speed"),
    )
}
