<template>
  <div style="padding: 5% 5% 0 5%">
    <el-row
      :gutter="40"
      class="panel-group"
    >
      <el-col
        v-for="item in licenseOption"
        :key="item.id"
        :xs="12"
        :sm="12"
        :lg="6"
        class="card-panel-col"
      >
        <div
          class="card-panel"
          @click="routerChange('/explore', item)"
        >
          <div
            v-if="item.type === 'WRITING'"
            class="card-panel-icon-wrapper icon1"
          >
            <svg-icon
              icon-class="education"
              class-name="card-panel-icon"
            />
          </div>
          <div
            v-else
            class="card-panel-icon-wrapper icon2"
          >
            <svg-icon
              icon-class="education"
              class-name="card-panel-icon"
            />
          </div>
          <div class="card-panel-description">
            <div
              class="card-panel-text"
              style="float:right; "
            >
              <span style="float:right; font-weight : bold"> {{ item.name }} </span>
              <br><br>
              <span
                style="font-size:13px; float:right;"
              >
                {{ item.type === 'WRITING' ? "필기" : item.type === "PERFORM" ? "실기" : "" }}<br>
              </span>
            </div>
          </div>
        </div>
      </el-col>
    </el-row>
  </div>
</template>
<script>

export default {
  props: {
    licenseOption: {
      type: Array,
      default: true
    }
  },

  data() {
    return {
      param: {
        name: ''
      }
    }
  },

  methods: {
    routerChange(path, licenseInfo) {
      this.$router.push(path)
      this.$session.set('licenseInfo', {
        licenseID: licenseInfo.id,
        licenseName: licenseInfo.name,
        licenseCode: licenseInfo.code,
        subject: licenseInfo.subjects
      })
    }
  }
}
</script>

<style lang="scss" scoped>
.panel-group {
  margin-top: 18px;

  .card-panel-col {
    margin-bottom: 32px;
  }

  .card-panel {
    height: 108px;
    cursor: pointer;
    font-size: 12px;
    position: relative;
    overflow: hidden;
    color: #666;
    background: #fff;
    box-shadow: 4px 4px 40px rgba(0, 0, 0, .05);
    border-color: rgba(0, 0, 0, .05);

    &:hover {
      .card-panel-icon-wrapper {
        color: #fff;
      }
      .icon1 {
        background: #40c9c6;
      }
      .icon2 {
        background: #f4516c;
      }
    }
    .icon1 {
      color: #40c9c6;
    }
    .icon2 {
      color: #f4516c;
    }
    .card-panel-icon-wrapper {
      float: left;
      margin: 14px 0 0 14px;
      padding: 16px;
      transition: all 0.38s ease-out;
      border-radius: 6px;
    }
    .card-panel-icon {
      float: left;
      font-size: 48px;
    }
    .card-panel-description {
      float: right;
      font-weight: bold;
      margin: 26px;
      margin-left: 0px;
      .card-panel-text {
        line-height: 18px;
        color: rgba(0, 0, 0, 0.45);
        font-size: 16px;
        margin-bottom: 12px;
      }
      .card-panel-num {
        font-size: 20px;
      }
    }
  }
}

@media (max-width:550px) {
  .card-panel-description {
    display: none;
  }
  .card-panel-icon-wrapper {
    float: none !important;
    width: 100%;
    height: 100%;
    margin: 0 !important;
    .svg-icon {
      display: block;
      margin: 14px auto !important;
      float: none !important;
    }
  }
}
</style>
