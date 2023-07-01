# 1. 使用 create-react-app 创建 React 应用

## 1.1 react 脚手架
1. xxx脚手架，用来帮助程序员快速构建一个基于 xxx 库的模板项目
  - 包含了所有需要的配置（语法检查，jsx 编译，devServer）
  - 下载好了所有相关的依赖
  - 可以直接运行一个简单效果

2. React 提供了一个用于创建 react 项目的脚手架库 create-react-app

3. 项目的整体技术架构为 react + webpack + es6 + eslint

4. 使用脚手架开发项目的特点：模块化，组件化，工程化


## 1.2 创建项目并启动

1. 全局安装，`npm install -g crate-react-app`，安装指定版本，`npm i -g create-react-app@4.0.3`

2. 切换到想创建项目的目录，输入命令 `create-react-app hello-react`

3. 进入项目文件夹，`cd hello-react`

4. 启动项目，`npm start`


SPA (Single Page Application)


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