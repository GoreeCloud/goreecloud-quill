package com.goreecloud.quill.recall

import com.goreecloud.quill.privacy.QuillPrivacyDecision

data class RecallEntry(val id: Long, val text: String)

/** User-controlled, bounded local recall. Persistent storage belongs to the host adapter. */
class QuillRecallEngine(private val capacity: Int = 50) {
    private val entries = ArrayDeque<RecallEntry>()
    private var nextId = 1L

    init { require(capacity > 0) }

    fun remember(text: String, privacy: QuillPrivacyDecision): RecallEntry? {
        val value = text.trim()
        if (!privacy.allowPersonalizedLearning || value.isEmpty()) return null
        val entry = RecallEntry(nextId++, value)
        entries.addFirst(entry)
        while (entries.size > capacity) entries.removeLast()
        return entry
    }

    fun recent(limit: Int): List<RecallEntry> =
        if (limit <= 0) emptyList() else entries.take(limit)

    fun forget(id: Long): Boolean = entries.removeIf { it.id == id }

    fun clear() = entries.clear()
}