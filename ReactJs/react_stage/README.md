# 1. 使用 create-react-app 创建 React 应用

## 1.1 react 脚手架
1. xxx脚手架，用来帮助程序员快速构建一个基于 xxx 库的模板项目
  - 包含了所有需要的配置（语法检查，jsx 编译，devServer）
  - 下载好了所有相关的依赖
  - 可以直接运行一个简单效果

2. React 提供了一个用于创建 react 项目的脚手架库 create-react-app

3. 项目的整体技术架构为 react webpack es6 eslint

4. 使用脚手架开发项目的特点：模块化，组件化，工程化


## 1.2 创建项目并启动

1. 全局安装，`npm install -g crate-react-app`，安装指定版本，`npm i -g create-react-app@4.0.3`

2. 切换到想创建项目的目录，输入命令 `create-react-app hello-react`

3. 进入项目文件夹，`cd hello-react`

4. 启动项目，`npm start`


## 1.3 功能界面的组件化编码流程

1. 拆分组件：拆分界面，抽取组件
2. 实现静态组件：使组件实现静态页面效果
3. 实现动态组件
  - 动态显示初始化组件
    - 数据类型
    - 数据名称
    - 保存在哪个组件
  - 交互（从绑定事件监听开始）
  

## TodoList 案例相关知识点

1. 拆分组件、实现静态组件，注意 className, style 的写法
2. 动态初始化列表，如何确定将数据放在哪个组件的 state 中
  - 某个组件使用，放在自身的 state 中
  - 某些组件使用，放在他们共同的父组件 state 中（官方称此操作为：状态提升）
3. 关于父子之间通信
  - 父组件给子组件传递数据，通过 props 传递
  - 子组件给父组件传递数据，通过 props 传递，并要求父组件提前给子组件传递一个函数
4. 注意 defaultChecked 和 checked 的区别，类似的还有 defaultValue 和 value
5. state 在哪，操作 state 的方法就放在哪里


# React Ajax

## 前置说明

1. React 本身只关注于界面，并不包含发送 Ajax 请求的代码
2. 前端应用需要通过 Ajax 请求与后台进行交互（json 数据）
3. React 中需要集成第三方 Ajax 库（或自己封装）

## 常用的 Ajax 请求库

1. jQuery，比较重，如果需要另外引入，不建议使用
2. Axios, 轻量级，建议使用
  - 封装 XmlHttpRequest 对象的 Ajax
  - promise 风格
  - 可以用在浏览器和 node 服务器端

## React 脚手架配置代理总结

### 方法一
在 package.json 中追加如下配置：
```json
"proxy": "http://localhost:5000"
```
说明：
  - 优点：配置简单，前端请求资源时可以不加任何前缀。
  - 缺点：不能配置多个代理。
  - 工作方式：上述方式配置代理，当请求了3000不存在的资源时，那么该请求会转发给5000（优先匹配前端资源）

### 方法二
1. 第一步：创建代理配置文件
```
在src下创建配置文件：src/setupProxy.js
```

2. 编写setupProxy.js配置具体代理规则：
```javascript
const {createProxyMiddleware} = require('http-proxy-middleware')

module.exports = function(app) {
    app.use(
        createProxyMiddleware('/api5000', {  // 遇见带 /api5000 前缀的请求，就会触发该代理配置
            target: 'http://localhost:5000', // 请求转发给谁
            changeOrigin: true,              // 控制服务端收到的 request header 中 Host 的值，默认 false。
            /*
              changeOrigin 设置为 true 时，服务器收到的请求头中的host为：localhost:5000
              changeOrigin 设置为 false 时，服务器收到的请求头中的host为：localhost:3000
              changeOrigin 默认值为 false，一般设为true
            */
            pathRewrite: {'^/api5000': ''}   // 重写请求路径。这里指的是把请求中 /api5000 替换为空串。必须配置
        }),

        createProxyMiddleware('/api5001', {
            target: 'http://localhost:5001',
            changeOrigin: true,
            pathRewrite: {'^/api5001': ''}
        })

    );
}

/* ------- 以下是低版本的写法，而且浏览器控制台报 VM156:5 crbug/1173575, non-JS module files deprecated. -------- */

// const proxy = require('http-proxy-middleware')

// module.exports = function(app) {
//     app.use(
//         proxy('/api5000', {
//             target: 'http://localhost:5000',
//             changeOrigin: true,
//             pathRewrite: {'^/api5000': ''}
//         }),

//         proxy('/api5001', {
//             target: 'http://localhost:5001',
//             changeOrigin: true,
//             pathRewrite: {'^/api5001': ''}
//         })

//     );
// }
```
说明：
  - 优点：可以配置多个代理，可以灵活的控制请求是否走代理。
  - 缺点：配置繁琐，前端请求资源时必须加前缀。


