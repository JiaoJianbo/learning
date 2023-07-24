// 引入 Count UI 组件
// import CountUI from '../../components/Count'
// 引入 connect 用于连接 UI 组件与 redux
import {connect} from 'react-redux'
// 引入 action
import {
    createIncremantAction, 
    createDecremantAction, 
    createIncremantAsyncAction
} from '../../redux/count_action'

import React, { Component } from 'react'
// 引入 store 
// import store from '../../redux/store'

// 定义 UI 组件
class Count extends Component {
  
  // 用了 react-redux 不用再自己监测 state 改变，容器组件已经具备监测能力
  /* componentDidMount() {
    // 监测到 redux 中的状态变化。只要变化，就调用 render
    store.subscribe(() => {
      this.setState({}); // 通过 setState() 间接调用 render 方法
    });
  } */

  increment = () => {
    const {value} = this.selectNumber;
    this.props.add(value*1);
  }

  decrement = () => {
    const {value} = this.selectNumber;
    this.props.decrement(value*1);
  }

  incrementIfOdd = () => {
    const {value} = this.selectNumber;
    if(this.props.count % 2 !== 0) {
      this.props.add(value*1);
    }
  }

  incrementAsync = () => {
    const {value} = this.selectNumber;
    this.props.asyncAdd(value*1, 800);
  }


  render() {
    console.log('CountUI@props:', this.props)

    return (
      <div>
        <h2>当前求和为：{this.props.count}</h2>

        <select ref={c => this.selectNumber = c }>
            <option value="1">1</option>
            <option value="2">2</option>
            <option value="3">3</option>
        </select>&nbsp;
        <button onClick={this.increment}>+</button>&nbsp;
        <button onClick={this.decrement}>-</button>&nbsp;
        <button onClick={this.incrementIfOdd}>奇数再加</button>&nbsp;
        <button onClick={this.incrementAsync}>异步加</button>&nbsp;
      </div>
    )
  }
}


/* 
// 映射状态
const mapStateToProps = state => ({count:state});

// 映射操作状态的方法
const mapDispatchToProps = dispatch => ({
    // 通过 redux 执行加法
    add: value => dispatch(createIncremantAction(value)),
    decrement: value => dispatch(createDecremantAction(value)),
    asyncAdd: (value, ts) => dispatch(createIncremantAsyncAction(value, ts))
})
 */

export default connect(
    state => ({count:state}), 
    // mapDispatchToProps 的一般写法 （一个函数）
    /* dispatch => ({
        add: value => dispatch(createIncremantAction(value)),
        decrement: value => dispatch(createDecremantAction(value)),
        asyncAdd: (value, ts) => dispatch(createIncremantAsyncAction(value, ts))
    }) */
    
    // mapDispatchToProps 的简写形式 (一个对象)。react-redux 帮你去 dispatch
    {
        add: createIncremantAction,
        decrement: createDecremantAction,
        asyncAdd: createIncremantAsyncAction
    }

)(Count);
