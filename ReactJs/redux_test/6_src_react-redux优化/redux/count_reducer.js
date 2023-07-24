/**
 * 该文件是用于创建一个为 Count 组件服务的 reducer，reducer 本质就是个函数
 * reducer 函数会接到两个参数：分别为之前的状态 preState, 动作对象 action
 */
import {INCREMENT, DECREMENT} from './constant'

// 使用参数默认值方式设定初始化状态
const initState = 0;
export default function countReducer(preState = initState, action) {
  console.log('countReducer', preState, action);

  // 如果 preState 是 undefined, 那么是初始化状态
  // if(preState === undefined) {preState = 0;}

  // 从 action 对象中获取 type, data
  const {type, data} = action;

  // 根据 type 决定如何加工数据
  switch (type) {
    case INCREMENT:
      return preState + data;
    case DECREMENT:
      return preState - data;

    default:
      return preState;
  }
}