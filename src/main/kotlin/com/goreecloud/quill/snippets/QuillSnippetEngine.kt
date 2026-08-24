package com.goreecloud.quill.snippets

import com.goreecloud.quill.spi.QuillSnippetStore

class QuillSnippetEngine(
    private val store: QuillSnippetStore,
) {
    fun expand(id: String): String? {
        val normalized = id.trim()
        if (normalized.isEmpty()) return null
        return store.read(normalized)
    }

    fun matchingIds(prefix: String, limit: Int = 8): List<String> {
        if (limit <= 0) return emptyList()
        val normalized = prefix.trim().lowercase()
        return store.listIds()
            .asSequence()
            .map { it.trim() }
            .filter { it.isNotEmpty() }
            .distinct()
            .filter { normalized.isEmpty() || it.lowercase().startsWith(normalized) }
            .sortedWith(compareBy<String> { it.length }.thenBy { it })
            .take(limit.coerceAtMost(MAX_RESULTS))
            .toList()
    }

    companion object {
        const val MAX_RESULTS = 20
    }
}
