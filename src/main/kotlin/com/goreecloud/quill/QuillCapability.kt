package com.goreecloud.quill

/**
 * Canonical GoreeCloud Quill capability-family taxonomy.
 *
 * Quill is a capability system for GoreeCloud Keyboard. It is not a replacement
 * for the Keyboard product or for platform-wide systems such as Glaze UI,
 * Wardveil Security, Privacy Shield, or Everkeep.
 */
enum class QuillCapability(
    val displayName: String,
    val purpose: String,
) {
    FLOW("Quill Flow", "typing flow, prediction orchestration, and input continuity"),
    SENSE("Quill Sense", "context-aware typing assistance"),
    LEARN("Quill Learn", "local personalization and learning"),
    LEXICON("Quill Lexicon", "dictionary and vocabulary services"),
    CLIP("Quill Clip", "clipboard capability and retention policy"),
    SNIPPETS("Quill Snippets", "user-controlled reusable text snippets"),
    EDIT("Quill Edit", "selection and editor actions"),
    CASE("Quill Case", "selection-aware letter-case transformation"),
    GLIDE("Quill Glide", "gesture and swipe input"),
    COMPOSE("Quill Compose", "writing and composition assistance"),
    RECALL("Quill Recall", "user-controlled local recall and history"),
    PRIVATE("Quill Private", "privacy-preserving input-session controls"),
    TOOLBOX("Quill Toolbox", "utility input tools"),
    THEMES("Quill Themes", "keyboard appearance and theme capability"),
}
