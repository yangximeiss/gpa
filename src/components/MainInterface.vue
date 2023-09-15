<template>
    <div >
        <el-container style="height: 700px; border: 1px solid #eee">
            <el-aside width="150px" style="background-color: rgb(238, 241, 246)">
              <Nac/>
              <el-main><router-view/></el-main>
            </el-aside>
            <el-container>
              
                <!--导航，放置查询和新增信息按钮-->
              <el-header style="text-align: right; font-size: 12px">
                <el-dropdown>
                  <i class="el-icon-setting" style="margin-right: 15px"></i>
                  <el-dropdown-menu slot="dropdown">
                    <el-dropdown-item>
                      <el-button @click="getStudents">查 询</el-button>
                    </el-dropdown-item>
                    <el-dropdown-item >
                      <el-button @click="add">增 加</el-button>
                    </el-dropdown-item>
                    <el-dropdown-item >
                      <el-button @click="login">退 出</el-button>
                    </el-dropdown-item>
                  </el-dropdown-menu>
                </el-dropdown>
                <span>学生管理系统</span>
              </el-header>

              <!--放置学生信息-->
              <el-main >
                <el-table :data="students_page" stripe style="width: 100%">
                  <el-table-column prop="id" label="学号">
                    <template #default="scope">
                      <el-input
                        v-if="scope.row.isEdit"
                        v-model="scope.row.id"
                        type="text"
                        placeholder="请填写"
                      />
                      <span v-else>{{ scope.row.id }}</span>
                    </template>
                  </el-table-column>
                  <el-table-column prop="name" label="姓名">
                    <template #default="scope">
                      <el-input
                        v-if="scope.row.isEdit"
                        v-model="scope.row.name"
                        type="text"
                        placeholder="请填写"
                      />
                      <span v-else>{{ scope.row.name }}</span>
                    </template>
                  </el-table-column>
                  <el-table-column prop="gender" label="性别">
                    <template #default="scope">
                      <el-input
                        v-if="scope.row.isEdit"
                        v-model="scope.row.gender"
                        type="text"
                        placeholder="请填写"
                      />
                      <span v-else>{{ scope.row.gender }}</span>
                    </template>
                  </el-table-column>
            
                  <el-table-column label="操作" fixed="right">
                    <template #default="scope">
                      <div v-if="scope.row.isEdit">
                        <el-button size="mini" type="primary" @click="handleRowSave(scope.row)">保存</el-button>
                        <el-button size="mini"  type="danger" @click="handleRowCancel(scope.row, scope.$index)">取消</el-button>
                      </div>
                      <div v-else>
                        <el-button size="mini" type="primary" @click="handleRowEdit(scope.row)"> 编辑</el-button>
                        <el-button size="mini"  type="danger" @click="handleRowDelete(scope.$index)">删除</el-button>
                      </div>
                    </template>
                  </el-table-column>
                </el-table>

                <el-input style="width: 250px; " placeholder="请输入名字查询" v-model="search_name"  clearable>
                  <el-button slot="append" icon="el-icon-search" @click="search"></el-button>
                </el-input>
              
                <!--分页管理-->
                <el-pagination 
                  @size-change="handleSizeChange" 
                  @current-change="handleCurrentChange" 
                  :page-size="pageSize" 
                  :current-page="currentPage" 
                  :page-sizes="[5, 10, 20, 40, 100]" 
                  layout="total, sizes, prev, pager, next, jumper"
                  :total="students.length">
                </el-pagination>
              </el-main>
            </el-container>
          </el-container>          
    </div>
</template>

<style>
  .el-pagination {
    justify-content : center;
  }
  .el-header {
    background-color: #B3C0D1;
    color: #333;
    line-height: 60px;
  }
  
  .el-aside {
    color: #333;
  }
</style>

<script >
import Nac from './NaComponents.vue';
import axios from 'axios';
import { set } from 'lodash-es'
  export default {
    components: {
       Nac
  },
    data(){

        return {
            search_name:'',
            students : [],
            currentPage : 1,
            pageSize : 10,
           
        }
        
    },
    created(){
      this.getStudents()
    },
    methods: {
      search(){
        axios({
          url:"http://localhost:8100/search",
          method:"POST",
          data:{
            name:this.search_name
          }
        }).then(res=>{
          this.students = res.data;
        })
      },

      getStudents() {
            axios({
                url: "http://localhost:8100/student",
                method: "GET"
            }).then(res =>{
                console.log(res.data)
                this.students = res.data
            })
        },
        login(){
          this.$router.push('/Login');
        },
        handleCurrentChange(currentPage) {
          this.currentPage = currentPage;
        },

        handleSizeChange(val) {
          this.pageSize = val;
        },
        handleRowEdit (row) {
          set(row, 'isEdit', true)
         },
         handleRowDelete(index)  {      
            let item = this.students[index]
            axios({
                url: "http://localhost:8100/delstudent",
                method : "DELETE",
                data : {
                    id: item.id,
                    name : "",
                    gender: "",
                    gpa : 0
                }
            }).then(res => {
                    console.log(res.data)
                    // 如删除成功，在表格中删除该行
                    this.students.splice(index, 1)
            }) 
        },

        // 新增联络人
        add() {
            this.students.push({
                id : '',
                name: '',
                gender: '',
                isEdit: true, // 当这个值为true时表示可以编辑
                isNew: true
            })
        },
          handleRowCancel(row, index)  {
            if (row.isNew)
                this.students.splice(index, 1)
            else {
                // TODO: 恢复单个学生数据
                set(row, 'isEdit', false)
            }
        },

        handleRowUpdate(row)  {
            console.log(row)
            axios({
                url: "http://localhost:8100/updatestudent",
                method : "PUT",
                data : {
                        id: row.id,
                        name : row.name,
                        gender: row.gender,
                }
            }).then(res => {
                    console.log(res.data)
                    row.isEdit = false
            }) 
        },

        handleRowInsert(row)  {
            //this.students.splice(index, 1)
            // 如何区分是新数据还是旧数据 ?
            console.log(row)
            axios({
                url: "http://localhost:8100/addstudent",
                method : "POST",
                data : {
                        id: row.id,
                        name : row.name,
                        gender: row.gender,
                }
            }).then(res => {
                    console.log(res.data)
                    row.isEdit = false
                    row.isNew = false
            }) 
        },

        handleRowSave(row)  {
            //this.students.splice(index, 1)
            // 如何区分是新数据还是旧数据 ?
            console.log(row)
            if (row.isNew) {
                this.handleRowInsert(row)
            } else {
                this.handleRowUpdate(row);
            }
        },
    },
    computed : {
      students_page() {
        return this.students.slice((this.currentPage - 1) * this.pageSize, 
                                    this.currentPage * this.pageSize);
      }
    }
  };
</script>