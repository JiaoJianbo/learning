import React, { Component } from 'react'
import Count from './containers/Count'
// 引入 store 
import store from './redux/store'

export default class App extends Component {
  render() {
    return (
      <div>
        {/* 给容器组件传入 store */}
        <Count store={store}/>
      </div>
    )
  }
}

