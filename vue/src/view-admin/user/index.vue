<template>
  <div class="main-container">
    <div class="main-title">
      <font class="font1">User Manager</font>
    </div>

    <div class="div1" style="padding:0px 0px 75px 45%;">
      <div style="float:left; padding:1px 20px 0px 0px">

        <el-select v-model="searchFiled" placeholder="Select">
          <el-option
            v-for="item in options"
            :key="item.value"
            :label="item.label"
            :value="item.value"
          />
        </el-select>

      </div>
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
        style="width:100%; height:100%;"
      >
        <el-table-column
          label="ID"
          prop="item.userId"
          width="280"
        />

        <el-table-column
          label="Nick Name"
          prop="item.nickName"
          width="280"
        />

        <el-table-column
          label="Email"
          prop="item.email"
        />

        <el-table-column
          label="등록일"
          width="180"
        >
          <template slot-scope="scope">
            {{ $moment(scope.row.item.createDate).format('YYYY-MM-DD') }}
          </template>
        </el-table-column>

        <el-table-column
          label="수정일"
          width="180"
        >
          <template slot-scope="scope">
            {{ $moment(scope.row.item.modifiedDate).format('YYYY-MM-DD') }}
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

        <el-table-column
          align="right"
          width="100"
        >
          <template slot-scope="scope">
            <el-button
              size="mini"
              @click="detailDrawerStatus(true, scope.row)"
            >상세</el-button>
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
    <detailDrawer
      :id="id"
      :popup-val="detailDrawerVal"
      @close:detaildrawer="detailDrawerStatus"
    />
    <updateDrawer
      :id="id"
      :popup-val="updateDrawerVal"
      @close:updatedrawer="updateDrawerStatus"
    />

  </div>
</template>

<script>

import Pagination from '@/components/Pagination'
import { userList, userListTotalCount } from '@/ddaja-api/admin/user/User'
import detailDrawer from './components/detailDrawer'
import updateDrawer from './components/updateDrawer'

export default {
  name: '',

  components: {
    Pagination,
    detailDrawer,
    updateDrawer
  },

  data() {
    return {
      options: [{
        value: 'ID',
        label: 'ID'
      }, {
        value: 'NickName',
        label: 'NickName'
      }, {
        value: 'Email',
        label: 'Email'
      }],
      searchFiled: 'ID',
      tableData: [],
      search: '',
      id: 1,
      totalCount: 0,
      page: {},
      param: {
        licenseID: 0,
        name: '',
        page: 1,
        size: 10
      },
      detailDrawerVal: false,
      updateDrawerVal: false
    }
  },

  created() {
    this.fetchList()
  },

  methods: {

    fetchList() {
      userList(this.param).then(response => {
        this.tableData = response.items
        this.page = response.page
      })

      userListTotalCount(this.param).then(response => {
        this.totalCount = response.totalCount
      })
    },

    updateDrawerStatus(val, row) {
      this.updateDrawerVal = val
      this.id = (row == undefined) ? 0 : row.item.id
    },
    detailDrawerStatus(val, row) {
      this.detailDrawerVal = val
      this.id = (row == undefined) ? 0 : row.item.id
    },
    clickSearch() {
      this.param.page = 0
      this.param.size = 10
      this.fetchList()
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
