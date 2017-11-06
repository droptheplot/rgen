# Random Generator CLI

Generate random UUID, Base64 etc strings from your terminal.

## Usage

    $ java -jar rgen.jar [args]

## Options

* `-f --format` UUID (default) or Base64.
* `-s --size` Number of strings (default: `1`).
* `-c --copy` Copy to clipboard (default: `false`).

## Examples

### Generate 10 UUID strings and copy to clipboard

    $ java -jar rgen.jar -f uuid -s 10 -c
