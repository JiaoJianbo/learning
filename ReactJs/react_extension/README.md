# React 进阶扩展

## 1. setState
setState 更新状态的两种写法
1. setState(stateChange, [callback])  -- 对象式的 setState
  - stateChange 为状态改变对象（该对象可以体现出状态的更改）
  - callback 是可选的回调函数，它在状态更新完毕、界面也更新后（render 调用后）才被调用

2. setState(updater, [callback])  -- 函数式的 setState
  - updater 为返回 stateChange 对象的函数
  - updater 可以接收到 state 和 props
  - callback 是可选的回调函数，它在状态更新完毕、界面也更新后（render 调用后）才被调用

总结：
1. 对象式的 setState 是函数式的 setState 的简写方法（语法糖）
2. 使用原则：
  1) 如果新状态不依赖原状态，则使用对象式
  2) 如果新状态依赖原状态，则使用函数式
  3) 如果需要在 setState() 执行之后获取最新的状态数据，要在 callback 函数中读取


## 2. LazyLoad
路由组件的 lazyLoad
```javascript
// 1.通过 React 的 lazy() 函数配合 import() 函数动态加载路由组件 —— 路由组件代码会被分开打包
const Login = lazy(()=>import ('@/pages/Login'))

// 2.通过 <Suspense> 指定在加载得到路由打包文件前显示一个自定义的 loading 界面
<Suspense fallback={<h1>loading......</h1>}>
  <Switch>
    <Route path="/xxx" component={Xxx} />
    <Redirect to="/login"/>
  </Switch>
</Suspense>
```

## 3. Hooks
### React Hook/Hook 是什么？
1. Hook 是 React 16.8.0 版本新增加的新特性/语法
2. 可以让你在函数组件中使用 state 以及其他的 React 特性

### 三个常用 Hook
1. State Hook: React.useState()
2. Effect Hook: React.useEffect()
3. Ref Hook: React.useRef()

### State Hook
1. State Hook 让函数组件也可以拥有 state 状态，并进行 state 数据读写操作
2. 语法： `const [xxx, setXxx] = React.useState(initValue)`
3. useState() 说明：
  - 参数：第一次初始化指定的值在内部做缓存
  - 返回值：包含两个元素的数组，第一个为内部当前 state 值，第二个为更新 state 值的函数
4. setXxx() 的两种写法:
  - setXxx(newValue): 参数为非函数值，直接指定新的状态值，内部用其覆盖原来的状态值
  - setXxx(value => newValue): 参数为函数，接收原来的状态值，返回新的状态值，内部用其覆盖原来的状态值
  
### Effect Hook
1. Effect Hook 可以让你在函数组件中执行副作用操作（用于模拟类组件中的生命周期钩子）
2. React 中的副作用操作：
  - 发 Ajax 请求获取数据
  - 设置订阅/启动定时器
  - 手动更改真实 DOM
3. 语法和说明
```javascript
  useEffect(() => {
    // 在此可以执行任何带副作用的操作

    return () => { // 在组件卸载前执行
      // 在此做一些收尾工作，如清除定时器/取消订阅等
    }
  }, [stateValue]); // 如果指定的是[]，回调函数只会在第一次 render() 之后执行
```
4. 可以把 useEffect 看作下面三个函数的组合
  - `componentDidMount()`
  - `componentDidUpdate()`
  - `componentWillUnmount()`

### Ref Hook
1. Ref Hook 可以在函数组件中存储/查找组件内的标签或任意其他数据
2. 语法：`const refContainer = React.useRef();`
3. 作用：保存标签对象，功能与 React.createRef() 一样

## 4. Fragment
1. 用法 `<Fragment>...</Fragment>`，可以有一个 key 属性
2. 可以不用必须有一个真实的 DOM 根标签。React 解析时，会自动舍弃这个标签，只保留里面的内容

## 5. Context
### 理解
一种组件通信方式，常用于“祖组件”与“后代组件”间通信

