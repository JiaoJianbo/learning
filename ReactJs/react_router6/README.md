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

