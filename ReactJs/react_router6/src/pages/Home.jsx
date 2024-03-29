import React from 'react'
import { NavLink, Outlet, useInRouterContext, useOutlet } from 'react-router-dom';

export default function Home() {
  console.log('useInRouterContext@Home', useInRouterContext());
  console.log('useOutlet@Home', useOutlet());
  
  return (
    <div>
      <h3>我是 Home 的内容</h3>
      <div>
        <ul className="nav nav-tabs">
          {/* V6 以前要按 News 的写法，V6 下面两种写法都可以。注意：去看看 App 中一级路由注册的写法 */}
          <li>
            <NavLink className="list-group-item" to="news">News</NavLink>
          </li>
          <li>
            <NavLink className="list-group-item" to="messages">Messages</NavLink>
          </li>
        </ul>
        {/* 指定路由组件呈现的位置 */}
        <Outlet/>
      </div>
    </div>
  )
}
