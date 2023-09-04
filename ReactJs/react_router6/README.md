# React Router 6 快速上手
  2021.11 成为默认版本
  安装 `npm i react-router-dom`

## 概述
1. React Router 以三个不同的包发布到 npm 上，它们分别是：
  - react-router 路由的核心库，提供了很多的组件、钩子
  - react-router-dom 包含了 react-router 的所有内容，并添加了一些专门用于 DOM 的组件，例如 `<BrowserRouter>` 等
  - react-router-native 包含了 react-router 的所有内容，并添加了一些专门用于 ReactNative 的 API，例如 `<NativeRouter>` 等
2. 与 React Router 5.x 比较，改变了什么
  - 内置组件的变化，移除了 `<Switch>`, 新增 `<Routes>` 等
  - 语法变化，`component={About}` 变为 `element={<About/>}` 等
  - 新增了多个 hook，`useParams`, `useNavigate`, `useMatch` 等
  - 官方明确推荐函数式组件了


## Component

### 1. <BrowserRouter>

### 2. <HashRouter>
1. 说明：作用与 `<BrowserRouter>` 一样，但 `<HashRouter>` 修改的是地址栏的 hash 值
2. 备注：6.x 版本中 `<BrowserRouter>`，`<HashRouter>` 用法与 5.x 中一样

### 3. <Routes> 与 <Route>
1. V6 版本中移除了先前的 `<Switch>`，引入了新的替代者 `<Routes>`
2. `<Routes>` 与 `<Route>` 要配合使用，且必须用`<Routes>` 包裹 `<Route>`
3. `<Route>` 相当于一个 if 语句，如果其路径与当前 URL 匹配，则呈现其对应的组件
4. `<Route caseSensitive>` 属性用于指定匹配时是否区分大小写（默认为 false）
5. 当 URL 发生变化时，`<Routes>` 会查看其所有子 `<Route>` 元素以找到最佳匹配并呈现
6. `<Route>` 也可以嵌套使用，且可以配合 `useRoutes()` 配置“路由表”，但是要通过 `<Outlet>` 来渲染子路由
7. 示例代码
```javascript
<Routes>
  {/* path 属性用于定义路径，element 属性用于定义当前路径所对应的组件 */}
  <Route path='/login' element={<Login />}></Route>

  {/* 用于定义嵌套路由，home 是一级路由，对应路径是 /home */}
  <Route path='/home' element={<Home />}>
    {/* test1 和 test2 是二级路由， 对应路径分别是 /home/test1, /home/test2 */}
    <Route path='/test1' element={<Test1 />}></Route>
    <Route path='/test2' element={<Test2 />}></Route>
  </Route>

  {/* Route 也可以不写 element 属性，这时就是用于展示嵌套路由，路径是 /user/test3 */}
  <Route path='/user'>
    <Route path='/test3' element={<Test3 />}></Route>
  </Route>
</Routes>
```

### 4. <Link>
1. 作用：修改 URL，且不发送网络请求（路由链接）
2. 注意：外侧需要用 `<BrowserRouter>`或`<HashRouter>` 包裹
3. 示例代码
```javascript
import { Link } from 'react-router-dom'

function Test() {
  return(
    <div>
      <Link to='/test'>按钮</Link>
    </div>
  );
}
```

### 5. <NavLink>
1. 作用：与 `<Link>` 组件类似，且可以实现导航的“高亮”效果
2. 示例代码
```javascript
// NavLink 默认的类名就是 active，下面是自定义的 class

<NavLink className={({isActive}) => isActive ? "list-group-item my-active" : "list-group-item"} to="/about">About</NavLink>

/*
 默认情况下，当 Home 的子组件匹配成功，Home 的导航也会高亮
 但是当 NavLink 加上 end 属性后，若 Home 的子组件匹配成功，Home 的导航不会有高亮效果
*/
<NavLink to="/home" end>Home</NavLink>
```

### 6. <Navigate>
1. 作用： 只要组件`<Navigate>`被渲染，就会修改路径，切换视图
2. `replace` 属性用于控制跳转模式（push 或者 replace，默认是 push）
3. 示例代码
```javascript
import React, {useState} from 'react'
import { Navigate } from 'react-router-dom';

export default function Home() {
  const [sum, setSum] = useState(1);
  return (
    <div><h3>我是 Home 的内容</h3>
      {/* 根据 sum 值决定是否切换视图 */}
      {sum === 2 ? <Navigate to='/about' replace={true}/> : <h5>当前 sum 值为： {sum}</h5>}
      <button onClick={()=> setSum(2)}>设置 sum 值为 2</button>
    </div>
  )
}
```

### 7. <Outlet>
1. 当 `<Route>` 产生嵌套时，渲染其对应的后续子路由
2. 示例代码
```javascript
const element = useRoutes([{
    path: '/about',
    element: <About/>
  },
  {
    path: '/home',
    element: <Home/>,
    children: [
      {
        path: 'news',
        element: <News/>
      },
      {
        path: 'messages',
        element: <Messages/>
      }
    ]
  }
]);


// Home.js
import React from 'react'
import { NavLink, Outlet } from 'react-router-dom';

export default function Home() {
  return (
    <div>
      <h3>我是 Home 的内容</h3>
      <div>
        <ul className="nav nav-tabs">
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
```

## Hooks

### useRoutes


### useParams


### useMatch


### useSearchParams


### useLocation


### useNavigate


### useInRouterContext()
作用：如果组件在 `<Router>` 的上下文中呈现，则 `useInRouterContext` 钩子返回 true, 否则返回 false.

### useNavigationType()
作用：返回当前的导航类型（用户是如何来到当前页面的）
返回值：`POP`, `PUSH`, `REPLACE`
备注：`POP` 是指在浏览器中直接打开了这个路由组件（刷新页面）

### useOutlet()
作用：用来呈现当前组件中要渲染的嵌套路由
示例代码
```javascript
const result = useOutlet();
// 如果嵌套路由没有挂载，则 result 为null
// 如果嵌套路由已经挂载，则展示嵌套的路由对象
console.log(result);
```

### useResolvedPath()
作用：给定一个 URL 值，解析其中的 path, search, hash 值
