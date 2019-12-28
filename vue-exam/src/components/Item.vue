<template>
    <el-main>
<div style="background-color: black;height:35px;margin-bottom: 20px" ><p style="color: white;float:left;margin-top: 1mm; margin-left: 10px">电商网站首页</p>
  <el-link @click="car" style="color: white; float: right; margin-top: 2mm;margin-right: 10px;" >购物车</el-link>
  <p  style="color: white; float: right; margin-top: 1mm;margin-right: 10px;" >{{user.name}}</p>
  <el-link href="Login.vue" style="color: white; float: right; margin-top: 2mm;margin-right: 10px;">退出登录</el-link>
  </div>

      <el-card shadow="always" >
        <div style="float: left">
      <el-form :inline="true" :model="queryForm" class="demo-form-inline" style="margin-left: 1px">

            <el-form-item label="品牌:">
            <el-radio-group  size="small" fill="red" v-model="queryForm.brand" @change="onSubmit">
            <el-radio-button label="Apple" >Apple</el-radio-button>
            <el-radio-button label="Beats">Beats</el-radio-button>
            <el-radio-button label="Sonos">Sonos</el-radio-button>
            <el-radio-button label="B&Q">B&Q </el-radio-button>
            <el-radio-button label="Bose">Bose</el-radio-button>
          </el-radio-group>
        </el-form-item><br>

          <el-form-item label="颜色:">
          <el-radio-group v-model="queryForm.color" @change="onSubmit" size="small" fill="red">
          <el-radio-button label="白色">白色</el-radio-button>
          <el-radio-button label="红色">红色</el-radio-button>
          <el-radio-button label="金色">金色</el-radio-button>
          <el-radio-button label="蓝色">蓝色</el-radio-button>
        </el-radio-group>
          </el-form-item><br>




          <el-form-item label="排序:">
          <el-radio-group v-model="queryForm.status" @change="onSubmit" size="small" style="margin-left: 0;margin-right: 50px;" fill="red" >
          <el-radio-button label="0" >默认</el-radio-button>
          <el-radio-button label="sales">销量</el-radio-button>
          <el-radio-button label="price">价格</el-radio-button>
        </el-radio-group>
          </el-form-item>

      </el-form>
        </div>
      </el-card>

      <div style="margin-top: 20px">
      <el-row style="margin-left: 200px;">
        <el-col :span="5" v-for="good in goodList" style="margin-left: 20px;">
          <el-card :body-style="{ padding: '10px' }" >
              <img src="good.image" :src="good.image" class="image" >
            <div style="margin-left: 0px;">
              <span>
                {{good.name}}<br>
                {{good.color}}<br>
                销量{{good.sales}}<br>
                <p style="color: red">￥{{good.price}}</p><br>
              </span>
              <div class="bottom clearfix">
              </div>
            </div>
          </el-card>
        </el-col>
      </el-row>
      </div>
      <el-pagination
        background
        layout="prev, pager, next"
        :page-count="pageCount"
        @current-change="gotoPage">
      </el-pagination>
    </el-main>
</template>

<script>
  const axios = require("axios");
  const Path = "http://localhost:10000/gateway/client/userApi/";
    export default {
        name: "Item",
        data() {
            return {
                goodList:[],
                user:{},
                radio3:"",
                brand:"",
                pageCount:0,
                queryForm:{
                    pageNo:1,
                    pageSize:4
                }
            };
        },
        methods:{
            getList(){
                axios({
                    url:Path+"listApi",
                    method:"post",
                    data:this.queryForm
                }).then(res=>{
                    this.goodList=res.data.list
                    this.pageCount=res.data.pages
                })
            },
            onSubmit(){
                if (this.queryForm.status=="0"){
                    this.$router.go(0);
                    return;
                }
                this.getList()
            },
            gotoPage(pageNo){
                this.queryForm.pageNo=pageNo
                this.getList()
            },
            car(){
                const uid = this.user.id;
                this.$router.push("/car/"+uid)

            }
        },
        created() {
            this.getList();
             let  user = JSON.parse(sessionStorage.getItem("user"));
             this.user=user
        }
    }
</script>
head{

}
<style scoped>
</style>
