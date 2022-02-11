# PlayerList

This is a plugin made to display who is on which server at the current time.

# SETUP

## Plugin

build the plugin using

```
mvn package
```
Copy the release file from `target/playerlist-1.0-SNAPSHOT.jar` to your bungee installation.

Make sure port 8081 is not firewalled. This is the port that is used for the data.
This is currently not configurable, but will be in the future.
## Frontend

Edit the `frontend/src/settings.js`, and set the correct URL here. This url should be reachable from the outside world.

Build the frontend using this:

```bash
cd frontend
npm ci
npm run build
```
Now, copy the full `frontend/dist` folder to a public webserver.

# Done!

Open your webpage.

# pre-build from github

You can also download the latest version from github. Currently, this doesn't have a config option, so it only works on localhost(the default setting).

you can find it here: [![Release](https://github.com/RubenNL/PlayerList/actions/workflows/release.yml/badge.svg)](https://github.com/RubenNL/PlayerList/actions/workflows/release.yml) Click here on the top item. There you will find a link for "release". This contains a zip, which has the frontend and the jar file.
