import React, { Component } from 'react'
// 安装 nanoid，npm i nanoid
import { nanoid } from 'nanoid';
// 安装 prop-types，npm i prop-types
import PropTypes from 'prop-types'

import './Header.css'

export default class Header extends Component {

  // 对接收的 props 进行类型、必要性限制
  static propTypes = {
    addTodo: PropTypes.func.isRequired
  }


  handleKeyUp = (event) => {
    const {target, keyCode} = event;
    // 如果按下非回车，直接返回
    if(keyCode !== 13) return;
    console.log('Header@', target.value, keyCode);

    if (target.value.trim() === '') {
      alert('输入不能为空');
      return;
    }
    
    // npm i nanoid, 引入 nanoid 生成 uuid
    const todoObj = {id: nanoid(), name: target.value, done: false}

    // 调用父组件 addTodo 将 todoObj 传递给 App 组件， addTodo 是父组件 App 传给子组件 Header 的
    this.props.addTodo(todoObj);
    // 清空输入
    target.value = '';
  }

  render() {
    return (
      <div className='header'>
        <input type="text" onKeyUp={this.handleKeyUp} placeholder='请输入待办事项，按回车确认'/>
      </div>
    )
  }
}