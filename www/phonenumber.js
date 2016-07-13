// Phone Object
var Phone = {
  getNumber: function (success, error) {
    cordova.exec(success, error, 'PhoneNumber', "get")
  }
}

module.exports = Phone
