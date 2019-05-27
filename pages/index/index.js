Page({

  /**
   * 页面的初始数据
   */
  data: {
    classData: [{ name: "二年级一班（0）", age: 20, id: "0" },
      { name: "二年级一班（1）", age: 20, id: "1" },
      { name: "二年级一班（2）", age: 20, id: "2" },
      { name: "二年级一班（3）", age: 20, id: "3" },
      { name: "二年级一班（4）", age: 20, id: "4" },
      { name: "二年级一班（5）", age: 20, id: "5" },
      { name: "二年级一班（6）", age: 20, id: "6" },
      { name: "二年级一班（7）", age: 20, id: "7" },
      { name: "二年级一班（8）", age: 20, id: "8" },
      { name: "二年级一班（9）", age: 20, id: "9" },
      { name: "二年级二班（1）", age: 20, id: "10" },
      { name: "二年级二班（2）", age: 20, id: "11" },
      { name: "二年级二班（3）", age: 20, id: "13" },
      { name: "二年级二班（4）", age: 20, id: "14" },
      { name: "二年级二班（5）", age: 20, id: "15" },
               
    ]
  },
  /**
  * 开始点名
  */
  goClass:function(event){
    console.log(event.target.dataset.index)
    wx.navigateTo({
      url: '../kaoqin/kaoqin'
    })
  },
  /**
   * 生命周期函数--监听页面加载
   */
  onLoad: function (options) {

  },

  /**
   * 生命周期函数--监听页面初次渲染完成
   */
  onReady: function () {

  },

  handleLeave: function (event) {
    console.log(event.target.dataset.index)

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