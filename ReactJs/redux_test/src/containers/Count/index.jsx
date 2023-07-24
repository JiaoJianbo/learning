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

/* 
// 映射状态
const mapStateToProps = state => ({count:state});

// 映射操作状态的方法
const mapDispatchToProps = dispatch => ({
    // 通过 redux 执行加法
    add: value => dispatch(createIncremantAction(value)),
    decrement: value => dispatch(createDecremantAction(value)),
    asyncAdd: (value, ts) => dispatch(createIncremantAsyncAction(value, ts))
})
 */

export default connect(
    state => ({count:state}), 
    // mapDispatchToProps 的一般写法 （一个函数）
    /* dispatch => ({
        add: value => dispatch(createIncremantAction(value)),
        decrement: value => dispatch(createDecremantAction(value)),
        asyncAdd: (value, ts) => dispatch(createIncremantAsyncAction(value, ts))
    }) */
    
    // mapDispatchToProps 的简写形式 (一个对象)。react-redux 帮你去 dispatch
    {
        add: createIncremantAction,
        decrement: createDecremantAction,
        asyncAdd: createIncremantAsyncAction
    }

)(CountUI);
