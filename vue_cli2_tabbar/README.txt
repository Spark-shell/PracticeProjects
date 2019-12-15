axios 使用记录：
  1.执行GET请求
    案例：请求'http://httpbin.org/get
        方式一：不带参数
              axios.get('http://httpbin.org/get')
                .then(function (response) {
                  console.log(response);
                }) .catch(function (error) {
                  console.log(error);
                });
       方式二：带参数
              axios.get('http://httpbin.org/get', { params: { ID: 12345 })
              .then(function (response) {
                  console.log(response);
                }).catch(function (error) {
                  console.log(error);
                });
       方式三：
             axios({
               url:'http://httpbin.org/get',
               method:'get'
             }).then(res => {
               console.log(res)
             })
  2.执行POST请求
        方式一：
          axios.post('http://httpbin.org/post', { firstName: 'Fred', lastName: 'Flintstone' })
            .then(function (response) {
              console.log(response);
            })
            .catch(function (error) {
              console.log(error);
            });
  3.执行多个并发请求
          function getUserAccount() {
            return axios.get('http://httpbin.org/get');
          }
          function getUserPermissions() {
            return axios.get('http://httpbin.org/get');
          }
          axios.all([getUserAccount(), getUserPermissions()])
            .then(axios.spread(function (acct, perms) {
              // 两个请求现在都执行完成
            }));
  4.可以通过向 axios 传递相关配置来创建请求
      axios(config)
          // 发送 POST 请求
          axios({
            method: 'post',
            url: '/user/12345',
            data: {
              firstName: 'Fred',
              lastName: 'Flintstone'
            }
          });
          // 获取远端图片
          axios({
            method:'get',
            url:'http://bit.ly/2mTM3nY',
            responseType:'stream'
          })
            .then(function(response) {
            response.data.pipe(fs.createWriteStream('ada_lovelace.jpg'))
          });

