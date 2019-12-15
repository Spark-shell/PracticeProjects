import axios from 'axios/index'
export function request(config,success,error) {
    const defaultRequest=axios.create({
      baseURL:'http://httpbin.org/',
      timeout:5000,
    })
    defaultRequest(config)
      .then(res=>{
          success(res)
      })
      .catch(err => {
        error(err)
    })
}
