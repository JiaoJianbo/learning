import React, { Component } from 'react'

/* const users = [
  {id:'001', name:'Tom', age:18},
  {id:'002', name:'Jack', age:19},
  {id:'003', name:'Lord', age:20},
]; 
*/
const users = "Users Not Found";

export default class Parent extends Component {
  state = {hasError: false};

  // 当 Parent 组件出现错误(生命周期产生的错误)时，会触发该方法的调用，并携带错误信息
  static getDerivedStateFromError(error) {
    console.log('getDerivedStateFromError', error);
    // 在 render 之前触发，返回新的 state
    /* return {
      users: []
    } */

    return {
      hasError: true
    }
  }

  componentDidCatch(error, info) {
    // 统计页面错误，请求后台API，触发告警 ...
    console.log('componentDidCatch', error, info);
  }

  render() {
    return (
      <div style={{ backgroundColor: 'skyblue', padding: '10px' }}>
        <h3>我是父組件</h3>
        {
          this.state.hasError ? "当前网络不稳定，请稍候重试" : <Child/>
        }
      </div>
    )
  }
}

class Child extends Component {
  
  render() {
    return (
      <div style={{ backgroundColor: 'orange', padding: '10px', marginTop: '10px' }}>
        <h4>我是 Child 組件</h4>
        <ul>
          {
            users.map((val, inx) => {
              return <li key={val.id}>{`${val.name} - ${val.age}`}</li>;
            })
          }
        </ul>

      </div>
    )
  }
}
