package com.goreecloud.quill.compose

import kotlin.test.Test
import kotlin.test.assertEquals

class QuillComposeEngineTest {
    @Test fun normalizesSpacingAndPunctuation() {
        assertEquals("Hello, world!", QuillComposeEngine.normalizeSpacing("  Hello ,   world! "))
    }

    @Test fun addsTerminalPunctuation() {
        assertEquals("Hello world.", QuillComposeEngine.ensureTerminalPunctuation("Hello world"))
        assertEquals("Hello!", QuillComposeEngine.ensureTerminalPunctuation("Hello!"))
    }

    @Test fun capitalizesSentence() {
        assertEquals("Hello world", QuillComposeEngine.capitalizeSentence("hello world"))
    }
}