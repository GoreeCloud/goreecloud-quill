package com.goreecloud.quill.edit

import kotlin.test.Test
import kotlin.test.assertEquals
import kotlin.test.assertNull

class QuillCaseTest {
    @Test
    fun `cycles lower title upper lower`() {
        assertEquals("Hello World", QuillCase.next("hello world"))
        assertEquals("HELLO WORLD", QuillCase.next("Hello World"))
        assertEquals("hello world", QuillCase.next("HELLO WORLD"))
    }

    @Test
    fun `mixed case normalizes to lower`() {
        assertEquals("hello world", QuillCase.next("hELLo WoRLD"))
    }

    @Test
    fun `selection without letters is rejected`() {
        assertNull(QuillCase.next("123 - !"))
    }
}
