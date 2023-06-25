import React, { Component } from 'react'
import './index.css'

export default class Footer extends Component {

  handleCheckAll = (event) => {
    this.props.checkAllTodo(event.target.checked);
  }

  handleClearAllDone = () => {
    this.props.clearAllDone();
  }

  render() {
    const {todos} = this.props;
    // 最基本写法
    // let doneCount = 0;
    // todos.forEach(todoObj => {
    //   if(todoObj.done) {
    //     doneCount += 1;
    //   }
    // });

    // const doneCount = todos.reduce((preVal, todoObj)=> {
    //   return preVal + (todoObj.done ? 1 : 0);
    // }, 0);
    // 上面简写
    const doneCount = todos.reduce((pre, todoObj) => pre + (todoObj.done? 1:0), 0);
    const total = todos.length;

    return (
      <div className='todo-footer'>
        <label htmlFor=""><input type="checkbox" onChange={this.handleCheckAll} checked = {(doneCount === total & total !== 0) ? true : false}/></label>
        <span><span>已完成 {doneCount}</span> / 全部 {total}</span>
        <button className='btn btn-danger' onClick={this.handleClearAllDone}>清除已完成任务</button>
      </div>
    )
  }
}
