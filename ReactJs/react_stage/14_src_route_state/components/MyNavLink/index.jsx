import React, { Component } from 'react'
import {NavLink} from 'react-router-dom'

export default class MyNavLink extends Component {
  render() {
    // console.log(this.props);
    return (
    //   属性中的 children 值也就是标签体的内容
      <NavLink className="list-group-item" {...this.props} />
    )
  }
}
