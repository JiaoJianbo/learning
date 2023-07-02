import React, { Component } from 'react'
import About from './components/About'
import Home from './components/Home/Home'
import {Link, Routes, Route} from 'react-router-dom'

export default class App extends Component {

  render() {
    return (
      <div>
          <div className="row">
            <div className="col-xs-offset-2 col-xs-8">
              <div className="page-header"><h2>React Router Demo</h2></div>
            </div>
          </div>

          <div className="row">
            <div className="col-xs-offset-2 col-xs-2">
              <div className="list-group">
                {/* 原生 html 中用 <a> 跳转不同链接 */}
                {/* <a href="" className="list-group-item">About</a>
                <a href="" className="list-group-item">Home</a> */}

                {/* React 中用路由链接实现切换组件 --编写路由链接 */}
                  <Link className="list-group-item" to="/about">About</Link>
                  <Link className="list-group-item" to="/home">Home</Link>
 
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
