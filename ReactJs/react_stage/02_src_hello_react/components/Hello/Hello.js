import React, { Component } from 'react';
import './Hello.css'

// 样式模块化
// import hello from './hello.moudule.css'
// className='hello.titile'

export default class Hello extends Component {
    render () {
        return <h2 className='title'>Hello React!</h2>
    }
}