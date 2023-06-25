import React, { Component } from 'react'
import Item from '../Item/Item'
// 安装 prop-types，npm i prop-types
import PropTypes from 'prop-types'
import './index.css'

export default class List extends Component {
  // 对接收的 props 进行类型、必要性限制
  static propTypes = {
    todos: PropTypes.array.isRequired,
    updateTodo: PropTypes.func.isRequired,
    deleteTodo: PropTypes.func.isRequired
  }


  render() {
    const {todos, updateTodo, deleteTodo} = this.props;
    // console.log('List@', todos);
    return (
      <ul className='list-group'>
        {
          todos.map((itm, index) => {
            return <Item key={itm.id} {...itm} updateTodo={updateTodo} deleteTodo={deleteTodo}/>
          })

        }
      </ul>
    )
  }
}