## 消息发布订阅机制
1. 工具库 PubSubJS

2. 下载：npm install pubsub-js --save

3. 使用：
  - import PubSub from 'pubsub-js'
  - PubSub.subscribe('delete', function(data)); // 订阅
  - PubSub.publish('delete', data); // 发布消息


## 扩展：Fetch
1. 文档
  - <a href='https://www.jianshu.com/p/THLARe?utm_campaign=maleskine&utm_content=note&utm_medium=seo_notes&utm_source=recommendation' target='_blank'>传统 Ajax 已死，Fetch 永生</a>

2. 特点
  - fetch 是原生函数，不再使用 XmlHttpRequest 对象提交 Ajax 请求
  - 老版本浏览器可能不支持

3. 相关 API 
  - [使用 Fetch](https://developer.mozilla.org/zh-CN/docs/Web/API/Fetch_API/Using_Fetch)

## github 搜索案例相关知识点
1. 设计状态时要考虑全面，如果带有网络请求的组件，要考虑请求失败怎么办

2. ES6 小知识点：解构赋值和重命
```javascript 
  let obj = {a: {b:1}}
  const {a} = obj; // 传统解构赋值
  const {a:{b}} = obj; // 连续解构赋值
  const {a:{b:value}} = obj; // 连续解构赋值+重命名
```

3. 消息发布订阅机制
  - 先订阅再发布，
  - 适用于任意组件之间的通信
  - 要在组件的 componentWithUnmount 中取消订阅

4. fetch 发送请求（关注分离设计思想）
```javascript
  try {
    const response = await fetch(`https://api.github.com/search/users?q=${keyword}`);
    const data = await response.json();
    console.log(data);
    PubSub.publish('search', { users: data.items, isLoading: false });
  } catch (error) {
    console.log('请求出错，', error);
    PubSub.publish('search', { err: error.message, isLoading: false });
  }
