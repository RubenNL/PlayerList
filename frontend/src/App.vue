<template>
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
        <v-list>
          <v-list-item v-for="player in server.players" :key="player.name">
            <v-list-item-content>
              <v-container align="center" justify="center">
                <img
                  style="image-rendering: pixelated"
                  :src="`https://crafatar.com/avatars/${player.uuid}?size=8`"
                  height="64px"
                  width="64px"
                  class="img"
                  alt=""
                />
                <br />
                {{ player.name }}
              </v-container>
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
