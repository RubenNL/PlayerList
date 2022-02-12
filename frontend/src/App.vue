<template>
  <v-app>
    <v-container v-if="!this.hasUrl">
      <v-text-field v-model="url" />
      <v-btn @click="get">go!</v-btn>
    </v-container>
    <div v-else-if="servers == null">Loading...</div>
    <v-expansion-panels v-else>
      <v-expansion-panel v-for="server in servers" :key="server.name">
        <v-expansion-panel-header>
          {{ server.name }}({{ server.players.length }})
        </v-expansion-panel-header>
        <v-expansion-panel-content>
          <v-tooltip v-for="player in server.players" :key="player.name" bottom>
            <template v-slot:activator="{ on, attrs }">
              <img
                v-bind="attrs"
                v-on="on"
                style="image-rendering: pixelated"
                :src="`https://crafatar.com/avatars/${player.uuid}?size=8`"
                height="32px"
                width="32px"
                :alt="player.name"
              />
            </template>
            <span>
              {{ player.name }}
            </span>
          </v-tooltip>
        </v-expansion-panel-content>
      </v-expansion-panel>
    </v-expansion-panels>
  </v-app>
</template>

<script>
import settings from "@/settings";

export default {
  name: "App",
  data: () => {
    return {
      servers: null,
      url: "",
      hasUrl: false,
    };
  },
  mounted() {
    this.url = settings.url;
    if (window.location.search) this.url = window.location.search.substring(1);
    if (this.url) this.get();
  },
  methods: {
    get: function () {
      this.hasUrl = true;
      fetch(this.url + "/data.json")
        .then((data) => data.json())
        .then((data) => (this.servers = data));
    },
  },
};
</script>
