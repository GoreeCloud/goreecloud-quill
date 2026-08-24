package com.goreecloud.quill.learn

import com.goreecloud.quill.privacy.QuillPrivacyDecision
import kotlin.test.Test
import kotlin.test.assertEquals
import kotlin.test.assertFalse
import kotlin.test.assertTrue

class QuillLearnEngineTest {
    private val allowed = QuillPrivacyDecision(
        allowPersonalizedLearning = true,
        allowContextRead = true,
        allowAutomaticClipboardRetention = true,
        allowSwipeLearningRetention = true,
        allowTypedTextDiagnostics = false,
    )

    @Test
    fun refusesLearningWhenPrivacyPolicyDeniesIt() {
        val engine = QuillLearnEngine()
        assertFalse(engine.observe("privateword", QuillPrivacyDecision.LockedDown))
        assertEquals(emptyList(), engine.ranked())
    }

    @Test
    fun ranksLocallyLearnedTokensByFrequency() {
        val engine = QuillLearnEngine()
        assertTrue(engine.observe("Quill", allowed))
        assertTrue(engine.observe("Quill", allowed))
        assertTrue(engine.observe("Keyboard", allowed))
        assertEquals(listOf("Quill", "Keyboard"), engine.ranked())
        assertTrue(engine.forget("Keyboard"))
        assertEquals(listOf("Quill"), engine.ranked())
    }
}
