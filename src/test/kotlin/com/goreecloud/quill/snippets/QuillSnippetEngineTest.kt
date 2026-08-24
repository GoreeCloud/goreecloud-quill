package com.goreecloud.quill.snippets

import com.goreecloud.quill.spi.QuillSnippetStore
import kotlin.test.Test
import kotlin.test.assertEquals
import kotlin.test.assertNull

class QuillSnippetEngineTest {
    @Test
    fun expandsByIdAndFiltersDeterministically() {
        val store = object : QuillSnippetStore {
            private val data = mapOf("addr" to "123 Main St", "address-home" to "Home", "sig" to "Regards")
            override fun listIds(): List<String> = data.keys.toList()
            override fun read(id: String): String? = data[id]
        }
        val engine = QuillSnippetEngine(store)

        assertEquals("123 Main St", engine.expand(" addr "))
        assertNull(engine.expand("missing"))
        assertEquals(listOf("addr", "address-home"), engine.matchingIds("ad"))
    }
}
