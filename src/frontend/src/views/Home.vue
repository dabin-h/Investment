<template>
  <div class="home">
    <img alt="Vue logo" src="../assets/logo.png" />
    <Test msg="GWelcome to Your Vue.js App" />
  </div>
</template>

<script>
// @ is an alias to /src
import Test from "@/components/HelloWorld.vue";

export default {
  name: "Home",
  components: {
    Test
  }
};
</script>
