package com.goreecloud.quill.flow

import com.goreecloud.quill.spi.QuillLexicon

/** Local suggestion and correction engine with bounded deterministic work. */
class QuillSuggestionEngine(
    private val lexicon: QuillLexicon,
) {
    fun complete(prefix: String, limit: Int = 5): List<String> =
        lexicon.suggestions(prefix.trim(), limit.coerceIn(0, MAX_RESULTS))

    fun corrections(
        token: String,
        candidates: Iterable<String>,
        limit: Int = 3,
        maxDistance: Int = 2,
    ): List<String> {
        val source = token.trim()
        if (source.isEmpty() || lexicon.contains(source) || limit <= 0 || maxDistance < 0) {
            return emptyList()
        }

        return candidates
            .asSequence()
            .map { it.trim() }
            .filter { it.isNotEmpty() }
            .distinct()
            .map { candidate -> candidate to editDistance(source.lowercase(), candidate.lowercase(), maxDistance) }
            .filter { (_, distance) -> distance <= maxDistance }
            .sortedWith(compareBy<Pair<String, Int>> { it.second }.thenBy { it.first.length }.thenBy { it.first })
            .map { it.first }
            .take(limit.coerceAtMost(MAX_RESULTS))
            .toList()
    }

    private fun editDistance(left: String, right: String, cutoff: Int): Int {
        if (kotlin.math.abs(left.length - right.length) > cutoff) return cutoff + 1
        var previous = IntArray(right.length + 1) { it }
        var current = IntArray(right.length + 1)

        for (i in left.indices) {
            current[0] = i + 1
            var rowMinimum = current[0]
            for (j in right.indices) {
                val substitution = previous[j] + if (left[i] == right[j]) 0 else 1
                current[j + 1] = minOf(current[j] + 1, previous[j + 1] + 1, substitution)
                rowMinimum = minOf(rowMinimum, current[j + 1])
            }
            if (rowMinimum > cutoff) return cutoff + 1
            val swap = previous
            previous = current
            current = swap
        }
        return previous[right.length]
    }

    companion object {
        const val MAX_RESULTS = 12
    }
}
