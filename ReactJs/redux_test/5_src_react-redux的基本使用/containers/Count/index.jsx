// 引入 Count UI 组件
import CountUI from '../../components/Count'
// 引入 connect 用于连接 UI 组件与 redux
import {connect} from 'react-redux'
// 引入 action
import {
    createIncremantAction, 
    createDecremantAction, 
    createIncremantAsyncAction
} from '../../redux/count_action'

/**
 * 1. mapStateToProps 函数的返回值是一个对象
 * 2. 返回的对象中 key 就作为传递一个 UI 组件 props 的 key，value 就作为传递给 UI 组件 props 的 value
 * 3. mapStateToProps 用于状态传递
 */
function mapStateToProps(state) {
    return {count:state};
}
/**
 * 1. mapDispatchToProps 函数的返回值是一个对象
 * 2. 返回的对象中 key 就作为传递一个 UI 组件 props 的 key，value 就作为传递给 UI 组件 props 的 value
 * 3. mapDispatchToProps 用于操作状态的方法
 */
function mapDispatchToProps(dispatch) {
    return {
        // 通过 redux 执行加法
        add: value => dispatch(createIncremantAction(value)),
        decrement: value => dispatch(createDecremantAction(value)),
        asyncAdd: (value, ts) => dispatch(createIncremantAsyncAction(value, ts))
    };
}


export default connect(mapStateToProps, mapDispatchToProps)(CountUI);
