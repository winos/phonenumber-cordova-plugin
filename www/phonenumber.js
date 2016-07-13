// Phone Object
var exec = require('cordova/exec')

var Phone = {
  get: function (success, error) {
    exec(success, error, 'PhoneNumber', "get", [])
  }
}

module.exports = Phone
