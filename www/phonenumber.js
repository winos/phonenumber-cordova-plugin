// Phone Object
var Phone = {
  get: function (success, error) {
    cordova.exec(success, error, 'PhoneNumber', "get")
  }
}

module.exports = Phone
