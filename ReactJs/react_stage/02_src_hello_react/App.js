// 创建“外壳”组件
// 引入 React 核心库
// import React from 'react';
import React, {Component} from 'react';

import Hello from './components/Hello/Hello';
import Welcome from './components/Welcome/Welcome';

// 创建并暴露 App 组件
// class App extends React.Component {
export default class App extends Component {
    render() {
        return <div>
            <Welcome/>
            <Hello/>
        </div>
    }
}

// export default App;