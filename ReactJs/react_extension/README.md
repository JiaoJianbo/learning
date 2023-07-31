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

