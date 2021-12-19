<template>
  <div style="margin : 10px 30px 100px 30px; padding : 0 0 100px 0; overflow:hidden; ">
    <div class="main-logo-div" style="height:150px;">

      <div style="float:left; width:100%; padding: 3% 0 1% 7%; height:100px;">
        <img :src="Logo" class="logo">
      </div>

      <div style="float:right; width: 80%; height:100px; padding:4% 5% 0 0">
        <div style="float:right; padding:0 0 0 3% ">
          <el-button type="primary" @click="setLisenseList()">검 색</el-button>
        </div>
        <div style="float:right; padding:0 0 0 0">
          <el-input v-model="param.name" style="width:500px" placeholder="자격증 명을 검색할 수 있습니다" @keyup.enter.native="setLisenseList()" />
        </div>
      </div>
    </div>

    <div style="margin:0 0 100px 0; padding:0 0 100px 0">
      <panel-group
        :license-option="licenseOption"
      />
    </div>
  </div>
</template>

<script>
import mainLogo from '@/assets/DDaJa-Logo/main_logo.png'
import PanelGroup from './component/PanelGroup'
import { fetchLicenseList } from '@/ddaja-api/user/dashboard/dashboard'

export default {
  components: {
    PanelGroup
  },

  data() {
    return {
      Logo: mainLogo,
      licenseOption: [],
      param: {
        name: ''
      }
    }
  },

  created() {
    this.setLisenseList()
  },

  methods: {
    async setLisenseList() {
      await fetchLicenseList(this.param).then(response => {
        this.licenseOption = []
        response.items.forEach(x => {
          this.licenseOption.push(x.item)
        })
      })
    },

    sessionSave(licenseInfo) {
      var info = {
        licenseID: licenseInfo.id,
        licenseName: licenseInfo.name,
        licenseCode: licenseInfo.code,
        subject: licenseInfo.subjects
      }
      this.$session.set('licenseInfo', info)
    }
  }
}
</script>

<style lang="scss" scoped>
.clearfix:before,
.clearfix:after {
  display: table;
  content: "";
}
.clearfix:after {
  clear: both
}

.box-card {
  width: 480px;
}
</style>
