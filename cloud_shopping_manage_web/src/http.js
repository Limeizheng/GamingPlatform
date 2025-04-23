import Vue from 'vue'
import axios from 'axios'
import config from './config'

axios.defaults.baseURL = config.api;// Set the basic request path of axios
axios.defaults.timeout = 2000;// Set the request time of axios
axios.defaults.withCredentials = true; //cookie writing issue

// axios.interceptors.request.use(function (config) {
//   // console.log(config);
//   return config;
// })
axios.loadData = async function (url){
  const resp = await axios.get(url);
  return resp.data;
}

Vue.prototype.$http = axios;// Add axios to the prototype of vue so that all vue instances can use this object
