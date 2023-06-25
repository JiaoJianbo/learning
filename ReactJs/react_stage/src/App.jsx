import axios from 'axios'
import React, { Component } from 'react'

export default class App extends Component {
  getStudentData = () => {
    // 没有使用代理前，直接访问 5000 端口
    // axios.get('http://localhost:5000/students').then(

    // package.json 中配置了代理后，访问 3000 端口  
    // axios.get('http://localhost:3000/students').then(

    // 使用 setupProxy.js 配置代理后, 加上 API 前缀 “/api5000”
    axios.get('http://localhost:3000/api5000/students').then(
      response => {
        console.log('Success', response.data);
      },
      error => {
        console.log('faild, ', error);
      }
    )
  }

  getCarData = () => {
    // http://localhost:5001/cars
    // 使用代理 /api5001
    axios.get('http://localhost:3000/api5001/cars').then(
      response => {
        console.log('Success', response.data);
      },
      error => {
        console.log('faild, ', error);
      }
    )
  }


  render() {
    return (
      <div>
        <button onClick={this.getStudentData}>点我获取学生数据</button> <br/> <br/>
        <button onClick={this.getCarData}>点我获取汽车数据</button> 
      </div>
    )
  }
  
}
