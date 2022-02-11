<template>
  <div>
    <div v-if="servers == null">Loading...</div>
    <div v-else v-for="server in servers" :key="server.name">
      {{ server.name }}
      <ul>
        <li v-for="player in server.playerNames" :key="player">{{ player }}</li>
      </ul>
    </div>
  </div>
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
