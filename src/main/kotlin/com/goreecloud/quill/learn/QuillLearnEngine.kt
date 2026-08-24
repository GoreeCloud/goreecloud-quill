package com.goreecloud.quill.learn

import com.goreecloud.quill.privacy.QuillPrivacyDecision

/**
 * Local personalization core. The caller owns persistence and must provide the
 * current privacy decision before any token can be learned.
 */
class QuillLearnEngine {
    private val frequencies = linkedMapOf<String, Int>()

    fun observe(token: String, privacy: QuillPrivacyDecision): Boolean {
        if (!privacy.allowPersonalizedLearning) return false
        val normalized = token.trim()
        if (!isLearnable(normalized)) return false
        frequencies[normalized] = (frequencies[normalized] ?: 0) + 1
        return true
    }

    fun ranked(prefix: String = "", limit: Int = 8): List<String> {
        if (limit <= 0) return emptyList()
        val normalizedPrefix = prefix.trim().lowercase()
        return frequencies.entries
            .asSequence()
            .filter { normalizedPrefix.isEmpty() || it.key.lowercase().startsWith(normalizedPrefix) }
            .sortedWith(compareByDescending<Map.Entry<String, Int>> { it.value }.thenBy { it.key })
            .map { it.key }
            .take(limit.coerceAtMost(MAX_RESULTS))
            .toList()
    }

    fun forget(token: String): Boolean = frequencies.remove(token.trim()) != null

    fun clear() = frequencies.clear()

    private fun isLearnable(token: String): Boolean =
        token.length in 2..MAX_TOKEN_LENGTH && token.none { it.isWhitespace() || it.isISOControl() }

    companion object {
        const val MAX_RESULTS = 20
        const val MAX_TOKEN_LENGTH = 64
    }
}
