/**
 * 该文件专门用于暴露一个 store 对象，整个应用只有一个 store 对象
 */
// 引入 crateStore 专门用于创建 redux 中最核心的 store 对象, 新版本中 createStore 已经过时
import {legacy_createStore as createStore, applyMiddleware} from 'redux'
// 引入 redux-thunk，用于支持异步 action
import thunk from 'redux-thunk'
// 引入汇总之后的 reducer
import allReducer from './reducers'
// 引入 redux-devtools-extension
import {composeWithDevTools} from 'redux-devtools-extension'

// export default createStore(allReducer, applyMiddleware(thunk));
export default createStore(allReducer, composeWithDevTools(applyMiddleware(thunk)));