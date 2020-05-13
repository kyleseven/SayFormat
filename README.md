# SayFormat
A Spigot plugin to edit the format of /say for both the players and the console.

# Commands
- `Help` - Shows the help page
- `Say` - Runs `/say` as the player
- `Server` - Runs `/say` as the server
- `Reload` - Reloads `config.yml`

# Default Config
`/Plugins/SayFormat/config.yml`

```
Plugin configuration below

Changes 'say' sent from console
server: "&8[&bServer&8]&r "

Changes 'say' sent from players
Use %PLAYER% to set the player's name
player: "&8[&a%PLAYER%&8]&r "
```
