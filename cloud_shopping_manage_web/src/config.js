const baseUrl = 'http://api.cloudshopping.com'

const config = {
  locale: 'zh-CN', // en-US, zh-CN
  url: baseUrl,
  debug: {
    http: false // http request log
  },
  api: `${baseUrl}/api`,
  changeOrigin: true, // 是否跨域
  theme:{
    primary: "#2196F3",
    secondary: "#455A64",
    accent: "#9c27b0",
    error: "#f44336",
    warning: "#FFC107",
    info: "#64B5F6",
    success: "#4caf50"
  },
  isDark:true,
  unitOption:[
    { header: '长度' },
    { value: 'mm'},
    { value: 'cm'},
    { value: 'dm'},
    { value: 'm'},
    { value: '寸'},
    { value: '英寸'},
    { value: '尺'},
    { divider: true },
    { header: '重量' },
    { value: 'mg'},
    { value: 'g'},
    { value: 'kg'},
    { value: 't'},
    { divider: true },
    { header: '像素' },
    { value: '万像素'},
    { divider: true },
    { header: '频率' },
    { value: 'Hz'},
    { value: 'mHz'},
    { value: 'gHz'},
    { divider: true },
    { header: '存储' },
    { value: 'KB'},
    { value: 'MB'},
    { value: 'GB'},
    { divider: true },
    { header: '电压' },
    { value: 'V'},
    { value: 'KV'},
    { divider: true },
    { header: '电池容量' },
    { value: 'mAh'},
    { divider: true },
    { header: '功率' },
    { value: 'w'},
    { value: 'Kw'},
    { divider: true },
    { header: '电流' },
    { value: 'μA'},
    { value: 'mA'},
    { value: 'A'},
    { divider: true },
    { header: '电阻' },
    { value: 'Ω'},
    { value: 'KΩ'},
    { value: 'A'},
    { divider: true },
  ]
}

export default config
