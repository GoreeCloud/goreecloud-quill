package com.goreecloud.quill.lexicon

import kotlin.test.Test
import kotlin.test.assertEquals
import kotlin.test.assertFalse
import kotlin.test.assertTrue

class QuillLexiconEngineTest {
    @Test
    fun normalizesLookupAndRanksShortestPrefixMatchesFirst() {
        val engine = QuillLexiconEngine(listOf("Keyboard", "key", "Keys", "keep"))

        assertTrue(engine.contains(" KEYBOARD "))
        assertFalse(engine.contains("missing"))
        assertEquals(listOf("key", "Keys", "Keyboard"), engine.suggestions("keY", 3))
    }

    @Test
    fun ignoresBlankAndDuplicateCanonicalEntries() {
        val engine = QuillLexiconEngine(listOf("", " Quill ", "quill", "Quillify"))
        assertEquals(listOf("Quill", "Quillify"), engine.suggestions("quill", 10))
    }
}
