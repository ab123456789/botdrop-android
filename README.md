# BotDrop

Run AI agents on your Android phone — no terminal, no CLI, just a guided setup.

BotDrop wraps [OpenClaw](https://github.com/nicepkg/openclaw) into a user-friendly Android app. Install, configure, and manage your AI agent in 4 simple steps.

## Features

- **Guided 4-step setup** — Agent → Install → Auth → Channel
- **Multi-provider support** — Anthropic, OpenAI, Google Gemini, OpenRouter, and more
- **Telegram & Discord integration** — Chat with your agent through your favorite messenger
- **Background gateway** — Keeps your agent running with auto-restart
- **No terminal required** — Everything happens through the GUI

## Installation

### Download APK

Download the latest APK from [Releases](../../releases).

### Build from Source

Prerequisites:
- Android SDK Platform 36
- NDK 29.0.14206865
- JDK 17
- Node.js and npm (for preparing the bundled runtime)

Prepare the OpenClaw runtime and QQ plugin before building; they are required for the app's offline setup.

```bash
git clone https://github.com/louzhixian/botdrop.git
cd botdrop

export BOTDROP_BUNDLED_OPENCLAW_VERSION=2026.3.13
QQBOT_VERSION=1.5.4
./scripts/build-openclaw-bundle.sh "$BOTDROP_BUNDLED_OPENCLAW_VERSION"
./scripts/build-qqbot-plugin-bundle.sh "$QQBOT_VERSION"

export BOTDROP_OPENCLAW_BUNDLE_TGZ="$PWD/build/openclaw-bundles/openclaw-runtime-${BOTDROP_BUNDLED_OPENCLAW_VERSION}.tar"
export BOTDROP_QQBOT_PLUGIN_DIR="$PWD/build/openclaw-bundles/qqbot-sliverp-qqbot-${QQBOT_VERSION}/package"
./gradlew assembleDebug
```

Bundle preparation requires network access. The APK will be at `app/build/outputs/apk/debug/` (arm64 only).

## Architecture

BotDrop is built on [Termux](https://github.com/termux/termux-app), providing a Linux environment for running Node.js-based AI agents on Android.

```
┌──────────────────────────────────┐
│     BotDrop UI (app.botdrop)     │
├──────────────────────────────────┤
│     Termux Core (com.termux)     │
├──────────────────────────────────┤
│  Linux Environment (proot/apt)   │
├──────────────────────────────────┤
│  OpenClaw + Node.js + npm        │
└──────────────────────────────────┘
```

See [docs/design.md](docs/design.md) for the original design proposal; some flows differ from the current implementation.

## Contributing

See [CONTRIBUTING.md](CONTRIBUTING.md) for guidelines.

## Crash Reporting

See [docs/crashlytics.md](docs/crashlytics.md) for Firebase Crashlytics setup, build behavior, and privacy notes.

## License

This project is licensed under the [GNU General Public License v3.0](LICENSE).

Built on [Termux](https://github.com/termux/termux-app) (GPLv3).
