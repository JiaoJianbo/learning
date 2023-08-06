import React, { Component } from 'react'

export default class Parent extends Component {

  render() {
    return (
      <div style={{ backgroundColor: 'skyblue', padding: '10px' }}>
        <h3>我是父組件</h3>
        {/* <A>
          Hello! <B name={'A.name'}/>
        </A> */}

        <A render={(name) => <B name={name}/>}/>
      </div>
    )
  }
}

class A extends Component {
  state = {sname: 'Tom'}
  
  render() {
    console.log(this.props);
    return (
      <div style={{ backgroundColor: 'red', padding: '10px', marginTop: '10px' }}>
        <h4>我是A組件</h4>
        {/* <span>{this.props.children}</span> */}
        {this.props.render(this.state.sname)}
      </div>
    )
  }
}

class B extends Component {
  render() {
    return (
      <div style={{ backgroundColor: 'white', padding: '10px', marginTop: '10px' }}>
        <h4>我是B組件, {this.props.name}</h4>
      </div>
    )
  }
}
