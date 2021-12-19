<template>
  <div :class="{'has-logo':showLogo}">
    <logo v-if="showLogo" />
  </div>
</template>

<script>
import { mapGetters } from 'vuex'
import Logo from './Logo'
export default {
  components: {
    Logo
  },
  computed: {
    ...mapGetters([
      'permission_routes'
    ]),

    activeMenu() {
      const route = this.$route
      const { meta, path } = route
      if (meta.activeMenu) {
        return meta.activeMenu
      }
      return path
    },
    showLogo() {
      return this.$store.state.settings.sidebarLogo
    }
  }
}
</script>
