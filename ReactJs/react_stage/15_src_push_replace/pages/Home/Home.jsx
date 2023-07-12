import React, { Component } from 'react'
import {Routes, Route, Navigate, NavLink} from 'react-router-dom'
import Message from './Message'
import News from './News'

export default class Home extends Component {
  render() {
    return (
      <div>
        <h2>Home的内容</h2>
        <div>
          <ul className="nav nav-tabs">
            {/* V6 以前要按 News 的写法，V6 下面两种写法都可以。注意：去看看 App 中一级路由注册的写法 */}
            <li>
              <NavLink replace className="list-group-item" to="/home/news">News</NavLink>
            </li>
            <li>
              <NavLink replace className="list-group-item" to="message">Message</NavLink>
            </li>
          </ul>
          {/* 注册路由 */}
          {/* V6 以前的写法 */}
          {/* <Switch>
            <Route path='/home/news' component={News}/>
            <Route path='/home/message' component={Message}/>
          </Switch> */}

          {/* V6 的写法, 不需要一级路由 /home/ 了,如果有子路由，后面要* */}
          <Routes>
            <Route path='news/*' element={<News/>}/>
            <Route path='message/*' element={<Message/>}/>

            <Route path='*' element={<Navigate to="news"/>}/>
          </Routes>
        </div>
      </div>
    )
  }
}
