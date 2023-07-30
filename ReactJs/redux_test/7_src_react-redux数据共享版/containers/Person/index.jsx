import React, { Component } from 'react'
import { nanoid } from 'nanoid';
// 引入 connect 用于连接 UI 组件与 redux
import {connect} from 'react-redux'
// 引入 action
import {addPersonAction} from '../../redux/actions/person'

class Person extends Component {
  addPerson = () => {
    const name = this.nameNode.value;
    const age = this.ageNode.value;
    const id = nanoid();
    const newPerson = {id, name, age};
    //console.log(newPerson);
    this.props.addPerson(newPerson);
    // 清空输入框
    this.nameNode.value = '';
    this.ageNode.value = '';
  }


  render() {
    return (
      <div>
        <h2>我是 Person 组件，上方 Count 组件的求和为：{this.props.he}</h2>
        <input ref={c => this.nameNode = c} type="text" placeholder='Input name'/> &nbsp;
        <input ref={c => this.ageNode = c} type="text" placeholder='Input age'/> &nbsp;
        <button onClick={this.addPerson}>Add</button>
        <ul>
          {
            this.props.persons.map((p)=>{
              return <li key={p.id}>{p.name} - {p.age}</li>
            })
          }
        </ul>
      </div>
    )
  }
}

export default connect(
  state => ({persons:state.rens, he:state.sum}), // 映射状态， // 这里的 state 是放在 store 里面的总的 state
  {
      addPerson: addPersonAction, //映射操作状态的方法
  }

)(Person);
