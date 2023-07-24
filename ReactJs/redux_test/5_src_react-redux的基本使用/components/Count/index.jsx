import React, { Component } from 'react'
// 引入 store 
import store from '../../redux/store'

export default class index extends Component {
  
  componentDidMount() {
    // 监测到 redux 中的状态变化。只要变化，就调用 render
    store.subscribe(() => {
      this.setState({}); // 通过 setState() 间接调用 render 方法
    });
  }

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
