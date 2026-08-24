package com.goreecloud.quill.privacy

/**
 * Text-free privacy context supplied by the host keyboard.
 *
 * Quill policy code deliberately evaluates metadata only. Raw typed text,
 * clipboard payloads, selections, and surrounding editor content do not belong
 * in this context.
 */
data class InputPrivacyContext(
    val editorKnown: Boolean,
    val passwordField: Boolean,
    val noPersonalizedLearning: Boolean,
    val quillPrivateEnabled: Boolean,
)

data class QuillPrivacyDecision(
    val allowPersonalizedLearning: Boolean,
    val allowContextRead: Boolean,
    val allowAutomaticClipboardRetention: Boolean,
    val allowSwipeLearningRetention: Boolean,
    val allowTypedTextDiagnostics: Boolean,
) {
    companion object {
        val LockedDown = QuillPrivacyDecision(
            allowPersonalizedLearning = false,
            allowContextRead = false,
            allowAutomaticClipboardRetention = false,
            allowSwipeLearningRetention = false,
            allowTypedTextDiagnostics = false,
        )
    }
}

/**
 * Fail-closed Privacy Shield / Wardveil-compatible policy boundary.
 */
object QuillPrivatePolicy {
    fun evaluate(context: InputPrivacyContext): QuillPrivacyDecision {
        if (!context.editorKnown || context.passwordField ||
            context.noPersonalizedLearning || context.quillPrivateEnabled
        ) {
            return QuillPrivacyDecision.LockedDown
        }

        return QuillPrivacyDecision(
            allowPersonalizedLearning = true,
            allowContextRead = true,
            allowAutomaticClipboardRetention = true,
            allowSwipeLearningRetention = true,
            allowTypedTextDiagnostics = false,
        )
    }
}
