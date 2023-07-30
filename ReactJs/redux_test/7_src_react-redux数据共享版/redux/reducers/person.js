import {ADD_PERSON} from '../constant'

// 使用参数默认值方式设定初始化状态
const initState = [{id:'001', name:'Tom', age:18}];
export default function countReducer(preState = initState, action) {
    const {type, data} = action;

    switch (type) {
        case ADD_PERSON:
            return [data, ...preState];
    
        default:
            return preState;
    }

}