<template>
  <div class="main-container">
    <el-drawer
      v-loading="loading"
      :visible.sync="popupVal"
      :with-header="false"
      :before-close="popupClose"
      style="width:100%"
    >
      <div class="div1">
        <span class="span1">License Update</span>
        <div style="float:right; padding: 15px 150px 0  0">
          <el-button
            type="primary"
            @click="onSubmit"
          >Update</el-button>
        </div>
      </div>

      <div style="padding:0 30px 10px  30px">
        <el-form
          ref="updateInfo"
          :model="updateInfo"
          label-width="200px"
        >
          <el-form-item label="License Name">
            <el-input
              ref="name"
              v-model="updateInfo.name"
              style="width:750px"
            />
          </el-form-item>

          <el-form-item label="License Type">
            <el-select ref="type" v-model="updateInfo.type" style="width:750px" placeholder="Select">
              <el-option
                v-for="item in licenseTypes"
                :key="item.value"
                :label="item.label"
                :value="item.value"
              />
            </el-select>
          </el-form-item>

          <el-form-item label="license Agency">
            <el-autocomplete
              ref="agency"
              v-model="updateInfo.agency"
              class="inline-input"
              style="width:750px"
              :fetch-suggestions="querySearch"
            />
          </el-form-item>
          <el-form-item label="license pass Score">
            <el-input
              ref="passScore"
              v-model="updateInfo.passScore"
              type="number"
              style="width:750px"
            />
          </el-form-item>
          <el-form-item label="license Code">
            <el-select ref="type" v-model="updateInfo.code" style="width:750px" placeholder="Select">
              <el-option
                v-for="item in codeTypes"
                :key="item.value"
                :label="item.label"
                :value="item.value"
              />
            </el-select>
          </el-form-item>
        </el-form>
      </div>

      <div class="div1">
        <span class="span2">Subject ADD</span>
        <div style="float:right; padding: 15px 150px 0  0">
          <el-button
            type="primary"
            @click="addSubject"
          >ADD</el-button>
        </div>
      </div>

      <div style="padding:0 30px 10px  30px">
        <el-form
          ref="param"
          :model="param"
          label-width="200px"
        >
          <el-form-item v-for="item in updateInfo.subjects" :key="item.id" label="Name / Min Score">
            <el-input
              ref="subjectName"
              v-model="item.name"
              style="width:440px"
            />
            <el-input
              ref="subjectMinScore"
              v-model="item.minScore"
              type="number"
              style="width:300px"
            />
            <el-button icon="el-icon-close" @click="removeSubject(item)" />
          </el-form-item>
        </el-form>
      </div>
    </el-drawer>
  </div>
</template>

<script>
import _ from 'lodash'
import { licenseById, updateLicense, updateSubject, licenseCodes } from '@/ddaja-api/admin/license/License'

