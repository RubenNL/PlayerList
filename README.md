# PlayerList

This is a plugin made to display who is on which server at the current time.

# Easy mode

## Requirements

This mode requires a https to http proxy of some sort. This is because the frontend is also served on https.  
As an alternative, you can follow the "hard mode" below.

## Steps

* Download the plugin from https://rubennl.github.io/PlayerList/playerlist.jar
* Launch bungee with the plugin installed.
* Choose a port to have the plugin function on. Make sure this port is open(no firewall)
* Edit the `config.yml` in the plugin folder. Set the port number here.
* In bungee, run `/playerlist reload`. This reloads the config, and starts the server on the port of your choosing.
* go to `https://rubennl.github.io/PlayerList/?https://<your ip>:<your port>`. example: `https://rubennl.github.io/PlayerList/?https://example.com:8081
* (optional) embed this page with an iFrame on your server website. `<iframe src="https://rubennl.github.io/PlayerList/?https://example.com:8081" />`

# Hard mode

## Plugin

* Download the plugin from https://rubennl.github.io/PlayerList/playerlist.jar
* Launch bungee with the plugin installed.
* Choose a port to have the plugin function on. Make sure this port is open(no firewall)
* Edit the `config.yml` in the plugin folder. Set the port number here.
* In bungee, run `/playerlist reload`. This reloads the config, and starts the server on the port of your choosing.

```
mvn package
```
Copy the release file from `target/playerlist-1.0-SNAPSHOT.jar` to your bungee installation.

## Frontend

Edit the `frontend/src/settings.js`, and set the correct URL here. This url should be reachable from the outside world.

Build the frontend using this:

```bash
cd frontend
npm ci
npm run build
```

Now, copy the full `frontend/dist` folder to a public webserver.

## Done!

Open your webpage.

# pre-build from github

You can also download the latest version from github.

you can find it here: [![Release](https://github.com/RubenNL/PlayerList/actions/workflows/release.yml/badge.svg)](https://github.com/RubenNL/PlayerList/actions/workflows/release.yml) Click here on the top item. There you will find a link for "release". This contains a zip, which has the frontend and the jar file.
