<template>
  <div class="main-container">
    <div class="main-title">
      <font class="font1">License Manager</font>
      <el-button
        icon="el-icon-circle-plus-outline"
        style="float:right; margin: 0 58px 0 0; width:100px; height :50px"
        @click="insertDrawerStatus(true)"
      > 추가 </el-button>
      <el-button
        type="danger"
        icon="el-icon-delete"
        style="float:right; margin: 0 30px 0 0; width:100px; height :50px"
        @click="deleteLicense()"
      > 삭제 </el-button>
    </div>

    <div class="div1" style="padding:0px 0px 70px 55%;">
      <div style="float:left; padding:0px 20px 0px 0px">
        <el-input
          v-model="param.name"
          style="width:500px"
          placeholder="검색 조건을 입력 하세요."
          @keyup.enter.native="clickSearch"
        />
      </div>
      <div style="float:left; padding:0px 0px 0px 0px">
        <el-button
          type="primary"
          @click="clickSearch"
        >검 색</el-button>
      </div>
    </div>
    <div class="div2">
      <el-table
        :data="tableData"
        :header-cell-style="{textAlign:'left'}"
        style="width:100%; height:100%;"
        @selection-change="handleSelectionChange"
      >

        <el-table-column type="expand">
          <template slot-scope="props">
            <el-table
              :data="props.row.subjects"
              border
              style="width: 100%"
            >
              <el-table-column
                prop="id"
                label="id"
                width="100"
              />
              <el-table-column
                prop="name"
                label="name"
              />
              <el-table-column
                prop="minScore"
                label="minScore"
                width="100"
              />

              <el-table-column
                label="inUse"
                width="180"
                align="left"
              >
                <template slot-scope="scope">
                  {{ scope.row.inUse === true ? 'Y' : 'N' }}
                </template>
              </el-table-column>
            </el-table>
          </template>
        </el-table-column>
        <el-table-column
          type="selection"
          width="55"
          align="left"
        />
        <el-table-column
          label="ID"
          prop="licenseId"
          width="100"
          align="left"
        />
        <el-table-column
          label="licenseName"
          align="left"
        >
          <template slot-scope="scope">
            {{ scope.row.licenseName }}
          </template>
        </el-table-column>
        <el-table-column
          label="licenseType"
          width="180"
          align="left"
        >
          <template slot-scope="scope">
            {{ scope.row.licenseType }}
          </template>
        </el-table-column>
        <el-table-column
          label="licenseAgency"
          width="180"
          align="left"
        >
          <template slot-scope="scope">
            {{ scope.row.licenseAgency }}
          </template>
        </el-table-column>
        <el-table-column
          label="passScore"
          width="180"
          align="left"
        >
          <template slot-scope="scope">
            {{ scope.row.passScore }}
          </template>
        </el-table-column>
        <el-table-column
          label="createdDate"
          width="180"
          align="left"
        >
          <template slot-scope="scope">
            {{ $moment(scope.row.createDate).format('YYYY-MM-DD') }}
          </template>
        </el-table-column>
        <el-table-column
          label="modifiedDate"
          width="180"
          align="left"
        >
          <template slot-scope="scope">
            {{ $moment(scope.row.modifiedDate).format('YYYY-MM-DD') }}
          </template>
        </el-table-column>

        <el-table-column
          align="right"
          width="100"
        >
          <template slot-scope="scope">
            <el-button
              size="mini"
              @click="updateDrawerStatus(true, scope.row)"
            >수정</el-button>
          </template>
        </el-table-column>
      </el-table>
      <div class="divpage">
        <pagination
          v-show="totalCount>0"
          :total="totalCount"
          :page.sync="param.page"
          :limit.sync="param.size"
          @pagination="fetchList"
        />
      </div>
      <div>
        <el-divider />
      </div>
    </div>
    <updateDrawer
      :popup-val="updateDrawerVal"
      :licence-info="licenceInfo"
      @refresh="fetchList()"
      @close:updatedrawer="updateDrawerStatus"
    />
    <insertDrawer
      :popup-val="insertDrawerVal"
      @refresh="fetchList()"
      @close:insertdrawer="insertDrawerStatus"
    />
  </div>
</template>

<script>
import Pagination from '@/components/Pagination'
import { licenseList, licenseListTotalCount, licenseDelete } from '@/ddaja-api/admin/license/License'
import updateDrawer from './components/updateDrawer'
import insertDrawer from './components/insertDrawer'

export default {
  name: 'AdminLicense',
  components: {
    Pagination,
    updateDrawer,
    insertDrawer
  },
  data() {
    return {
      tableData: [],
      totalCount: 10,
      param: {
        licenseID: undefined,
        name: '',
        page: 1,
        size: 10
      },
      licenceInfo: {
        lID: 0
      },
      selectList: [],
      updateDrawerVal: false,
      insertDrawerVal: false
    }
  },
  created() {
    this.fetchList()
  },
  methods: {
    async fetchList() {
      await licenseList(this.param).then(response => {
        var tableData = []
        response.items.forEach(x => {
          var licenseType = (x.item.type === 'WRITING') ? '필기' : '실기'
          tableData.push({
            licenseId: x.item.id,
            licenseName: x.item.name,
            licenseType: licenseType,
            subjects: x.item.subjects,
            passScore: x.item.passScore,
            licenseAgency: x.item.agency,
            createdDate: x.item.createdDate,
            modifiedDate: x.item.modifiedDate
          })
        })

        this.tableData = tableData
        this.page = response.page
      })

      await licenseListTotalCount(this.param).then(response => {
        this.totalCount = response.totalCount
      })
    },
    async deleteLicense() {
      for (var index in this.selectList) {
        await this.licenseDelete(this.selectList[index])
      }

      await this.fetchList()
    },
    async licenseDelete(lID) {
      var param = {
        id: lID
      }
      await licenseDelete(param).then(response => {
        this.$message({
          message: 'License Delete Success',
          type: 'success'
        })
      })
    },
    updateDrawerStatus(val, row) {
      this.updateDrawerVal = val
      if (val) {
        this.licenceInfo.lID = row.licenseId
      }
    },
    insertDrawerStatus(val) {
      this.insertDrawerVal = val
    },
    clickSearch() {
      this.param.page = 0
      this.param.size = 10
      this.fetchList()
    },
    handleSelectionChange(val) {
      this.selectList = []
      val.forEach(x => {
        this.selectList.push(x.licenseId)
      })
    }
  }
}
</script>

<style lang="scss" scoped>
@import url('https://fonts.googleapis.com/css2?family=Do+Hyeon&family=Jua&display=swap');
.main-container{
width: 100%;
text-align: center;
padding: 30px;
overflow:auto;
background-color: rgb(255, 255, 255);
.main-title{
text-align: left;
margin-top: 5px;
margin-bottom: 30px;
color: rgb(0, 0, 0);
margin-left: 1%;
margin-right: 1%;
padding: 1.5% 2% 0% 4%;
.font1{
font-size: 50px;
font-family: 'Do Hyeon', sans-serif;
}
.font2{
font-size: 40px;
padding: 0 0 0 30px;
font-family: 'Do Hyeon', sans-serif;
}
}
.div1{
width: 90%;
margin: 0 5% 0 5%;
padding: 2% 0 3% 0;
.select1{
width: 200px;
}
.btn1{
width: 100px;
}
}
.div2{
width: 90%;
margin: 0 5% 0 5%;
}
}
</style>
