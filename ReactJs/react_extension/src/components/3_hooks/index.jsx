import React from 'react'
// import ReactDOM from 'react-dom/client';

function Demo() {
    console.info('Demo');

    const [count, setCount] = React.useState(0);
    // console.log(count, setCount);
    // const [name, setName] = React.useState('Tom');

    function add(){
        // setCount(count+1); // 第一种写法
        setCount(count => count+1); // 第二种写法
    }

    /* function changeName() {
        setName('Tim');
    } */

    // 第二参数不写时，相当于所有都监测。但如果是[]表示都不监测,其效果就相当于 componentDidMount()
    React.useEffect(()=>{
        let timer = setInterval(() => {
            setCount(count => count+1);
        }, 1000);

        // 该返回值相当与 componentWillUnmount()
        return () => {
            clearInterval(timer);
        }
    }, []);

    function unmount() {
        
    }

    const myRef = React.useRef();

    function show() {
        alert(myRef.current.value)
    }


    return (<div>
        <h2>当前求和为： {count}</h2>
        <button onClick={add}>点我+1</button>
        {/* <h2>当前名字为： {name}</h2>
        <button onClick={changeName}>点我改名</button> */}
        <button onClick={unmount}>卸载组件</button> 
        <br/><br/>
        <input type="text" ref={myRef}/>
        <button onClick={show}>点击显示数据</button>
    </div>);
}


export default Demo;