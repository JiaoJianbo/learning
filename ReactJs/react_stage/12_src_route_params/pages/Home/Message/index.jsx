import React, { Component } from 'react'
import {Link, Routes, Route} from 'react-router-dom';
import Detail from './Detail/Detail';

export default class Message extends Component {
  state = {messageArr:[
    {id: "01", title: 'Message01'},
    {id: "02", title: 'Message02'},
    {id: "03", title: 'Message03'}
  ]};

  render() {
    return (
      <div>
        <ul>
          {
            this.state.messageArr.map(msgObj => {
              return(
                <li key={msgObj.id}>
                  {/* 向路由组件传递 params 参数 */}
                  {/* V6 以前写法 */}
                  {/* <Link to={`/home/message/detail/${msgObj.id}/${msgObj.title}`}>{msgObj.title}</Link> */}

                  {/* V6 写法 */}
                  <Link to={`detail/${msgObj.id}/${msgObj.title}`}>{msgObj.title}</Link>
                </li>
              );
            })
          }
        </ul>

        <div>
          {/* 声明接收 params 参数 */}
          <Routes>
            {/* V6 以前写法 */}
            {/* <Route path='/home/message/detail/:id/:title' conponent={Detail}/> */}

            {/* V6 写法 */}
            <Route path='detail/:id/:title' element={<Detail/>}/>
          </Routes>
        </div>
      </div>
      
    )
  }
}
