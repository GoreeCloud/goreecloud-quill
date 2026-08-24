package com.goreecloud.quill.sense

import kotlin.test.Test
import kotlin.test.assertEquals
import kotlin.test.assertTrue

class QuillSenseEngineTest {
    @Test fun classifiesQuestionAndCurrentToken() {
        val snapshot = QuillSenseEngine.inspect("Where are we?")
        assertEquals(QuillSenseEngine.Context.QUESTION, snapshot.context)
        assertEquals("", snapshot.currentToken)
    }

    @Test fun detectsSentenceStartForCurrentWord() {
        val snapshot = QuillSenseEngine.inspect("Hello world. next")
        assertEquals("next", snapshot.currentToken)
        assertTrue(snapshot.sentenceStart)
    }

    @Test fun boundsCallerProvidedContext() {
        val snapshot = QuillSenseEngine.inspect("ignored prefix alpha", maxChars = 5)
        assertEquals("alpha", snapshot.currentToken)
        assertEquals(QuillSenseEngine.Context.WORD, snapshot.context)
    }
}
