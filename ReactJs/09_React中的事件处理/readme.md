### 事件处理
1. 通过 onXxx 属性指定事件处理函数（注意大小写）
    - React 使用的是自定义（合成）事件，而不是使用的原生 DOM 事件 —— 为了更好的兼容性
    - React 中的事件是通过事件委托方式处理的（委托给组件最外层的元素） —— 为了高效
2. 通过 event.target 得到发生事件的 DOM 元素 —— 不要过度使用 ref
