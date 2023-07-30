import React from 'react';
import ReactDOM from 'react-dom/client';
import App from './App';
// 引入 store 用于获取保存在 store 中的 state
import store from './redux/store'
import {Provider} from 'react-redux'

const root = ReactDOM.createRoot(document.getElementById('root'));
root.render(
  <React.StrictMode>
    {/* 在这里统一传入 store，不用再为每个组件单独传入了 */}
    <Provider store={store}>
      <App />
    </Provider>
  </React.StrictMode>
); 

/* 低版本中可以通过这种方式统一订阅
store.subscribe(() => {
  ReactDOM.render(document.getElementById('root'), <App/>);
});
 */