export default {
  name: 'AdminLicenseInsert',

  components: {
  },

  props: {
    popupVal: {
      type: Boolean,
      default: false
    },
    licenceInfo: {
      type: Object,
      default: function() {
        return {
          lID: 0
        }
      }
    }
  },

  data() {
    return {
      param: {
        name: '',
        lCode: 'NONE',
        inUse: true,
        agency: '',
        passScore: 60,
        licenseType: 'WRITING'
      },
      updateInfo: {
        lID: 0,
        agency: '',
        inUse: true,
        name: '',
        code: '',
        passScore: 0,
        subjects: [
          {
            id: 0,
            name: '',
            minScore: 0,
            inUse: true
          }
        ]
      },
      tableData: [],
      loading: false,

      licenseTypes: [
        { label: '필기', value: 'WRITING' },
        { label: '실기', value: 'PERFORM' }
      ],
      codeTypes: [],
      agenceTypes: [
        { label: '', value: '선택 안함' },
        { label: 'Q-Net', value: 'Q-Net' },
        { label: '네트워크 공단', value: '네트워크 공단' },
        { label: 'Oracle', value: 'Oracle' }
      ],
      subjects: [
        { id: 1, name: '', minScore: 0, inUse: 'Y' }
      ]
    }
  }, watch: {
    popupVal(val) {
      var param = {
        id: this.licenceInfo.lID
      }
      licenseById(param).then(response => {
        var license = response.item
        console.log(license)
        this.updateInfo = license
      })
    }
  },

  created() {
    this.setCodeTypes()
  },

  methods: {
    async onSubmit() {
      if (!this.verification()) { return }

      this.loading = true

      await updateLicense(this.updateInfo).then(response => {
      })

      for (var index in this.updateInfo.subjects) {
        var subject = this.updateInfo.subjects[index]
        var param = {
          id: subject.id,
          lID: this.licenceInfo.lID,
          name: subject.name,
          minScore: subject.minScore,
          inUse: true
        }
        await updateSubject(param).then(response => {

        })
      }
      this.loading = false
      this.popupClose()
      this.$alert('SAVE SUCESS')
    },

    addSubject() {
      var id = (_.max(_.map(this.updateInfo.subjects, 'id')) + 1)
      this.updateInfo.subjects.push({ id: id, name: '', minScore: 0, inUse: 'Y' })
    },

    removeSubject(subject) {
      _.remove(this.updateInfo.subjects, function(x) {
        return x.id === subject.id
      })

      var tempList = this.updateInfo.subjects
      this.updateInfo.subjects = []
      this.updateInfo.subjects = tempList
    },

    verification() {
      if (this.updateInfo.name.trim() === '') {
        this.$alert('LICENSE NAME NULL ERROR')
        this.$refs.name.focus()
        return false
      }

      if (this.updateInfo.agency === '') {
        this.$alert('TITLE AGENCY NULL ERROR')
        this.$refs.agency.focus()
        return false
      }

      if (this.updateInfo.passScore <= 0) {
        this.$alert('PASS SCORE NULL ERROR')
        this.$refs.passScore.focus()
        return false
      }

      if (this.updateInfo.type === '') {
        this.$alert('TYPE NULL ERROR')
        this.$refs.type.focus()
        return false
      }

      for (var index in this.updateInfo.subjects) {
        var vo = this.updateInfo.subjects[index]
        if (vo.name.trim() === '' || vo.minScore === 0) {
          this.$alert('Subject SCORE NULL ERROR')
          return false
        }
      }
      return true
    },

    popupClose() {
      this.$emit('refresh')
      this.$emit('close:updatedrawer', false)
    },

    querySearch(queryString, cb) {
      var links = this.agenceTypes
      var results = queryString ? links.filter(this.createFilter(queryString)) : links
      cb(results)
    },

    createFilter(queryString) {
      return (link) => {
        return (link.value.toLowerCase().indexOf(queryString.toLowerCase()) === 0)
      }
    },

    setCodeTypes() {
      licenseCodes().then(response => {
        response.items.forEach(x => {
          this.codeTypes.push({ label: x.name + '(' + x.value + ')', value: x.value })
        })
      })
    }
  }
}
</script>

<style lang="scss" scoped>
@import url('https://fonts.googleapis.com/css2?family=Kirang+Haerang&display=swap');
.main-container{ width: 100%; .div1{ text-align: left; margin-top: 5px; margin-bottom: 30px; color: rgb(0, 0, 0); margin-left: 1%; margin-right: 1%; padding: 4% 2% 2% 4%; .span1{ font-size: 50px; font-family: 'Do Hyeon', sans-serif; } .span2{ font-size: 40px; font-family: 'Do Hyeon', sans-serif; } } .div2{ width : 100%; margin: 1% 4% 2% 4%; overflow:auto; .div2-1{ width : 100%; float :left ; padding: 1% 1% 1% 0%;text-align : right; font-weight:bold;.div2-1-1{ width : 5%; padding: 1% 3% 1% 6%; float :left ;} .div2-1-2{ width : 82%; padding: 1% 1% 1% 1%; float :left ; text-align: left; }} .div2-2{ width : 100%; text-align : left;float :left ; padding: 1% 10% 1% 10%; }} .pointer{ cursor:pointer;}} ::v-deep .el-drawer{ width: 70% !important;}

::v-deep  .el-drawer__body {
		height: 100%;
		box-sizing: border-box;
		overflow-y: auto;
}
</style>
