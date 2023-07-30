import React from 'react';
import ReactDOM from 'react-dom/client';
import App from './App';
// 引入 store 用于获取保存在 store 中的 state
import store from './redux/store'
import {Provider} from 'react-redux'

const root = ReactDOM.createRoot(document.getElementById('root'));
root.render(
  <React.StrictMode>
    {/* 在这里统一传入 store，目的是让 App 的所有后代容器组件都能接收到 store */}
    <Provider store={store}>
      <App />
    </Provider>
  </React.StrictMode>
); 

