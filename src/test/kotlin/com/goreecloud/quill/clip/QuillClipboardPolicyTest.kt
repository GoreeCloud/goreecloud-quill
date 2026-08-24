package com.goreecloud.quill.clip

import com.goreecloud.quill.privacy.QuillPrivacyDecision
import kotlin.test.Test
import kotlin.test.assertFalse
import kotlin.test.assertTrue

class QuillClipboardPolicyTest {
    @Test
    fun `sensitive clipboard content is never retained`() {
        assertFalse(
            QuillClipboardPolicy.mayRetain(
                ClipboardRetentionRequest(userInitiated = true, sensitive = true),
                QuillPrivacyDecision(
                    allowPersonalizedLearning = true,
                    allowContextRead = true,
                    allowAutomaticClipboardRetention = true,
                    allowSwipeLearningRetention = true,
                    allowTypedTextDiagnostics = false,
                ),
            ),
        )
    }

    @Test
    fun `private policy blocks automatic retention`() {
        assertFalse(
            QuillClipboardPolicy.mayRetain(
                ClipboardRetentionRequest(userInitiated = false, sensitive = false),
                QuillPrivacyDecision.LockedDown,
            ),
        )
    }

    @Test
    fun `explicit non-sensitive retention remains user controlled`() {
        assertTrue(
            QuillClipboardPolicy.mayRetain(
                ClipboardRetentionRequest(userInitiated = true, sensitive = false),
                QuillPrivacyDecision.LockedDown,
            ),
        )
    }
}
