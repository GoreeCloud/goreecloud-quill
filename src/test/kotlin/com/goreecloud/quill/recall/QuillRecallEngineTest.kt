package com.goreecloud.quill.recall

import com.goreecloud.quill.privacy.QuillPrivacyDecision
import kotlin.test.Test
import kotlin.test.assertEquals
import kotlin.test.assertNull

class QuillRecallEngineTest {
    private val allowed = QuillPrivacyDecision(true, true, true, true, false)
    private val denied = QuillPrivacyDecision.LockedDown

    @Test fun refusesRecallWhenPrivacyDeniesLearning() {
        val recall = QuillRecallEngine()
        assertNull(recall.remember("secret", denied))
        assertEquals(emptyList(), recall.recent(10))
    }

    @Test fun keepsBoundedNewestFirstHistory() {
        val recall = QuillRecallEngine(2)
        recall.remember("one", allowed)
        recall.remember("two", allowed)
        recall.remember("three", allowed)
        assertEquals(listOf("three", "two"), recall.recent(10).map { it.text })
    }
}