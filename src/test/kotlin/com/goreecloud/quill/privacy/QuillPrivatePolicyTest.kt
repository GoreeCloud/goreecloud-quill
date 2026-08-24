package com.goreecloud.quill.privacy

import kotlin.test.Test
import kotlin.test.assertEquals
import kotlin.test.assertFalse

class QuillPrivatePolicyTest {
    @Test
    fun `private session disables retention and context capabilities`() {
        val decision = QuillPrivatePolicy.evaluate(
            InputPrivacyContext(
                editorKnown = true,
                passwordField = false,
                noPersonalizedLearning = false,
                quillPrivateEnabled = true,
            ),
        )

        assertEquals(QuillPrivacyDecision.LockedDown, decision)
    }

    @Test
    fun `unknown editor fails closed`() {
        val decision = QuillPrivatePolicy.evaluate(
            InputPrivacyContext(
                editorKnown = false,
                passwordField = false,
                noPersonalizedLearning = false,
                quillPrivateEnabled = false,
            ),
        )

        assertEquals(QuillPrivacyDecision.LockedDown, decision)
    }

    @Test
    fun `ordinary editor never enables typed text diagnostics`() {
        val decision = QuillPrivatePolicy.evaluate(
            InputPrivacyContext(
                editorKnown = true,
                passwordField = false,
                noPersonalizedLearning = false,
                quillPrivateEnabled = false,
            ),
        )

        assertFalse(decision.allowTypedTextDiagnostics)
    }
}
