/**
 * 该文件用于汇总所有的 reducer 为一个总的 reducer
 */
// 引入 combineReducers 用于汇总多个 reducer
import {combineReducers} from 'redux'

// 引入为 Count 组件服务的 reducer
// import countReducer from './count'
import sum from './count'
// 引入为 Person 组件服务的 reducer
// import personReducer from './person'
import persons from './person'

// 汇总所有 reducer 变为一个总的 reducer
/* export default combineReducers({
    sum: countReducer,
    persons: personReducer
}); */

// 对象的简写方式，key 和 value 变量同名时
export default combineReducers({
    sum,
    persons
});