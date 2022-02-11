<template>
  <div v-if="servers == null">Loading...</div>
  <v-expansion-panels v-else>
    <v-expansion-panel v-for="server in servers" :key="server.name">
      <v-expansion-panel-header>
        {{ server.name }}({{ server.playerNames.length }})
      </v-expansion-panel-header>
      <v-expansion-panel-content>
        <v-list>
          <v-list-item v-for="player in server.playerNames" :key="player">
            <v-list-item-content>
              {{ player }}
            </v-list-item-content>
          </v-list-item>
        </v-list>
      </v-expansion-panel-content>
    </v-expansion-panel>
  </v-expansion-panels>
</template>

<script>
export default {
  name: "App",
  data: () => {
    return {
      servers: null,
    };
  },
  mounted() {
    fetch(window.dataUrl)
      .then((data) => data.json())
      .then((data) => (this.servers = data));
  },
};
</script>
