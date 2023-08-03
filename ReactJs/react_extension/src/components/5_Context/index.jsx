import React, { Component } from 'react'

//创建 Context 对象
const MyContext = React.createContext();
const {Provider, Consumer} = MyContext;
export default class A extends Component {
  state = {username: 'Tom', age: 18}

  render() {
    const {username, age} = this.state;
    return (
      <div style={style_parent}>
        <h2>我是 A 组件</h2>
        <h5>我的用户名是： {username}</h5>
        <Provider value = {{username, age}}>
          <B/>
        </Provider>
      </div>
    )
  }
}


class B extends Component {
  render() {
    return (
      <div style={style_child}>
        <h2>我是 B 组件</h2>
        <h5>我不用 A 里面的数据</h5>
        <C/>
      </div>
    )
  }
}


class C extends Component {
  static contextType = MyContext; // 声明接收 context

  render() {
    console.log('C', this.context);
    return (
      <div style={style_grand}>
        <h2>我是 C 组件</h2>
        <h5>我从 A 组件接收到的用户名是： {this.context.username}, 年龄： {this.context.age}</h5>
        <D />
      </div>
    )
  }
}

function D() {
  return (
    <div style={{background:'#FFF'}}>
      <h2>我是 D 组件</h2>
      <Consumer>
        {
          value => {
            console.log('D', value);
            return `我从 A 组件接收到的用户名是： ${value.username}, 年龄： ${value.age}`;
          }
        }
      </Consumer>
    </div>
  );
}


const style_parent = {
  width: '800px',
  margin: 'auto',
  background: 'orange',
  padding: '10px',
  border: '1px solid'
}


const style_child = {
  width: '100%',
  background: '#abc123',
  padding: '10px',
  border: '1px solid'
}


const style_grand = {
  width: '100%',
  background: 'pink',
  padding: '10px',
  border: '1px solid'
}
