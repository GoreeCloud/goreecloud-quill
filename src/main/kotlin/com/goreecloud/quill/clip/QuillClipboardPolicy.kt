package com.goreecloud.quill.clip

import com.goreecloud.quill.privacy.QuillPrivacyDecision

/**
 * Clipboard retention decision that never accepts or emits clipboard payloads.
 */
data class ClipboardRetentionRequest(
    val userInitiated: Boolean,
    val sensitive: Boolean,
)

object QuillClipboardPolicy {
    fun mayRetain(
        request: ClipboardRetentionRequest,
        privacy: QuillPrivacyDecision,
    ): Boolean {
        if (request.sensitive) return false
        if (!privacy.allowAutomaticClipboardRetention && !request.userInitiated) return false
        return request.userInitiated || privacy.allowAutomaticClipboardRetention
    }
}
