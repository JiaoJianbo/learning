import React, { Component } from 'react'
import Header from './components/Header/Header'
import List from './components/List'
import Footer from './components/Footer'

import './App.css'

export default class App extends Component {
  // state 在哪里，操作 state 的方法就在哪里

  state = {todos: [
    {id: '001', name: '吃饭', done: true}, 
    {id: '002', name: '睡觉', done: true}, 
    {id: '003', name: '敲代码', done: false}
  ]};

  addTodo = (todoObj)=> {
    console.log('App@', todoObj);
    const {todos} = this.state;
    this.setState({todos: [todoObj, ...todos]});
  }

  // 这个方法一路透传 App --> List --> Item, 最后由 Item 调用
  updateTodo = (id, done) => {
    // console.log('App@', id, done);
    const {todos} = this.state;

    const newTodos = todos.map((todoObj) => {
      if (todoObj.id === id) {
        // 用新的 done 覆盖原来 todoObj 里面的值
        // return {...todoObj, done:done}
        // 上面简写为
        return {...todoObj, done}
      } else {
        return todoObj;
      }
    });

    this.setState({todos: newTodos});
  }

  // 这个方法一路透传 App --> List --> Item, 最后由 Item 调用
  deleteTodo = (id) => {
    const {todos} = this.state;
    const newTodos = todos.filter(todoObj => {
      return todoObj.id !== id;
    });

    this.setState({todos: newTodos});
  }

  checkAllTodo = (done) => {
    const {todos} = this.state;
    const newTodos = todos.map((todoObj) => {
      return {...todoObj, done};
    });

    this.setState({todos: newTodos});
  }

  clearAllDone = () => {
    const {todos} = this.state;
    const newTodos = todos.filter(todoObj => {
      return !todoObj.done;
    });

    this.setState({todos: newTodos});
  }

  render() {
    const {todos} = this.state;
    return (
      <div className='container'>
        <Header addTodo={this.addTodo}/>
        <List todos={todos} updateTodo={this.updateTodo} deleteTodo={this.deleteTodo}/>
        <Footer todos={todos} checkAllTodo={this.checkAllTodo} clearAllDone={this.clearAllDone}/>
      </div>
    )
  }
}
