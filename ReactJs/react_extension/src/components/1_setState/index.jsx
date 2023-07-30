import React, { Component } from 'react'

export default class Demo extends Component {
  state = {count:0}

  add = ()=> {
    // 对象式的 setState
    /* const {count} = this.state;
    this.setState({count: count+1}, ()=>{
      // 这个回调是在 state 更新后，render() 调用完成后才开始执行
      console.log('newStateInCallback', this.state.count);
    });
    console.log('newStateAftSetSate', this.state.count); //setState 是异步的 
    */

    // 函数式的 setState
    this.setState((state, props)=>{
      // console.log(state, props);
      return {count: state.count + 1};
    },
    ()=>{
      // 这个回调是在 state 更新后，render() 调用完成后才开始执行
      console.log('newStateInCallback', this.state.count);
    });
  }

  render() {
    return (
      <div>
        <h2>当前求和为：{this.state.count}</h2>
        <button onClick={this.add}>点我+1</button>
      </div>
    )
  }
}
