import React, { Component } from 'react'
import {Routes, Route, Navigate} from 'react-router-dom'
import Header from './components/Header' // 一般组件
import About from './pages/About/About' // 路由组件
import Home from './pages/Home/Home' // 路由组件
import MyNavLink from './components/MyNavLink'

export default class App extends Component {

  render() {
    return (
      <div>
          
          <div className="row">
            <div className="col-xs-offset-2 col-xs-8">
              <Header />
            </div>
          </div>

          <div className="row">
            <div className="col-xs-offset-2 col-xs-2">
              <div className="list-group">
                {/* 标签体的值被绑定在了 props.children 上 */}
                <MyNavLink to="/about">About</MyNavLink>
                <MyNavLink to="/home">Home</MyNavLink>
              </div>
            </div>
            <div className="col-xs-8">
              <div className="panel">
                <div className="panel-body">
                  {/* 注册路由 */}
                  {/* 高版本的写法 */}
                  <Routes>
                    {/* exact=true 表示精确匹配，低版本默认模糊匹配，但是高版本（v6）默认就是精确匹配 */}
                    <Route path='/about/*' element={<About/>}/>
                    <Route path='/home/*' element={<Home/>}/>

                    {/* 低版本用重定向 */}
                    {/* <Redirect to="/home" /> */}
                    {/* 高版本的重定向写法 */}
                    <Route path='*' element={<Navigate to="/about"/>}/>
                  </Routes>
                </div>
              </div>
            </div>
          </div>
      </div>
    )
  }
}