### 使用
1. 创建 Context 容器对象
```javascript
const XxxContext = React.createContext();
```
2. 渲染子组件时，外面包裹 `XxxContext.Provider`，通过 **value** 属性给后代组件传递数据
```javascript
<XxxContext.Provider value={数据}>
  // 子组件
</XxxContext.Provider>
```
3. “后代组件”读取数据
```javascript
// 第一种方式，仅适用于类式组件
static contextType = xxxContext; // 声明接收 context
this.context // 读取 context 中的 value

// 第二种方式，函数组件与类式组件都可以
<XxxContext.Consumer>
  {
    value => ( // value 就是 context 中的 value 数据
      // 要显示的内容
    )
  }
</XxxContext.Consumer>
```
### 注意
在一用开发中一般不用 context, 一般都用它的封装 react 插件

## 6. 组件优化
### Component 的两个问题
1. 只要执行 setState() 方法，即使不改变状态的数据，组件也会重新 render
2. 只要当前组件重新 render，就会自动重新 render 子组件，即使子组件没有用到父组件的任何数据 -- 效率低 

### 效率高的做法
只有当组件的 state 或者 props 数据发生改变时才重新 render

### 原因
Component 中的 shouldComponentUpdate() 方法总是返回 true 

### 解决办法
1. 办法一
重写 shouldComponentUpdate() 方法。比较新旧 state 或者 props 数据，如果有变化才返回 true，如果没有则返回 false
2. 办法二
使用 PureComponent。PureComponent 重写了 shouldComponentUpdate(), 只有 state 或者 props 数据有变化才返回 true。但是注意下面几点：
  - 只是进行 state 和 props 数据的**浅比较**，如果只是数据对象内部数据变了，返回 false。 
  - 不要修改 state 数据，而是产生新数据。 
  - 项目中一般使用 PureComponent 来优化

## 7. render props
### 如何向组件内部动态传入带内容的结构（标签）
- Vue 中，使用 solt 技术，也就是通过组件标签体传入结构，例如 `<A><B/></A>`
- React 中
  - 使用 childre props, 通过组件标签体传入结构
  - 使用 render props, 通过组件标签属性传入结构，而且可以携带属性，一般用 `render` 函数属性

### children props
```
<A>
  <B> ....</B>
</A>

this.props.children
```
但是有个问题，如果 B 组件需要用 A 内的数据 -- 这种方式做不到

### render props
```
<A render={(data) => <C data={data}></C>}> </A>

A 组件: {this.props.render(state 数据)}
C 组件: 读取 A 组件传入的数据显示, {this.props.data}
```

## 8. 错误边界
### 理解
错误边界 (Error Boundary): 用来捕获后代组件的错误，渲染出备用页面

### 特点
**只能捕获后代组件生命周期产生的错误**，不能捕获自己组件产生的错误和其他在合成事件、定时器中产生的错误。只在生产环境有效，即运行 build 之后的文件时有效。

### 使用方式
`getDerivedStateFromError`配合`componentDidCatch`
```javascript
// 生命周期函数，一旦后台组件报错，就会触发
static getDerivedStateFromError(error) {
  console.log(error);
  // 在 render 之前触发，返回新的 state
  return {
    errorInfo: error
  }
}

componentDidCatch(error, info) {
  // 统计页面错误，发送请求到后台
  console.log(error, info);
}
```

## 9. 组件通信方式总结
### 组件间的关系
- 父子关系
- 兄弟关系（非嵌套组件）
- 祖孙关系（跨级组件）

### 几种通信方式
1. props
  - children props
  - render props
2. 消息发布-订阅
  - pub-sub, event 等
3. 集中式管理
  - redux, dva 等
4. Context
  - Provider - Consumer 模式

### 比较好的搭配方式
1. 父子组件： props
2. 兄弟组件： 发布-订阅, 集中式管理
3. 跨级组件： 发布-订阅, 集中式管理，Context (开发用的少，封装插件用的多)
