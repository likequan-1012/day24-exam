<template>
<el-main>
  <h1>注册页面</h1>
  <el-form :model="ruleForm" status-icon :rules="rules" ref="ruleForm" label-width="200" :inline="true"  class="demo-ruleForm" >

    <el-form-item label="用户账号" prop="name" >
      <el-input v-model="ruleForm.name" ></el-input>
    </el-form-item>
    <br>
    <el-form-item label="用户密码" prop="password">
      <el-input type="password" v-model="ruleForm.password" autocomplete="off"></el-input>
    </el-form-item>
    <br>
    <el-form-item label="确认密码" prop="checkPass">
      <el-input type="password" v-model="ruleForm.checkPass" autocomplete="off"></el-input>
    </el-form-item>
    <br>
    <el-form-item>
      <el-button type="primary" @click="submitForm('ruleForm')">提交</el-button>
      <el-button @click="login">已有账号</el-button>
    </el-form-item>
  </el-form>
</el-main>
</template>
  <script>
      const axios = require("axios");
      const Path = "http://localhost:10000/gateway/client/userApi/";
      export default {
          name: "Zhuc",
          data() {
              var validatePass = (rule, value, callback) => {
                  if (value === '') {
                      callback(new Error('请输入密码'));
                  } else {
                      if (this.ruleForm.checkPass !== '') {
                          this.$refs.ruleForm.validateField('checkPass');
                      }
                      callback();
                  }
              };
              var validatePass2 = (rule, value, callback) => {
                  if (value === '') {
                      callback(new Error('请再次输入密码'));
                  } else if (value !== this.ruleForm.password) {
                      callback(new Error('两次输入密码不一致!'));
                  } else {
                      callback();
                  }
              };
              return {
                  ruleForm: {
                      checkPass: '',
                  },
                  rules: {
                      password: [
                          { validator: validatePass, trigger: 'blur' }
                      ],
                      checkPass: [
                          { validator: validatePass2, trigger: 'blur' }
                      ],
                  }
              };
          },
          methods: {
              submitForm(formName) {
                  this.$refs[formName].validate((valid) => {
                      if (valid) {

                          axios({
                              url:Path+"zhucApi",
                              method:"post",
                              data:this.ruleForm
                          }).then(res=>{
                              this.$message("注册成功，请登录")
                              this.$router.push("login")
                          })


                      } else {
                          console.log('error submit!!');
                          return false;
                      }
                  });
              },
              resetForm(formName) {
                  this.$refs[formName].resetFields();
              },
              login(){
                  this.$router.push("login")
              }
          }
      }
  </script>
<style scoped>

</style>
