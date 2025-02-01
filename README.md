# SayFormat
A Spigot plugin to edit the format of /say for both the players and the console.

# Commands
- `Help` - Shows the help page
- `Say` - Runs `/say` as the player
- `Server` - Runs `/say` as the server
- `Reload` - Reloads `config.yml`
- `Version` - Checks version of the plugin

# Default Config
`/Plugins/SayFormat/config.yml`

```
#Plugin configuration below

#Changes 'say' sent from console
server: "&8[&b&lServer&8]&c "

#Changes 'say' sent from players
#Use %PLAYER% to set the player's name
player: "&8[&a%PLAYER%&8]&r "
```
# Permissions
- `sf.server` 
- `sf.sudosay`
- `sf.reload`