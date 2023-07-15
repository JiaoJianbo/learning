import React, { Component } from 'react'
// withRouter 在 V6 版本中已经移除了
// import { withRouter } from 'react-router-dom';

export default class Header extends Component {
/* class Header extends Component {
  back = ()=>{
    this.props.history.goBack();
  }

  forward = ()=>{
    this.props.history.goForward();
  } */

  render() {
    console.log('Header@', this.props);
    return (
      <div className="page-header">
        <h2>React Router Demo</h2>
        <button onClick={this.back}>后退</button> &nbsp;
        <button onClick={this.forward}>前进</button>
      </div>
    )
  }
}

// export default withRouter(Header)
/*
1. withRouter 可以加工一般组件，让一般组件具备路由组件所特有的 API
2. withRouter 的返回值是一个新组件
*/