var express = require('express'),
  router = express.Router(),
  user = require('../controllers/user');

module.exports = function (app) {
  router.get('/user/:username', user.get(app));
  router.post('/user/:username', user.auth(app));
  router.post('/user', user.post(app));
  router.post('/user/:username/nickname', user.changeNickname(app));
  router.post('/user/:username/password', user.changePassword(app));

  return router;
}