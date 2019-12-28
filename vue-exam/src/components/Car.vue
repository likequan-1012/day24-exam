<template>
  <el-main>
    <div style="background-color: black;height:35px;margin-bottom: 20px"><p
      style="color: white;float:left;margin-top: 1mm; margin-left: 10px">电商网站首页</p>
      <el-link href="Car.vue" style="color: white; float: right; margin-top: 2mm;margin-right: 10px;">
        <el-badge :value='num' class="item">购物车</el-badge>
      </el-link>
      <p style="color: white; float: right; margin-top: 1mm;margin-right: 10px;">{{user.name}}</p>
      <el-link href="Login.vue" style="color: white; float: right; margin-top: 2mm;margin-right: 10px;">退出登录</el-link>
    </div>
    <el-card shadow="always">
      <p style="float:left;margin-top: 1px">购物清单</p>
      <el-table
        :data="tableData"
        stripe
        style="width: 100%">
        <el-table-column
          label="商品信息">
          <template slot-scope="scope">
            <img :src="scope.row.image" class="image" style="height: 40px;">
            {{scope.row.name}}
            {{scope.row.desc}}
          </template>
        </el-table-column>
        <el-table-column
          label="单价"
          width="180">
          <template slot-scope="scope">
            {{scope.row.price}}
          </template>
        </el-table-column>
        <el-table-column
          label="数量"
          prop="num"
          width="180">
          <template slot-scope="scope">
            <el-input-number  v-model="scope.row.num" size="mini" @change="handleChange(scope.row)" ></el-input-number>
          </template>
        </el-table-column>
        <el-table-column
          label="小计"
          width="180">
          <template slot-scope="scope">
            ￥{{scope.row.subtotal}}
          </template>
        </el-table-column>
        <el-table-column
          fixed="right"
          label="操作"
          width="100">
          <template slot-scope="scope">
            <el-button @click="del(scope.row.id)" type="text" size="small">删除</el-button>
          </template>
        </el-table-column>
      </el-table>
      <div style="float: left">
        使用优惠券
        <el-input
          placeholder="暂无优惠券"
          size="mini"
          style="width: 150px;"
          :disabled="true">
        </el-input>
        <el-button size="mini" type="primary">确定</el-button>
      </div>
      <div style="float: right">
        <template >
          <span>共计：<font style="color:red">{{num}}</font>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;应付金额：<font
            style="color:red">{{totalPrice}}</font>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</span>
          <el-button size="mini" type="primary" @click="bill">现在结算</el-button>
        </template>
      </div>
    </el-card>

  </el-main>
</template>

<script>
    const axios = require("axios");
    const Path = "http://localhost:10000/gateway/client/userApi/";
    export default {
        name: "Car",
        data() {
            return {
                user: '',
                tableData: [],
                carList: [],
                totalPrice:0,
                car: [],
                num:0,
                subtotal:0,
                productList:[]
            }
        },
        methods: {
            handleChange() {
                this.totalPrice=0;
                this.productList=[]

                this.num=0;
                this.tableData.forEach(entity=>{
                    if(entity.num>0){
                        entity.subtotal=new Number(entity.num*entity.price);
                        this.totalPrice=new Number(this.totalPrice+entity.subtotal)
                        this.num=new Number(this.num+entity.num)
                        this.productList[this.productList.length]=entity
                    }else{
                        entity.subtotal=0
                    }
                })

            },
            bill(){
                let order = {
                    total_Price : this.totalPrice,
                    num : this.num,
                }
                this.$message("当前商品数量："+order.num+"需要支付￥："+order.total_Price)
            },
            del(id) {
               let uid = this.user.id
                axios({
                    url:Path+"delApi",
                    method: "get",
                    params :{id:id,uid:uid}
                }).then(res=>{
                    this.$message("删除成功！")
                    this.getList()
                })
            },
            getList(){
                let user = JSON.parse(sessionStorage.getItem("user"));
                this.user = user
                const uid = this.user.id;
                axios({
                    url: Path + "carApi",
                    method: "get",
                    params: {uid: uid}
                }).then(res => {
                    this.tableData = res.data.list;
                    this.carList = res.data.cars
                    this.tableData.forEach(a=>{
                        this.num+=a.num;
                        this.totalPrice+=a.subtotal
                    })

                })
            }
        },
        created() {
           this.getList()
        }
    }
</script>

<style scoped>

</style>
