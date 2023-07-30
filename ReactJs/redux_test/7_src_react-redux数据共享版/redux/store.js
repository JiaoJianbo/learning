/**
 * 该文件专门用于暴露一个 store 对象，整个应用只有一个 store 对象
 */
// 引入 crateStore 专门用于创建 redux 中最核心的 store 对象, 新版本中 createStore 已经过时
import {legacy_createStore as createStore, applyMiddleware, combineReducers} from 'redux'
// 引入 redux-thunk，用于支持异步 action
import thunk from 'redux-thunk'

// 引入为 Count 组件服务的 reducer
import countReducer from './reducers/count'
// 引入为 Person 组件服务的 reducer
import personReducer from './reducers/person'

// 汇总所有 reducer 变为一个总的 reducer
const allReducer = combineReducers({
    sum: countReducer,
    rens: personReducer
});

export default createStore(allReducer, applyMiddleware(thunk));