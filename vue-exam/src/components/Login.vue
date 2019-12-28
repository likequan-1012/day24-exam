<template>
  <el-main>
    <h1>登录页面</h1>
    <el-form :model="ruleForm"  ref="ruleForm" label-width="200" :inline="true"  class="demo-ruleForm" >

      <el-form-item label="用户账号" prop="name" >
        <el-input v-model="ruleForm.name" ></el-input>
      </el-form-item>
      <br>
      <el-form-item label="用户密码" prop="password">
        <el-input type="password" v-model="ruleForm.password" autocomplete="off"></el-input>
      </el-form-item>
      <br>
      <el-form-item>
        <el-button type="primary" @click="onSubmit">登录</el-button>
        <el-button @click="zhuc">去注册</el-button>
      </el-form-item>
    </el-form>
  </el-main>
</template>
<script>
    const axios = require("axios");
    const Path = "http://localhost:10000/gateway/client/userApi/";
    export default {
        name: "Login",
        data() {
            return {
                ruleForm: {
                },
            }
        },
        methods: {
            onSubmit() {
                        axios({
                            url:Path+"loginApi",
                            method:"post",
                            data:this.ruleForm
                        }).then(res=>{
                            if (res.data.flag){
                                this.$message("登录成功")
                                sessionStorage.setItem("user",JSON.stringify(res.data.user))
                                this.$router.push("item")
                            }else{
                                this.$message(res.data.msg)
                            }

                        })
            },
            zhuc(){
                this.$router.push("/")
            }
        },
        created() {
        }
    }
</script>
<style scoped>

</style>

