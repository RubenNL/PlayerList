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
