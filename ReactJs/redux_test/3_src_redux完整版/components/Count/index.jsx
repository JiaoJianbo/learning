import React, { Component } from 'react'
// 引入 store 用于获取保存在 store 中的 state
import store from '../../redux/store'
// 引入 actionCreator, 专门用于创建 action 对象
import {createIncremantAction, createDecremantAction} from '../../redux/count_action'

export default class index extends Component {
  
  componentDidMount() {
    // 监测到 redux 中的状态变化。只要变化，就调用 render
    store.subscribe(() => {
      this.setState({}); // 通过 setState() 间接调用 render 方法
    });
  }

  increment = () => {
    const {value} = this.selectNumber;
    // value*1 自动转成数值，否则为字符串拼接
    // store.dispatch({type:'increment', data: value*1});
    store.dispatch(createIncremantAction(value*1));
  }

  decrement = () => {
    const {value} = this.selectNumber;
    // store.dispatch({type:'decrement', data: value*1});
    store.dispatch(createDecremantAction(value*1));
  }

  incrementIfOdd = () => {
    const {value} = this.selectNumber;
    const count = store.getState();
    if(count % 2 !== 0) {
      // store.dispatch({type:'increment', data: value*1});
      store.dispatch(createIncremantAction(value*1));
    }
  }

  incrementAsync = () => {
    const {value} = this.selectNumber;
    setTimeout(()=>{
      // store.dispatch({type:'increment', data: value*1});
      store.dispatch(createIncremantAction(value*1));
    }, 800);
  }


  render() {
    return (
      <div>
        <h2>当前求和为： {store.getState()}</h2>

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
