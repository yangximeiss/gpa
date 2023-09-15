<template>
  <div>
    <!--导航栏-->
    <el-menu
        router: default-active="$route.path"
        :router="true"
        class="el-menu-vertical"
      >
      <el-header style="text-align: center; font-size: 15px"> 
      </el-header>
        <el-menu-item index="Main">          
           <span><i class="el-icon-document"></i>学生信息</span>
        </el-menu-item>
        <el-menu-item index="Course">
          <span><i class="el-icon-document-copy"></i>课程信息</span>
        </el-menu-item>
      </el-menu>
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

<script>
  export default {
    data() {
      return {
        isCollapse: true
      };
    },
    methods: {
      handleOpen(key, keyPath) {
        console.log(key, keyPath);
      },
      handleClose(key, keyPath) {
        console.log(key, keyPath);
      }
    }
  }
</script>
