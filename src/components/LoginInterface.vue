<template>
    <div>
  <el-form :rules="rules" ref="loginForm" :model="loginForm" class="loginContainer">
     <h3 class="loginTitle" style="font-size: 25px; ">
       系 统 登 录
     </h3>
      <el-form-item label="用户名" prop="username">
          <el-input type="text" v-model="loginForm.username" placeholder="请输入用户名" >
          </el-input>
      </el-form-item>
      <el-form-item label="密码" prop="password">
          <el-input type="password" v-model="loginForm.password" placeholder="请输入密码" >
          </el-input>
      </el-form-item>
      <el-form-item  style=" text-align :center;">
        <el-button type="primary" @click="submitLogin">登 录</el-button>
        <el-button @click="regisForm">注 册</el-button>
      </el-form-item>
  </el-form>
    </div>
</template>

<script>
export default {
 
    data(){
      return{
          captchaUrl: "",
          loginForm:{
              username:"",
              password:"",
          },
          checked: true,
          rules:{
              username:[{required:true,message:"请输入用户名",trigger:"blur"},{ min: 0, max: 14, message: '长度在 0 到 14 个字符', trigger: 'blur' }
              ],
              password:[{required:true,message:"请输入密码",trigger:"blur"},{ min: 6, max: 8, message: '长度在 5 到 8 个字符', trigger: 'blur' }],
          }

      }
  },
    methods:{
      submitLogin(){
        if(this.loginForm.username===localStorage['username'] && this.loginForm.password===localStorage['password'])
       {
         this.$router.replace('/Main');//如果输入的名字以及密码正确路由跳转至个人页面
       } 
       else if(this.loginForm.username==='')//名字为空
       {
         alert('用户名不为空');
       }
       else if(this.loginForm.password==='')//密码为空
       {
         alert('密码不为空');
       }
      else{
        alert('账号不存在，请注册后登录');//查无此号
      }
      },
      regisForm(){
        this.$router.push('/Register')
      }
  }
};
</script>

<style  scoped>
    .loginContainer{
        border-radius: 15px;
        background-clip: padding-box;
        margin: 180px auto;
        width: 350px;
        padding: 15px 35px 15px 35px;
        background: aliceblue;
    }
    .loginTitle{
        margin: 10px auto 10px auto;
        text-align: center;
        font-size: 40px;
    }
    .loginRemember{
        text-align: left;
        margin: 0px 0px 15px 0px;
    }
    
</style>

