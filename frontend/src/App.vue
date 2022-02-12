<template>
  <div v-if="servers == null">Loading...</div>
  <v-expansion-panels v-else>
    <v-expansion-panel v-for="server in servers" :key="server.name">
      <v-expansion-panel-header>
        {{ server.name }}({{ server.players.length }})
      </v-expansion-panel-header>
      <v-expansion-panel-content>
        <v-list>
          <v-list-item v-for="player in server.players" :key="player.name">
            <v-list-item-content>
              {{ player.name }}
            </v-list-item-content>
          </v-list-item>
        </v-list>
      </v-expansion-panel-content>
    </v-expansion-panel>
  </v-expansion-panels>
</template>

<script>
import settings from "@/settings";

export default {
  name: "App",
  data: () => {
    return {
      servers: null,
    };
  },
  mounted() {
    fetch(settings.url + "/data.json")
      .then((data) => data.json())
      .then((data) => (this.servers = data));
  },
};
</script>
