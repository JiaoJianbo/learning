#


## redux

### redux 的理解
#### 文档
- 英文官网： https://redux.js.org
- 中文官网： https://cn.redux.js.org/
- 中文文档： https://www.redux.org.cn/
- GitHub: https://github.com/reduxjs/redux

#### redux 是什么
1. redux 是一个专门用于做**状态管理**的 JS 库（不是 React 插件库）
2. 它可以用在 React, Vue, Angular 等项目中，但基本与 React 配合使用
3. 作用：集中式管理 React 应用中多个组件**共享**的状态

#### 什么情况下需要使用 redux
1. 某个组件的状态需要让其他组件可以随时拿到（共享）
2. 一个组件需要改变另一个组件的状态（通信）
3. 总体原则：能不用就不用，如果不用比较吃力才考虑使用

#### redux 工作流程
1. redux 原理图
![redux原理图](redux.png)

#### redux 的三个核心概念
1. Action
  - 动作的对象
  - 包含两个属性 1）type: 标识属性，值为字符串，唯一，必要属性。2）data: 数据属性，值类型任意，可选属性
  - 示例： `{type: 'ADD_STUDENT', data:{name: 'Tome', age: 18}}`
2. reducer
  - 用于初始化状态、加工状态
  - 加工时，根据旧的 state 和 action，产生新的 state 的**纯函数**
3. Store
  - 将 state, action, reducer 联系在一起的对象
  - 如何得到此对象
    ```javascript
      import {createStore} from 'redux'
      import reducer from './reducer'
      const store = createStore(reducer)
    ```
  - 此对象的功能
    - getState() 得到 state
    - dipatch(action) 分发 action，触发 reducer 的调用，产生新的 state
    - subscribe(listener) 注册监听器，产生新的 state 时，自动调用
    

安装 `npm redux`