```

# React 路由

## 相关理解

### SPA 的理解
1. 单页 Web 应用 (Single Page Application, SPA)
2. 整个应用只有**一个完整的页面**
3. 点击页面中的链接不会刷新页面，只会做页面的**局部更新**
4. 数据都要通过 Ajax 请求获取，并在前端异步展现

### 路由的理解
1. 什么是路由？
  - 一个路由就是一个映射关系（key:value）
  - key 为路径，value 可能是 function 或 component 
2. 路由分类
  - 后端路由
    - 1). 理解：value 是 function，用来处理客户端提交的请求
    - 2). 注册路由：router.get(path, function(req, res))
    - 3). 工作过程：当 node 接收到一个请求时，根据请求路径找到匹配的路由，调用路由中的函数来处理请求，返回响应数据

  - 前端路由
    - 1). 浏览器端路由：value 是 component，用于展示页面内容
    - 2). 注册路由：<Router path="/test" component={Test}/>
    - 3). 工作过程：当浏览器的 path 变为 /test 时，当前路由组件就变为 Test 组件

### react-router 的理解
1. React 的一个插件库 （基于 web 的 react-router-dom, native, anywhere）
2. 专门用来实现一个 SPA 应用
3. 基于 React 的项目基本都会用到此库

### react-router 相关 API

#### 内置组件
1. <BrowserRouter>
2. <HashRouter>
3. <Route>
4. <Redirect>
5. <Link>
6. <NavLink>
7. <Switch>

#### 准备
1. 下载 react-router-dom, `npm i --save react-router-dom`
2. 引入 bootstrap.css, `<link href="%PUBLIC_URL%/css/bootstrap.css" rel="stylesheet">`

### 路由的基本使用
1. 明确好界面中的导航区，展示区
2. 导航区的 a 标签改为 Link 标签。`<Link to='/xxx'>Demo</Link>`
3. 展示区写 Route 标签进行路径匹配。`<Route path='/xxx' component={Demo}/>`
4. App 外侧包裹一个 `<BrowserRouter>` 或 `<HashrRouter>`

### 路由组件与一般组件
1. 写法不同
  - 一般组件： <Demo/>
  - 路由组件： <Route path='/demo' component={Demo}/>
2. 存放位置不同
  - 一般组件： components
  - 路由组件： pages
3. 接收到的 props 不同
  - 一般组件： 写组件标签时，传递了什么就能收到什么
  - 路由组件： 接收到三个固定的属性。history, location, match。注意：V6版本路由组件写法变化，与一般组件一样了，传递什么，才能接收到什么
  ```yaml
  history:
    go: f go(n)
    goBack: f goBack()
    goForward: f goForward()
    push: f push(path, state)
    replace: f replace(path, state)
  location:
    hash: ""
    key: "znwrid"
    pathname: "/about"
    search: ""
    state: undefined
  match:
    isExact: true
    params: {}
    path: "/about"
    url: "/about"
  ```

### NavLink 与封装 NavLink
1. NavLink 可以实现路由链接高亮，（低版本中）通过 `activeClassName` 指定样式名
2. 标签体内容是一个特殊的标签属性
3. 通过 `this.props.children` 可以获取标签体内容

### Switch 的使用
1. 通常情况下，path 和 component 是一一对应的关系
2. Switch 可以提高路由匹配效率（单一匹配）
3. 高版本中没有 Switch，已经解决了同时匹配多个组件的问题

### 解决多级路径刷新页面样式丢失的问题
1. public/index.html 中引入样式时不写 ./ 写 / (常用)
2. public/index.html 中引入样式时不写 ./ 写 %PUBLIC_URL% (常用)
3. 使用 HashRouter

### 路由的严格匹配与模糊匹配
1. 默认的使用的是模糊匹配（v6以前），简单记，输入的路径必须包含匹配的路径，且 输入路径.startWith(匹配的路径)
2. 开启严格匹配：`<Route exact={true} path='/home' component={Home}/>`
3. 严格匹配不要随便开启，需要时再开，有些时候开启会导致无法继续匹配二级路由

### Redirect 的使用
1. 一般写在所有路由注册的最下方，当所有路由都无法匹配时，跳转到 Redirect 指定的路由
2. 具体写法
  ```javascript
    // V6 以前的写法
    <Switch>
      <Route path='/home' component={Home}/>
      <Route path='/about' component={About}/>
      <Redirect to="/home" />
    </Switch>

    // V6 的写法
    <Routes>
      <Route path='/about' element={<About/>}/>
      <Route path='/home' element={<Home/>}/>
      <Route path='*' element={<Navigate to="/home"/>} />
    </Routes>
  ```

### 嵌套路由
1. 注册子路由时要写上父路由的 path 值（V6 以前）
2. 路由的匹配是按照注册路由的顺序进行的

### 向路由传递参数
1. params 参数 
  - V6 以前的做法
    路由链接（携带参数）：`<Link to={`/demo/test/tom/18`}>详情</Link>`
    注册路由（声明接收）：`<Route path="/demo/test/:name/:age" component={Test} />`
    接收参数：`const {name, age} = this.props.match.params`

  - V6 的做法
    路由链接（携带参数）：`<Link to={`test/tom/18`}>详情</Link>`
    注册路由（声明接收）：`<Route path='test/:name/:age' element={<Test/>}/>`
    接收参数：使用 `useParams` hook, `const {name, age} = useParams();` **要特别注意**， `useParams` hook 只能用在函数式组件中。

2. 传递 search 参数
  - V6 以前的做法
    路由链接（携带参数）：`<Link to={`/demo/test?name=tom&age=18`}>详情</Link>`
    注册路由（无需声明，正常注册即可）：`<Route path="/demo/test" component={Test} />`
    接收参数：`const {search} = this.props.location`
    备注：获取到的 search 是 urlEncoded 的字符串，需要借助 qs 解析

  - V6 的做法
    路由链接（携带参数）：`<Link to={`test?name=tom&age=18`}>详情</Link>`
    注册路由（无需声明）：`<Route path='test' element={<Test/>}/>`
    接收参数：使用 `useSearchParams` hook, `const [params] = useSearchParams(); const name = params.get('name');` **要特别注意**， `useSearchParams` hook 只能用在函数式组件中。

3. 传递 state 参数
  - V6 以前的做法
    路由链接（携带参数）：`<Link to={{pathname:'detail', state: {id:msgObj.id,title:msgObj.title}}}>{msgObj.title}</Link>`
    注册路由（无需声明，正常注册即可）：`<Route path="/demo/test" component={Test} />`
    接收参数：`const {search} = this.props.location`
    备注：刷新也可以保留参数

  - V6 的做法
    路由链接（携带参数）：`<Link to='detail' state={{id:msgObj.id,title:msgObj.title}}>{msgObj.title}</Link>`, state 是一个单独的 property
    注册路由（无需声明）：`<Route path='test' element={<Test/>}/>`
    接收参数：使用 `useLocation` hook, `const name = useLocation().state.name` **要特别注意**， `useLocation` hook 只能用在函数式组件中。
    备注：刷新也可以保留参数

### 编程式路由导航
V6 以前借助 this.props.history 对象上的 API 对操作路由跳转，前进，后退
```javascript
this.props.history.push()
this.props.history.replace()
this.props.history.goBack()
this.props.history.goForward()
this.props.history.go()
```
V6 要借助 `useNavigate, useParams, useSearchParams, useLocation` 这几个 hook, 具体可以参考下面的链接：
- [React路由使用步骤(含三种传参方式+编程式导航)](https://blog.csdn.net/weixin_68658847/article/details/130295502)
- [2.React框架之函数组件路由及参数传递React-Router（V6）](https://blog.csdn.net/qq_44438941/article/details/129018373)

### withRouter 
1. withRouter 可以加工一般组件，让一般组件具备路由组件所特有的 API
2. withRouter 的返回值是一个新组件

### BrowserRouter 和 HashRouter 的区别
1. 底层原理不同
- BrowserRouter 使用的是 H5 的 history API, 不兼容 IE9 及以下版本
- HashRouter 使用的是 URL 的 hash 值
2. path 表现形式不同
- BrowserRouter 路径中没有 #，例如：localhost:3000/demo/test
- HashRouter 路径中包含 #，例如：localhost:3000/#/demo/test
3. 刷新后对路由 state 参数的影响
- BrowserRouter 没有任何影响，因为 state 保存在 history 对象中
- HashRouter 刷新后会导致 state 参数的丢失
4. HashRouter 可以用于解决一些路径错误相关的问题


## React UI 组件库

### 流行的开源 React UI 组件库
1. material-ui (国外)
  - 官网： [https://mui.com/material-ui/](https://mui.com/material-ui/)
  - GitHub: [https://github.com/mui/material-ui](https://github.com/mui/material-ui)
2. ant-design (国内蚂蚁金服)
  - 官网： [https://ant.design/index-cn](https://ant.design/index-cn)
  - GitHub: [https://github.com/ant-design/ant-design](https://github.com/ant-design/ant-design)
3. Element UI
  - 官网：[https://element.eleme.cn/#/zh-CN](https://element.eleme.cn/#/zh-CN)
  - GitHub: [https://github.com/ElemeFE/element](https://github.com/ElemeFE/element)
4. Vant UI
  - 官网： [https://vant-contrib.gitee.io/vant/#/zh-CN/](https://vant-contrib.gitee.io/vant/#/zh-CN/)

### antd(4.x) 的按需引入和自定义主题使用
`npm run eject` 暴露 react 脚手架 webpack 的配置。注意：这一步骤不可逆，一旦暴露了，不能再隐藏。
1. 安装依赖： react-app-rewired customize-cra babel-plugin-import less less-loader
2. 修改 package.json
  ```json
  /* package.json */
  "scripts": {
    "start": "react-app-rewired start",
    "build": "react-app-rewired build",
    "test": "react-app-rewired test",
    "eject": "react-scripts eject"
  }
  ```
3. 在根目录下创建`config-overrides.js`用于修改默认配置。
```javascript
  const { override, fixBabelImports } = require('customize-cra');

  module.exports = override(
    fixBabelImports('import', {
      libraryName: 'antd',
      libraryDirectory: 'es',
      style: true,
    }),
    addLessLoader({
      lessOptions: {
        javascriptEnabled: true,
        modifyVars: { '@primary-color': '#1DA57A' }
      }
    }),
  );
```
4. 备注：不用在组件里亲自引入样式了，即：`import 'antd/dist/antd.css'`应该删掉


###

