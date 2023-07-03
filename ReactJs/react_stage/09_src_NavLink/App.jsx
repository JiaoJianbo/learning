import React, { Component } from 'react'
import {NavLink, Routes, Route} from 'react-router-dom'
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
                {/* 自定义 Active 时的样式，默认就是 active, 低版本的写法 */}
                {/* <NavLink activeClassName="atguigu" className="list-group-item" to="/about">About</NavLink> */}

                {/* 高版本的写法 */}
                <NavLink className={({isActive}) => "list-group-item " + (isActive? "atguigu" : "")} to="/about">About</NavLink>
                <NavLink className="list-group-item" to="/home">Home</NavLink>
                {/* 标签体的值被绑定在了 props.children 上 */}
                <MyNavLink to="/about">About</MyNavLink>
                <MyNavLink to="/home">Home</MyNavLink>
              </div>
            </div>
            <div className="col-xs-6">
              <div className="panel">
                <div className="panel-body">
                  {/* 注册路由 */}
                  {/* 低版本的写法 */}
                  {/* <Route path='/about' element={About}/>
                  <Route path='/home' component={Home}/> */}

                  {/* 高版本的写法 */}
                  <Routes>
                    <Route path='/about' element={<About/>}/>
                    <Route path='/home' element={<Home/>}/>
                  </Routes>
                </div>
              </div>
            </div>
          </div>
      </div>
    )
  }
}
