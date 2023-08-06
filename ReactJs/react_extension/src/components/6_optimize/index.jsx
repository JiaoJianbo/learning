import React, { Component, PureComponent } from 'react'

export default class Parent extends PureComponent {
  state = {carName:'BMW-X5'};

  changeCar = () => {
    /* 这种写法会导致 PureComponent 默认的 shouldComponentUpdate 失效
    const obj = this.state;
    obj.carName = 'Audi Q7';
    this.setState(obj);
     */
    this.setState({carName:'BMW-750'});
  }

/* 
  shouldComponentUpdate(nextProps, nextState, nextContext) {
    // console.log(nextProps, nextState, nextContext);
    // console.log(this.props, this.state, this.context);
    return nextState.carName !== this.state.carName;
  }
 */

  render() {
    console.log('Parent - render');
    const {carName} = this.state;
    return (
      <div style={{backgroundColor:'orange', padding:'10px'}}>
        <h3>我是父組件</h3>
        <span>我的車是 {carName} &nbsp;&nbsp;</span>
        <button onClick={this.changeCar}>点我换车</button>
        <Child/>
      </div>
    )
  }
}

class Child extends PureComponent {
/* 
  shouldComponentUpdate(nextProps, nextState) {
    return this.props.carName !== nextProps.carName;
  }
 */

  render() {
    console.log('Child - render');
    return (
      <div style={{backgroundColor:'white', padding:'10px', marginTop: '10px'}}>
        <h4>我是子組件</h4>
        {/* <span>父组件的車是 {this.props.carName}</span> */}
      </div>
    )
  }
}
