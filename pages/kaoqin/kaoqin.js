Page({

  /**
   * 页面的初始数据
   */
  data: {
    classData: [{ name: "张启山", age: 20, id: "1" },
    { name: "陈皮", age: 20, id: "2" },
    { name: "小师娘", age: 20, id: "3" },
    { name: "二爷", age: 20, id: "4" },
    { name: "陈皮", age: 20, id: "5" },
    { name: "小师娘", age: 20, id: "6" },
    { name: "二爷", age: 20, id: "7" },
    { name: "陈皮", age: 20, id: "8" },
    { name: "小师娘", age: 20, id: "9" },
    { name: "二爷", age: 20, id: "10" },
    { name: "陈皮", age: 20, id: "11" },
    { name: "小师娘", age: 20, id: "12" },
    { name: "二爷", age: 20, id: "13" },
    { name: "陈皮", age: 20, id: "14" },
    { name: "小师娘", age: 20, id: "15" },
    { name: "二爷", age: 20, id: "16" },
    { name: "陈皮", age: 20, id: "17" },
    { name: "小师娘", age: 20, id: "18" },
    { name: "二爷", age: 20, id: "19" }
    ],
  },

  /**
   * 生命周期函数--监听页面加载
   */
  onLoad: function (options) {
    var classData = this.data.classData             //班级学生数据
    classData.forEach((item, index, array) => {     //添加样式数据    
      var cons = {}
      cons["con1"] = "clock-o"
      cons["con2"] = "close"
      cons["con3"] = "passed"
      item.cons = cons

    })
    this.setData({                                   //修改初始化数据
      "classData": classData
    })
  },
  /**
   * 考勤完成
   */
  complete: function (event) {

    wx.navigateTo({
      url: '../person/person'
    })
    var classData = this.data.classData
    var infos = {}                            //考勤结果数据，接口写好后直接把infos发送给接口就可以了
    classData.forEach((item, intex, array) => {
      var student = {}
      if (item.cons.con1 != "clock-o" || item.cons.con2 != "close" || item.cons.con3 != "passed") {
        if (item.cons.con1 != "clock-o") {
          student.kaoqin = 1
        }
        if (item.cons.con2 != "close") {
          student.kaoqin = 2
        }
        if (item.cons.con3 != "passed") {
          student.kaoqin = 3
        }
        student.id = item.id
        student.name = item.name
        infos[item.id] = student
      }
    })
    console.log(infos)
    // wx.navigateBack()
    wx.switchTab({                          //跳转到 tabBar 页面，并关闭其他所有非 tabBar 页面
      url: '../index/index'
    })
  },
  /**
   * 处理点名动作
   */
  handleLeave: function (event) {
    var index = event.target.dataset.index
    var itemId = index.split("_")[0] - 1
    var iconId = index.split("_")[1]
    var classData = this.data.classData
    var iconName = classData[itemId].cons["con" + iconId]


    if (iconId == 1 && iconName == 'clock-o') {
      classData[itemId].cons.con1 = "clock"
    } else {
      classData[itemId].cons.con1 = "clock-o"
    }

    if (iconId == 2 && iconName == 'close') {
      classData[itemId].cons.con2 = "clear"
    } else {
      classData[itemId].cons.con2 = "close"
    }

    if (iconId == 3 && iconName == 'passed') {
      classData[itemId].cons.con3 = "checked"
    } else {
      classData[itemId].cons.con3 = "passed"
    }

    this.setData({                                   //修改初始化数据
      "classData": classData
    })
    
  },


  /**
   * 生命周期函数--监听页面初次渲染完成
   */
  onReady: function () {

  },
  /**
   * 生命周期函数--监听页面显示
   */
  onShow: function () {

  },

  /**
   * 生命周期函数--监听页面隐藏
   */
  onHide: function () {

  },

  /**
   * 生命周期函数--监听页面卸载
   */
  onUnload: function () {

  },

  /**
   * 页面相关事件处理函数--监听用户下拉动作
   */
  onPullDownRefresh: function () {

  },

  /**
   * 页面上拉触底事件的处理函数
   */
  onReachBottom: function () {

  },

  /**
   * 用户点击右上角分享
   */
  onShareAppMessage: function () {

  }
})