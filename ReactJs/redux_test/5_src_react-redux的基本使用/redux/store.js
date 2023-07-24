/**
 * 该文件专门用于暴露一个 store 对象，整个应用只有一个 store 对象
 */
// 引入 crateStore 专门用于创建 redux 中最核心的 store 对象, 新版本中 createStore 已经过时
import {legacy_createStore as createStore, applyMiddleware} from 'redux'
// 引入 redux-thunk，用于支持异步 action
import thunk from 'redux-thunk'

// 引入为 Count 组件服务的 reducer
import countReducer from './count_reducer'

export default createStore(countReducer, applyMiddleware(thunk));