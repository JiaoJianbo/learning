import {ADD_PERSON} from '../constant'

// 使用参数默认值方式设定初始化状态
const initState = [{id:'001', name:'Tom', age:18}];
export default function countReducer(preState = initState, action) {
    const {type, data} = action;

    switch (type) {
        case ADD_PERSON:
            /* 这种方式不行，redux 仅进行了“浅比较”，发现 preState 并未改变，因而不会调用 render 方法
            preState.unshift(data); // 这样就不是“纯函数”了
            return preState;
            */
            return [data, ...preState];
    
        default:
            return preState;
    }

}