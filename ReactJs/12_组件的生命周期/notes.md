### 理解
1. 组件从创建到死亡它会经历一些特定的阶段
2. React 组件中包含一系列的钩子函数（生命周期回调函数），会在特定的时机调用
3. 我们在定义组件时，会在特定的生命周期回调函数中，做特定的工作


### 生命周期的三个阶段（旧）

1. **初始化阶段**：由 ReactDOM.render() 触发 —— 初次渲染
  - 1. constructor()
  - 2. componentWillMount()
  - 3. render()
  - 4. componentDidMount()


2. **更新阶段**：由 this.setState() 或由父组件 render() 触发
  - 1. shouldComponentUpdate()
  - 2. componentWillUpdate()
  - 3. render()
  - 4. componentDidUpdate()


3. **卸载阶段**：由 ReactDOM.unmountComponentAtNode() 触发
  - 1. componentWillUnmount()


#### 常用的
- componentDidMount()
- render()
- componentWillUnmount
