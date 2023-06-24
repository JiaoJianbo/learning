//console.log('入口文件');
// 引入 React 核心库
import React from 'react';
// 引入 ReactDOM
// import ReactDOM from 'react-dom';
// React18 from 'react-dom/client'
import ReactDOM from 'react-dom/client';
// 引入 App 组件，.js, .jsx 可以省略
import App from './App';

// 渲染组件, React18以前的写法
// ReactDOM.render(<App/>, document.getElementById('root'));

// React 18 推荐的写法
const root = ReactDOM.createRoot(document.getElementById('root'));
// root.render(
//     <React.StrictMode>
//       <App />
//     </React.StrictMode>
//   );
root.render(<App />);