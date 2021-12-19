<template>
  <div>
    <el-dialog
      :visible.sync="popupVal"
      :before-close="handleClose"
    >
      <div>
        <template>
          <article-detail
            :is-edit="false"
            @close:popup="popupClose"
          />
        </template>
      </div>
    </el-dialog>
  </div>
</template>

<script>
import ArticleDetail from './components/ArticleDetail'
export default {
  name: '',
  components: {
    ArticleDetail
  }, props: {
    popupVal: {}
  },
  data() {
    return {

    }
  },
  created() { },
  methods: {
    handleClose(done) {
      this.$confirm('종료 하시겠습니까 ?')
        .then(_ => {
          this.popupClose(false)
        })
        .catch(_ => {})
    },
    popupClose(val) {
      this.$emit('close:popup', val)
    }
  }
}
</script>
