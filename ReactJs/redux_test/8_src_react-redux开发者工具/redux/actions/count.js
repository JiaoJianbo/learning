/**
 * 该文件专门为 Count 组件生成 action 对象
 */
import {INCREMENT, DECREMENT} from '../constant'

// 同步 Action,就是 action 的值为 Object 类型的一般对象
export const createIncremantAction = data => ({type:INCREMENT, data})
export const createDecremantAction = data => ({type:DECREMENT, data})

// 异步 Action，就是指的 action 的值为函数，异步 action 中一般都会调用同步 action。异步 action 不是必须要用的
export const createIncremantAsyncAction = (data, ts) => {
    return (dispatch) => {
        setTimeout(() => {
            dispatch(createIncremantAction(data));
        }, ts);
    }
}
