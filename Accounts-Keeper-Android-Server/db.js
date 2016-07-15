var config = require('./config.json');

module.exports = {
  'url': 'mongodb://' + config.user + ':' + config.password + '@' + config.ip + ':27017/' + config.collection
};