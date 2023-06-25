import React, { Component } from 'react'
// 安装 prop-types，npm i prop-types
import PropTypes from 'prop-types'
import './Item.css'

export default class Item extends Component {

  // 对接收的 props 进行类型、必要性限制
  static propTypes = {
    id: PropTypes.string.isRequired,
    name: PropTypes.string.isRequired,
    done: PropTypes.bool.isRequired,
    updateTodo: PropTypes.func.isRequired,
    deleteTodo: PropTypes.func.isRequired
  }

  state = {mouse: false};

  // 处理鼠标移入，移出。使用的地方直接传参调用了，那么实现要用高阶函数
  handlerMouse = (flag)=> {
    return () => {
      // console.info(flag);
      this.setState({mouse: flag});
    }
  }

  // 处理勾选。使用的地方直接传参调用了，那么实现要用高阶函数
  handleCheck = (id)=> {
    const {updateTodo} = this.props;
    return (event) => {
      // console.info('Item@', id, event.target.checked);
      updateTodo(id, event.target.checked);
    }
  }

  // 处理按钮删除。使用下面的箭头函数方式调用，这里就不需要高阶函数
  handleDelete = (id) => {
    // console.info('Item@', id);
    if(window.confirm("你确定删除吗？")){
      this.props.deleteTodo(id);
    }
  }


  render() {
    const {id,name,done} = this.props;

    // console.info('Item@', this.props);
    const {mouse} = this.state;
    return (
      <li className='list-group-item' onMouseLeave={this.handlerMouse(false)} onMouseEnter={this.handlerMouse(true)}>
        <label htmlFor={id}>
            <input type="checkbox" id={id} checked={done} onChange={this.handleCheck(id)}/>
            <span>{name}</span>
        </label>
        <button onClick={()=>{this.handleDelete(id)}} className='btn btn-danger' style={{display: mouse? "block" : "none"}}>删除</button>
      </li>
    )
  }
}