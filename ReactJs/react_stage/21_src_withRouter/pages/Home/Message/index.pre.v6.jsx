import React, { Component } from 'react'
import {Link, Routes, Route, useNavigate } from 'react-router-dom';
import Detail from './Detail/Detail';

export default class Message extends Component {
  state = {messageArr:[
    {id: "01", title: 'Message01'},
    {id: "02", title: 'Message02'},
    {id: "03", title: 'Message03'}
  ]};

  pushShow = (id, title) => {
    console.log('Message@', this.props);
    // ------ V6 以前的写法 -------
    // 携带 params 参数
    // this.props.history.push(`detail/${id}/${title}`);
    // 携带 search 参数
    // this.props.history.push(`detail?id=${id}&title=${title}`);
    // 携带 state 参数
    this.props.history.push('detail', {id, title});
  }


  replaceShow = (id, title) => {
    console.log('Message@', this.props);
    // ------ V6 以前的写法 -------
    // 携带 params 参数
    // this.props.history.replace(`detail/${id}/${title}`);
    // 携带 search 参数
    // this.props.history.replace(`detail?id=${id}&title=${title}`);
    // 携带 state 参数
    this.props.history.replace('detail', {id, title});
  }

  goBack = () => {
    // ------ V6 以前的写法 -------
    this.props.history.goBack();
  }

  goForward = () => {
    // ------ V6 以前的写法 -------
    this.props.history.goForward();
  }

  render() {
    return (
      <div>
        <ul>
          {
            this.state.messageArr.map(msgObj => {
              return(
                <li key={msgObj.id}>
                  {/* 向路由组件传递 params 参数 */}
                  <Link to={`detail/${msgObj.id}/${msgObj.title}`}>{msgObj.title}</Link>&nbsp;&nbsp;

                  {/* 向路由组件传递 search 参数 */}
                  <Link to={`detail?id=${msgObj.id}&title=${msgObj.title}`}>{msgObj.title}</Link>&nbsp;&nbsp;

                  {/* 向路由组件传递 state 参数 */}
                  <Link to={`detail`} state={{id: msgObj.id, title:msgObj.title}}>{msgObj.title}</Link>&nbsp;&nbsp;

                  <button onClick={()=> this.pushShow(msgObj.id, msgObj.title)}>push 查看</button> &nbsp;&nbsp;
                  <button onClick={()=> this.replaceShow(msgObj.id, msgObj.title)}>replace 查看</button>
                </li>
              );
            })
          }
        </ul>

        <div>
          <Routes>
            {/* 声明接收 params 参数 - V6 以前写法 */}
            {/* <Route path='/home/message/detail/:id/:title' conponent={Detail}/> */}
            {/* 声明接收 params 参数 - V6 写法 */}
            <Route path='detail/:id/:title' element={<Detail/>}/>

            {/* 声明接收 search 参数 - V6 写法 */}
            <Route path='detail' element={<Detail/>}/>

            {/* 声明接收 state 参数 - V6 写法 */}
            <Route path='detail' element={<Detail/>}/>

          </Routes>
        </div>

        <div>
          <button onClick={this.goBack}>后退</button> &nbsp;
          <button onClick={this.goForward}>前进</button>
        </div>
      </div>
      
    )
  }
}
