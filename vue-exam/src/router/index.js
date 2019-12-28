import Vue from 'vue'
import Router from 'vue-router'
import HelloWorld from '@/components/HelloWorld'
import Zhuc from '@/components/Zhuc'
import Login from '@/components/Login'
import Item from '@/components/Item'
import Car from '@/components/Car'

Vue.use(Router)

export default new Router({
  routes: [
    {
      path: '/aaa',
      name: 'HelloWorld',
      component: HelloWorld
    },
    {
      path: '/',
      name: 'Zhuc',
      component: Zhuc
    },
    {
      path: '/login',
      name: 'Login',
      component: Login
    },
    {
      path: '/item',
      name: 'Item',
      component: Item
    },
    {
      path: '/car/:id',
      name: 'Car',
      component: Car
    }
  ]
})
