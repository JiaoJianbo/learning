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


## Hooks

### useRoutes


### useParams
