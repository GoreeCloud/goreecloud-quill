package com.goreecloud.quill.edit

import kotlin.test.Test
import kotlin.test.assertEquals
import kotlin.test.assertFailsWith

class QuillEditEngineTest {
    @Test fun replacesAndDeletesSelection() {
        val selection = QuillEditEngine.Selection(6, 11)
        assertEquals("Hello Quill", QuillEditEngine.replace("Hello world", selection, "Quill"))
        assertEquals("Hello ", QuillEditEngine.delete("Hello world", selection))
    }

    @Test fun duplicatesSelection() {
        assertEquals("go go", QuillEditEngine.duplicate("go ", QuillEditEngine.Selection(0, 2)))
    }

    @Test fun swapsAdjacentSelectionsWithoutChangingSeparator() {
        val text = "one   two"
        assertEquals(
            "two   one",
            QuillEditEngine.swapAdjacentWords(
                text,
                QuillEditEngine.Selection(0, 3),
                QuillEditEngine.Selection(6, 9),
            ),
        )
    }

    @Test fun rejectsOutOfBoundsSelection() {
        assertFailsWith<IllegalArgumentException> {
            QuillEditEngine.replace("short", QuillEditEngine.Selection(0, 6), "x")
        }
    }
}
