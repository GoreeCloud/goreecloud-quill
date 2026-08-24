# GoreeCloud Quill

GoreeCloud Quill is the first-party capability-family foundation for advanced typing, writing assistance, editing, personalization, dictionaries, clipboard, and intelligent input technologies used by GoreeCloud Keyboard.

Quill is not a separate keyboard application. GoreeCloud Keyboard remains the product; Quill organizes the specialized capability systems that power it.

## Capability families

- Quill Flow
- Quill Sense
- Quill Learn
- Quill Lexicon
- Quill Clip
- Quill Snippets
- Quill Edit
- Quill Case
- Quill Glide
- Quill Compose
- Quill Recall
- Quill Private
- Quill Toolbox
- Quill Themes

## Foundation status

The native foundation now provides:

- canonical capability taxonomy;
- deterministic local Quill Lexicon lookup and prefix suggestions;
- bounded Quill Flow completion and correction ranking;
- privacy-gated in-memory Quill Learn personalization with explicit forget and clear operations;
- user-controlled Quill Snippets lookup and expansion;
- pure Quill Case selection transformation logic;
- fail-closed Quill Private policy for unknown, password, private, and no-personalized-learning editor states;
- text-free Quill Clip retention policy;
- bounded host integration contracts for lexicon, snippets, time, and typed diagnostics;
- unit-test coverage across privacy, case transformation, clipboard retention, lexicon, Flow suggestions, Learn, and Snippets;
- GitHub Actions CI for the Kotlin/JVM core.

The core deliberately avoids Android framework dependencies. GoreeCloud Keyboard will provide native Android adapters at the application boundary.

## GoreeCloud platform integration

Quill is designed to integrate with the current GoreeCloud platform contracts:

- **Glaze UI** — design and interaction language.
- **Wardveil Security** — security state, protected-field policy, and evidence.
- **Privacy Shield** — privacy controls, data minimization, and privacy state.
- **Everkeep** — resilience, portability, preservation, and recovery requirements for persistent Quill data.

These names represent functional contracts, not decorative badges. Stable qualification requires evidence-backed integration in the consuming application.

## Privacy boundary

Quill core does not require network access and does not expose raw typed text through its diagnostics contracts. Password fields, unknown editors, editor requests that disable personalized learning, and explicit Quill Private sessions fail closed for learning, context reads, automatic clipboard retention, swipe-learning retention, and typed-text diagnostics.

See [`docs/architecture.md`](docs/architecture.md) for the current architecture and validation boundary.
