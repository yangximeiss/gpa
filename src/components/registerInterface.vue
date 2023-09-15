<template>
    <div>
  <el-form :rules="rules" ref="loginForm" :model="loginForm" class="loginContainer">
     <h3 class="loginTitle" style="font-size: 25px; ">
       账 号 注 册
     </h3>
      <el-form-item label="用户名" prop="username">
          <el-input type="text" v-model="loginForm.username" placeholder="请输入用户名" >
          </el-input>
      </el-form-item>
      <el-form-item label="密码" prop="password">
          <el-input type="password" v-model="loginForm.password" placeholder="请输入密码" >
          </el-input>
      </el-form-item>
      <el-form-item label="确认密码" prop="checkpassword">
        <el-input type="password" v-model="loginForm.checkpassword" placeholder="请输入密码" >
        </el-input>
    </el-form-item>
    <el-form-item style=" text-align :center; margin: 40px auto;">
        <el-button type="primary" @click="submitForm">提 交</el-button>
        <el-button @click="resetForm">返 回</el-button>
    </el-form-item>
  </el-form>
    </div>
</template>

<script>
export default {
 
    data(){
        var validatepass = (rule, value, callback) =>{
            if (value === ''){
                callback(new Error ('请输入密码'));
            }else{
                if(this.loginForm.checkpassword !== ''){
                    this.$refs.loginForm.validateField('checkPass');
                }
                callback();
            }
        };
        var validatepass2 = (rule, value, callback)=> {
            if (value === ''){
                callback(new Error('请再次输入密码'));
            }else if (value !== this.loginForm.password){
                callback(new Error('两次输入密码不一致！'));
            }else{
                callback();
            }
        };

      return{
          captchaUrl: "",
          loginForm:{
              username:"",
              password:"",
              checkpassword:""

          },
          checked: true,
          rules:{
              username:[{required:true,message:"请输入用户名",trigger:"blur"},{ min: 0, max: 14, message: '长度在 0 到 14 个字符', trigger: 'blur' }
              ],
              password:[{required:true,validator: validatepass ,trigger:"blur"},{ min: 6, max: 8, message: '长度在 5 到 8 个字符', trigger: 'blur' }],
              checkpassword:[{required:true,validator: validatepass2,trigger:"blur"},{ min: 6, max: 8, message: '长度在 5 到 8 个字符', trigger: 'blur' }],
          }

      }
  },
    methods:{
      submitForm(){
        if(localStorage['username']===this.loginForm.username)
      {
        alert("用户名已存在");//如果用户名已存在则无法注册
      }
       else if(this.loginForm.username==='')
      {
        alert("用户名不能为空");
      }
      else {//将新用户信息存储到localStorage
        localStorage.setItem('username',this.loginForm.username);
        localStorage.setItem('password',this.loginForm.password);     
        alert("注册成功");
        this.$router.replace('/Login');
    }
      },


      resetForm(){
        this.$router.push('/Login');
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

