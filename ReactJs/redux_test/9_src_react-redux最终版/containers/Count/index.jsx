// 引入 connect 用于连接 UI 组件与 redux
import {connect} from 'react-redux'
// 引入 action
import {
    increment, 
    decrement, 
    incrementAsync
} from '../../redux/actions/count'
import React, { Component } from 'react'

// 定义 UI 组件
class Count extends Component {
  increment = () => {
    const {value} = this.selectNumber;
    this.props.increment(value*1);
  }

  decrement = () => {
    const {value} = this.selectNumber;
    this.props.decrement(value*1);
  }

  incrementIfOdd = () => {
    const {value} = this.selectNumber;
    if(this.props.count % 2 !== 0) {
      this.props.increment(value*1);
    }
  }

  incrementAsync = () => {
    const {value} = this.selectNumber;
    this.props.incrementAsync(value*1, 800);
  }


  render() {
    //console.log('CountUI@props:', this.props)
    return (
      <div>
        <h2>我是 Count 组件, 下方 Person 组件总人数为: {this.props.personCount}</h2>
        <h4>当前求和为：{this.props.count}</h4>

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


export default connect(
    state => ({
      count:state.sum, // 这里的 state 是放在 store 里面的总的 state
      personCount: state.persons.length
    }), 
    {
        increment,
        decrement,
        incrementAsync
    }

)(Count);
