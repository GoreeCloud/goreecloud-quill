package com.goreecloud.quill.toolbox

import kotlin.test.Test
import kotlin.test.assertEquals

class QuillToolboxTest {
    @Test fun countsWordsCharactersAndLines() {
        assertEquals(3, QuillToolbox.wordCount("one  two\nthree"))
        assertEquals(3, QuillToolbox.characterCount("a b c", false))
        assertEquals(2, QuillToolbox.lineCount("one\ntwo"))
    }

    @Test fun reversesText() {
        assertEquals("cba", QuillToolbox.reverse("abc"))
    }
}