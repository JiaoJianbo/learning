/**
 * 该文件专门为 Count 组件生成 action 对象
 */
import {INCREMENT, DECREMENT} from './constant'
/* 
function createIncremantAction(data) {
    return {type:'increment', data};
}

function createDecremantAction(data) {
    return {type:'decrement', data};
}
*/

export const createIncremantAction = data => ({type:INCREMENT, data})
export const createDecremantAction = data => ({type:DECREMENT, data})
