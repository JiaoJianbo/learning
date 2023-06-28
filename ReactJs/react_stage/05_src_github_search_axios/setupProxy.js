// 这里面使用 common JS 语法，而不是 ES6 的
const {createProxyMiddleware} = require('http-proxy-middleware')

module.exports = function(app) {
    app.use(
        // 这里配置的代理暂时没用到
        createProxyMiddleware('/api5000', {  // 遇见带 /api5000 前缀的请求，就会触发该代理配置
            target: 'http://localhost:5000', // 请求转发给谁
            changeOrigin: true,              // 控制服务端收到的 request header 中 Host 的值，默认 false。true: Host=http://localhost:5000; false: Host=http://localhost:3000
            pathRewrite: {'^/api5000': ''}   // 重写请求路径。这里指的是把请求中 /api5000 替换为空串。必须配置
        })
    );
}
