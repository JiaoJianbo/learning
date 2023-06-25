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

