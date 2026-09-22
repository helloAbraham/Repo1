const { defineConfig } = require('@vue/cli-service')
module.exports = defineConfig({
  transpileDependencies: true,
  devServer: {
    proxy: {
      '/api': {
        target: 'https://api.abrahamparker.com',
        changeOrigin: true,
        pathRewrite: { '^/api': '/api' },
      },
      '/hls': {
        target: 'https://www.abrahamparker.com',
        changeOrigin: true,
        pathRewrite: { '^/hls': '/hls' },
      },
    },
  },
})

