package com.goreecloud.quill.flow

import com.goreecloud.quill.lexicon.QuillLexiconEngine
import kotlin.test.Test
import kotlin.test.assertEquals

class QuillSuggestionEngineTest {
    @Test
    fun completionUsesLexiconAndBoundsResults() {
        val engine = QuillSuggestionEngine(QuillLexiconEngine(listOf("cloud", "clover", "clock")))
        assertEquals(listOf("clock", "cloud"), engine.complete("cl", 2))
    }

    @Test
    fun correctionSkipsKnownWordsAndRanksClosestCandidates() {
        val lexicon = QuillLexiconEngine(listOf("keyboard", "quill"))
        val engine = QuillSuggestionEngine(lexicon)

        assertEquals(emptyList(), engine.corrections("quill", listOf("quilt")))
        assertEquals(
            listOf("keyboard", "keyboards"),
            engine.corrections("keybord", listOf("keyboards", "keyboard", "clipboard"), limit = 2),
        )
    }
}
