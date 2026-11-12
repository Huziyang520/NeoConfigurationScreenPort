# Neo Configuration Screen Port

An unofficial port of NeoForge's in-game configuration screen to other mod loaders, so that a mod's
configuration UI looks and behaves the same everywhere it runs.

This project is not affiliated with, endorsed by, or supported by NeoForged.

## What it provides

- NeoForge's configuration screen, ported to run outside NeoForge, keeping the original package names
  and entry points so that mod code written against NeoForge's screen works unchanged.
- A registration hook for mods that want to supply or extend their own screen.

## Building

```
./gradlew build
```

The jars land in `forge/build/libs` and `fabric/build/libs`.

## License

Mozilla Public License 2.0, except where a file header states otherwise. See `LICENSING.md` for the
list of adapted sources and `LICENSE-ASSETS.md` for art assets.
